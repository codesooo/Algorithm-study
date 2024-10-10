package 지연.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.388 화성 탐사 / 난이도 중
 */
public class 최단경로_화성탐사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        final int INF = (int) 1e9;
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        // 테스트 케이스
        for (int i = 0; i < t; i++) {
            // 노드 개수
            int n = Integer.parseInt(br.readLine());
            int[][] graph = new int[n][n];
            int[][] d = new int[n][n];
            // 맵 정보 입력받기
            for (int j = 0; j < n; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    graph[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            // 최단 거리 테이블 모두 무한으로 초기화
            for (int j = 0; j < n; j++) {
                Arrays.fill(d[j], INF);
            }

            // 시작위치 (0,0)
            int x = 0, y = 0;
            PriorityQueue<Node> queue = new PriorityQueue<>();
            queue.offer(new Node(0, 0, graph[x][y]));
            d[x][y] = graph[x][y];

            // 다익스트라
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                x = node.getX();
                y = node.getY();
                int dist = node.getDistance();

                // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
                if (d[x][y] < dist)  continue;

                for (int j = 0; j < 4; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    // 맵의 범위를 벗어나는 경우 무시
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    int cost = dist + graph[nx][ny];
                    // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                    if (cost < d[nx][ny]) {
                        d[nx][ny] = cost;
                        queue.offer(new Node(nx, ny, cost));
                    }
                }
            }
            System.out.println(d[n - 1][n - 1]);
        }

    }

    static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
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
이 문제는 최단거리를 계산하는 문제이다.
A와 위치 B가 서로 인접해 있다고 할 때, A -> B로 가는 비용은 B위치의 탐사비용이고 B -> A로 가는 비용은 A 위치의 탐사비용이 도니다.

플로이드 워셜 알고리즘으로는 2차원 배열을 이용하기 때문에 적합하지 않다.
따라서 다익스트라 최단 경로 알고리즘을 이용해야 한다.
 */