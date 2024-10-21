package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.397 어두운 길 / 난이도 중
 */
public class 그래프이론_어두운길 {
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken()); // 집의 수
        int m = Integer.parseInt(st.nextToken()); // 도로 수

        parent = new int[n + 1];
        for (int i = 1; i <= n ; i++) {
            parent[i] = i;
        }
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // 모든 간선에 대한 정보를 입력 받기
            edges.add(new Edge(z, x, y));
        }

        Collections.sort(edges); // 간선을 비용순으로 정렬
        int total = 0; // 전체 가로등 비용
        int result = 0;

        // 간선 하니씩 확인
        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();
            total += cost;

            // 사이클이 발생하지 않는 경우에만 집합에 포함
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
            }
        }

        // 절역할 수 있는 최대 금액을 출력해야하기 때문에
        // 전체 가로등 켜는 비용 - 최소 신장 트리를 구성하는 비용
        System.out.println(total - result);

    }

    // 두 원소가 속한 집합을 합치기
    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    // 특정 원소가 속한 집합을 찾기
    private static int findParent(int x) {
        // 루트 노드가 이니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
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
            // 거리(비용)가 짧은 것이 높은 우선순위를 가짐
            if(this.distance < o.distance) return  -1;
            else if(this.distance == o.distance) return 0;
            return 1;
        }
    }
}

/*
이 문제에서 가로등이 켜진 도로만ㅇ르 이용해서, 모든 두 집이 서로 도달이 가능해야 한다는 조건을 제시하고 있다.
이때 최소한의 비용으로 모든 집을 연결해야 하기 때문에 이를 통해 전형적인 최소 신장 트리 문제라는 것을 알 수 있다.
'임의의 두 집에 대하여 가로등이 켜진 도로만으도 오갈 수 있도록' 같은 문장이 있으면, 최소 신장 트리 문제라는 것을 알 수 있다.
'왕래'할 수 있다는것은 그래프에서 각 노드가 서로 연결되어 있다는 의미(연결 그래프)와 같다.
따라서 주어진 입력을 통해서 그래프를 구성한 뒤에 크루스칼 알고리즘을 수행하면 된다.

 */