package 지연.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.353 인구 이동 / 난이도 중
 * 백준 골드4 https://www.acmicpc.net/problem/16234
 */
public class DFS_BFS_인구이동 {
    private static boolean[][] visited;
    private static int n, l, r;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 크기
        l = Integer.parseInt(st.nextToken()); // 인구차이 L
        r = Integer.parseInt(st.nextToken()); // 인구차이 R

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            visited = new boolean[n][n];
            boolean isMoved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        List<int[]> union = bfs(i, j);
                        if (union.size() > 1) {
                            isMoved = true;
                            int totalPerson = 0;
                            for (int[] pos : union) {
                                totalPerson += arr[pos[0]][pos[1]];
                            }
                            int newPerson = totalPerson / union.size();
                            for (int[] pos : union) {
                                arr[pos[0]][pos[1]] = newPerson;
                            }
                        }
                    }
                }
            }
            if(!isMoved) break;
            day++;
        }
        System.out.println(day);
    }

    private static List<int[]> bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> union = new ArrayList<>();

        queue.add(new int[]{x, y});
        union.add(new int[]{x, y});

        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
                    // 인구 수 차이
                    int diff = Math.abs(arr[cx][cy] - arr[nx][ny]);
                    if (l <= diff && diff <= r) {
                        queue.add(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }

        }
        return union;
    }

}
/*
이 문제는 BFS 알고리즘을 사용하여 풀 수 있다.
1. bfs를 사용하여 연합을 찾는다
2. 인구이동을 시뮬레이션 한다.
3. 각 칸의 인구 수를 갱신한다.
4. 더 이상 인구 이동이 발생하지 않을떄까지 반복한다.
 */