package 지연.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.351 감시 피하기 / 난이도 중상
 * 백준 골드5 https://www.acmicpc.net/problem/18428
 */
public class DFS_BFS_감시피하기 {
    static String[][] arr;
    private static int n;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int count;
    static boolean found = false;
    private static List<int[]> teachers = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new String[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = st.nextToken();
                if (arr[i][j].equals("T")) {
                    teachers.add(new int[] {i, j});
                }
            }
        }

        dfs(0);

        System.out.println(found ? "YES" : "NO");

    }

    private static boolean check() {
        for (int[] teacher : teachers) {
            int x = teacher[0];
            int y = teacher[1];

            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;
                // 각 방향으로 직선 이동
                while (true) {
                    nx += dx[i];
                    ny += dy[i];

                    // 벽인경우
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) break;
                    // 장애물인경우
                    if (arr[nx][ny].equals("O")) break;
                    // 학생 만났을 경우
                    if (arr[nx][ny].equals("S")) return false;

                }
            }
        }
        return true;
    }

    private static void dfs(int count) {
        // 이미 찾은경우 리턴
        if(found) return;
        if (count == 3) {
            if (check()) {
                found = true;
            }
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j].equals("X")) {
                    arr[i][j] = "O";
                    dfs(count + 1);
                    arr[i][j] = "X";
                }
            }
        }
    }
}
