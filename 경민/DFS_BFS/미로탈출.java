package DFS_BFS;

import java.util.*;

public class 미로탈출 {
    public static int n, m; // 미로의 크기: n x m
    public static int[][] graph = new int[201][201]; // 미로 정보를 담을 2차원 배열 (최대 200x200 크기)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력 받기 (미로의 세로(n)와 가로(m) 크기)
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine(); // 버퍼 지우기

        // 2차원 배열의 맵 정보 입력 받기
        for (int i = 0; i < n; i++) { // 각 행에 대해 반복
            String str = sc.nextLine();
            for (int j = 0; j < m; j++) { // 각 열에 대해 반복
                // 문자로 입력받은 값을 정수로 변환하여 2차원 배열에 저장
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        // BFS를 수행한 결과 출력 (0, 0에서 시작하여 n-1, m-1까지의 최단 경로)
        System.out.println(bfs(0, 0));
    }

    // 이동할 네 가지 방향 정의 (상, 하, 좌, 우)
    // dx와 dy 배열을 사용하여 네 방향으로 이동할 때의 좌표 변화를 나타냄
    public static int dx[] = {-1, 1, 0, 0}; // 행 방향: 위로 한 칸(-1), 아래로 한 칸(1), 좌우 이동은 없음(0)
    public static int dy[] = {0, 0, -1, 1}; // 열 방향: 좌우 이동(좌(-1), 우(1)), 상하 이동은 없음(0)

    // BFS(너비 우선 탐색) 알고리즘을 통해 최단 경로를 찾는 메서드
    public static int bfs(int x, int y) {
        // 큐(Queue) 구현을 위해 LinkedList 사용
        // BFS에서 사용하는 큐에 시작 위치를 좌표로 만들어 추가 (0,0 위치)
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y}); // 시작 위치를 큐에 삽입

        // 큐가 빌 때까지 반복하기
        while (!q.isEmpty()) {
            int[] current = q.poll(); // 큐에서 현재 위치를 꺼냄
            x = current[0];
            y = current[1];

            // 현재 위치에서 4가지 방향으로의 위치 확인
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; // 다음 이동할 x 좌표
                int ny = y + dy[i]; // 다음 이동할 y 좌표

                // 미로의 범위를 벗어난 경우 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                // 괴물이 있는 벽인 경우 무시 (값이 0인 경우)
                if (graph[nx][ny] == 0) continue;

                // 해당 위치를 처음 방문하는 경우에만 최단 거리 기록
                // 아직 방문하지 않은 위치(값이 1인 위치)
                if (graph[nx][ny] == 1) {
                    // 현재 위치의 값에 1을 더하여 해당 위치의 값으로 설정 (최단 경로 계산)
                    graph[nx][ny] = graph[x][y] + 1;
                    // 큐에 다음 위치를 추가하여 계속 탐색
                    q.offer(new int[]{nx, ny}); // 다음 위치를 큐에 삽입
                }
            }
        }
        // BFS 탐색이 완료되면, 가장 오른쪽 아래 (n-1, m-1)까지의 최단 거리 반환
        return graph[n - 1][m - 1];
    }

}