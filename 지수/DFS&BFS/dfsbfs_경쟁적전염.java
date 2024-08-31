import java.io.*;
import java.util.*;
public class dfsbfs_경쟁적전염 {

    private static int N, K, S, X, Y;
    private static int[][] map;
    private static boolean[][] visited;
    private static Queue<int[]> queue = new LinkedList<>();

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        input();

        while (S-- > 0) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0 && !visited[i][j]) {
                        queue.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                }
            }
            virus();
        }

        System.out.println(map[X][Y]);
    }

    private static void virus() {
        while (!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.poll()[1];

            for (int p = 0; p < dx.length; p++) {
                int nx = dx[p] + x;
                int ny = dy[p] + y;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                    continue;
                }

                if (map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y];
                } else {
                    map[nx][ny] = Math.min(map[nx][ny], map[x][y]);
                }
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
    }

}
