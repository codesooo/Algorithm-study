package 지연.최단경로;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.390 숨바꼭질 / 난이도 중
 */
public class 최단경로_숨바꼭질 {
    static final int INF = (int) 1e9;
    // 노드의 개수(N), 간선의 개수(M)
    public static int n, m;
    // 시작 노드를 1번 헛간으로 설정
    public static int start = 1;
    // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 리스트를 만들기
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    // 최단 거리 테이블 만들기
    public static int[] d = new int[20001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        // 그래프 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
        }

        // 모든 간선 정보를 입력받기
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            // a번 노드와 b번 노드의 이동 비용이 1이라는 의미(양방향)
            graph.get(a).add(new Node(b, 1));
            graph.get(b).add(new Node(a, 1));
        }

        // 최단 거리 테이블을 모두 무한으로 초기화
        Arrays.fill(d, INF);

        // 다익스트라 알고리즘을 수행
        dijkstra(start);

        // 가장 최단 거리가 먼 노드 번호(동빈이가 숨을 헛간의 번호)
        int maxNode = 0;
        // 도달할 수 있는 노드 중에서, 가장 최단 거리가 먼 노드와의 최단 거리
        int maxDistance = 0;
        // 가장 최단 거리가 먼 노드와의 최단 거리와 동일한 최단 거리를 가지는 노드들의 리스트
        ArrayList<Integer> result = new ArrayList<Integer>();

        for (int i = 1; i <= n; i++) {
            if (maxDistance < d[i]) {
                maxNode = i;
                maxDistance = d[i];
                result.clear();
                result.add(maxNode);
            }
            else if (maxDistance == d[i]) {
                result.add(i);
            }
        }

        System.out.println(maxNode + " " + maxDistance + " " + result.size());
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] d = new int[20001];

        // 시작 노드 최단 경로 0으로 설정
        queue.offer(new Node(start, 0));
        d[start] = 0;

        while (!queue.isEmpty()) {
            // 가장 최단 거리가 짧은 노드 정보 꺼내기
            Node node = queue.poll();
            // 현재 노드까지의 비용
            int dist = node.getDistance();
            // 현재 노드
            int now = node.getIndex();
            // 현재 노드가 이미 처리된 적 있는 노드라면 무시
            if (dist > d[now]) continue;
            // 현재 노드와 연결된 다른 인접한 노드들 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = dist + graph.get(now).get(i).getDistance();
                // 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[graph.get(now).get(i).getIndex()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    queue.offer(new Node(graph.get(now).get(i).getIndex(), cost));
                }
            }

        }
    }

    private static class Node implements Comparable<Node>{
        private int index;
        private int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }
        public int getIndex() {
            return index;
        }
        public int getDistance() {
            return distance;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
}

/*
이 문제는 다익스트라 알고리즘을 이용하여 1번노드로부터
다른 모든 노드로의 최단 거리를 계산한 뒤,
가장 최단 거리가 긴 노드를 찾는 문제이다.
 */