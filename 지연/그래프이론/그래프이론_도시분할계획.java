package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.300 도시 분할 계획 / 난이도 중
 * 백준 골드 4 https://www.acmicpc.net/problem/1647
 */
public class 그래프이론_도시분할계획 {

    public static int n, m;
    public static int[] parent = new int[100001];
    public static ArrayList<Edge> edges = new ArrayList<>();
    public static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 부모를 자기 자신으로 초기화
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        // 모든 간선 정보 입력 받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(cost, a, b));
        }

        Collections.sort(edges);
        int last = 0; // 최소 신장 트리에 포함되는 간선 중에서 가장 비용이 큰 간선

        // 간선 하나씩 확인
        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).getDistance();
            int a = edges.get(i).getNodeA();
            int b = edges.get(i).getNodeB();

            // 사이클 발생하지 않는 경우에만 집합 포함
            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += cost;
                last = cost;
            }
        }

        System.out.println(result - last);
    }

    /**
     * 두 원소가 속한 집합 합치기
     * @param a
     * @param b
     */
    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    /**
     * 특정 원소가 속한 집합 찾기
     * @param x
     * @return
     */
    private static int findParent(int x) {
        // 루트 노드가 아니면 루트 노드 찾을 때까지 재귀 호출
        if (x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    public static class Edge implements Comparable<Edge> {
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

        // 거리가 짧은 것이 높은 우선순위
        @Override
        public int compareTo(Edge o) {
            if(this.distance < o.distance) return -1;
            else if(this.distance == o.distance) return 0;
            else return 1;
        }
    }
}

/*
이 문제는 전체 그래프에서 2개의 최소 신장 트리를 만들어야 하기 때문에
크루스칼 알고리즘으로 최소 신장 트리를 찾은 뒤에 최소 신장 트리를 구성하는 간선 중에서
가장 비용이 큰 간선을 제거해야 한다.
그러면 최소 신장 트리가 2개의 부분 그래프로 나누어지며, 문제에서 요구하는 최적의 해를 만족한다.
따라서 최소 신장 트리를 찾은 뒤에 가장 큰 간선을 제거해야한다.

 */
