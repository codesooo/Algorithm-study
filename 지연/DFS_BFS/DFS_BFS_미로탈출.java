package 지연.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.152 미로 탈출 / 난이도 중
 */
public class DFS_BFS_미로탈출 {
    static int n, m;
    static int[][] graph;
    // 상하좌우
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));

    }

    private static int bfs(int x, int y) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            x = node.getX();
            y = node.getY();

            // 4가지 방향 확인
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 벗어났을 경우 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                // 벽인경우 무시
                if (graph[nx][ny] == 0) {
                    continue;
                }
                // 해당 노드 처음방문할 경우
                if (graph[nx][ny] == 1) {
                    graph[nx][ny] = graph[x][y] + 1;
                    queue.offer(new Node(nx, ny));
                }
            }
        }
        // 가장 오른쪽 아래까지의 최단 거리 반환
        return graph[n - 1][m - 1];
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

/*
이 문제는 BFS를 이용하여 해결할 수 있다.
보통 최단 경로 문제는 BFS이다
시작 지점에서 가까운 노드부터 차례대로 그래프의 모든 노드를 탐색하기 때문이다.
특정한 노드를 방문하면 이전 노드의 거리에 1을 더한 값을 넣어주어
최종 목적지에 도달했을 때의 값을 출력한다.
 */