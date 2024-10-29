package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.398 행성 터널 / 난이도 중
 * 백준 플래티넘5 https://www.acmicpc.net/problem/2887
 */
public class 그래프이론_행성터널 {
    public static int[] parent;
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        parent = new int[n + 1];
        ArrayList<Edge> edges = new ArrayList<>();
        int result = 0;

        // 부모를 자기 자신으로 초기화
        for (int i = 1; i <= n ; i++) {
            parent[i] = i;
        }

        ArrayList<Position> x = new ArrayList<>();
        ArrayList<Position> y = new ArrayList<>();
        ArrayList<Position> z = new ArrayList<>();

        StringTokenizer st;
        // 좌표값 입력 받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            x.add(new Position(a, i));
            y.add(new Position(b, i));
            z.add(new Position(c, i));
        }

        Collections.sort(x);
        Collections.sort(y);
        Collections.sort(z);


        // 인접한 노드들로부터 간선 정보를 추출하여 처리
        for (int i = 0; i < n - 1; i++) {
            edges.add(new Edge(x.get(i + 1).getX() - x.get(i).getX(), x.get(i).getY(), x.get(i + 1).getY()));
            edges.add(new Edge(y.get(i + 1).getX() - y.get(i).getX(), y.get(i).getY(), y.get(i + 1).getY()));
            edges.add(new Edge(z.get(i + 1).getX() - z.get(i).getX(), z.get(i).getY(), z.get(i + 1).getY()));
        }


        // 간선을 비용순으로 정렬
        Collections.sort(edges);

        for (Edge edge : edges) {
            int cost = edge.getDistance();
            int a = edge.getNodeA();
            int b = edge.getNodeB();

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }
        System.out.println(result);
    }

    public static int findParent(int x) {
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    public static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }


    static class Edge implements Comparable<Edge> {
        private int distance;
        private int nodeA;
        private int nodeB;

        public Edge(int distance, int nodeA, int nodeB) {
            this.distance = distance;
            this.nodeA = nodeA;
            this.nodeB = nodeB;
        }

        public int getDistance() {
            return distance;
        }

        public int getNodeA() {
            return nodeA;
        }

        public int getNodeB() {
            return nodeB;
        }

        @Override
        public int compareTo(Edge o) {
            // 거리가 짧은 것이 높은 우선순위
            if(this.distance < o.distance) return -1;
            else if(this.distance == o.distance) return 0;
            return 1;
        }
    }

    static class Position implements Comparable<Position> {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Position o) {
            // x축, y축 순서대로 정렬
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }
}

/*
이 문제는 N - 1개의 터널을 설치해서 모든 행성이 연결되도록 요구하므로, 최소 신장 트리를 만드는 문제로 이해할 수 있다.
일단 크루스칼 알고리즘의 시간 복잡도는 간선의 개수가 E일 때 O(ElogE)이다.
이 문제에서는 기본적으로 임의의 두 노드 사이에 터널을 연결할 수 있다고 가정하므로, 간선의 개수는 N(N-1)/2 개가 된다.
N이 최대 100,000이라는 입력 조건을 감안해보면 이는 매우 큰 수가 될 수 있으므로, 모든 두 행성 간의 거리를 확이한느 방법으로는 문제를 해결할 수 없다.
하지만, 터털의 비용이 min(|Xa - Xb|, |Ya - Yb|, |Za - Zb|)라고 정의되어 있다. 이러한 특징을 이용하면 고려할 간선의 개수를 줄일 수 ㅇ씨다.
입력을 받은 뒤에 x축, y축, z축을 기준으로 각각 정렬을 수행한다.
만약 y축과 z축은 무시하고 오직 x축만 존재한다고 했을 때, 이러한 4개의 간선만 이용해도 항상 최소 신장트리를 만들 수 있다는 점이다.
즉, 이러한 방법을 이용하면 최소 신장 트리를 만들지 못하는 경우는 존재하지 않는다.
결과적으로 x축, y축, z축에 대하여 정렬 이후에 각각 N - 1개의 간선만 고려해도 최적의 솔루션을 찾을 수 있다는 아이디어를 떠욜려야한다.
따라서 문제 풀이를 위해 고려한 총 간선의 개수는 3 * (N - 1)개가 되고, 이를 이용해 크루스칼 알고리즘을 수행하면 된다.
 */