import java.io.*;
import java.util.*;

public class dfsbfs_음료수얼려먹기 {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        int cnt = 0;
        String str;
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int p = 0; p < dx.length; p++) {
                int nx = now[0] + dx[p];
                int ny = now[1] + dy[p];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }
                if (!visited[nx][ny] && map[nx][ny] == 0) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }

        }
    }
}
