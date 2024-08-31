import java.util.*;
import java.io.*;

public class dfsbfs_연구소 {
    private static int N, M;
    private static int[][] originMap;
    private static int[][] wallMap;
    private static int res = 0;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        input();

        dfs(0);

        System.out.println(res);
    }

    private static void dfs(int cnt) {
        if (cnt == 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    wallMap[i][j] = originMap[i][j];
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (wallMap[i][j] == 2) {
                        virus(i, j);
                    }
                }
            }

            res = Math.max(res, getScore());
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (originMap[i][j] == 0) {
                    originMap[i][j] = 1;
                    cnt += 1;
                    dfs(cnt);
                    originMap[i][j] = 0;
                    cnt -= 1;
                }
            }
        }
    }

    private static void virus(int x, int y) {
        for (int p = 0; p < dx.length; p++) {
            int nx = x + dx[p];
            int ny = y + dy[p];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue;
            }

            if (wallMap[nx][ny] == 0) {
                wallMap[nx][ny] = 2;
                virus(nx,ny);
            }


        }
    }

    private static void bfs(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int p = 0; p < dx.length; p++) {
                int nx = now[0] + dx[p];
                int ny = now[1] + dy[p];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (!visited[nx][ny] && wallMap[nx][ny] == 0) {
                    queue.offer(new int[]{nx, ny});
                    wallMap[nx][ny] = 2;
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        originMap = new int[N][M];
        wallMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                originMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int getScore() {
        int cnt_virus = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (wallMap[i][j] == 0) {
                    cnt_virus++;
                }
            }
        }
        return cnt_virus;
    }
}

