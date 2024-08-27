import java.io.*;
import java.util.*;

public class dfsbfs_미로탈출 {
    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(map[N - 1][M - 1]);
    }

    private static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0]; y = now[1];
            visited[x][y] = true;

            for (int p = 0; p < dx.length; p++) {
                int nx = dx[p] + now[0];
                int ny = dy[p] + now[1];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 0) {
                    continue;
                }
                if (map[nx][ny]==1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    map[nx][ny] = map[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }


            }
        }
    }
}


//5 6
//        101010
//        111111
//        000001
//        111111
//        111111
