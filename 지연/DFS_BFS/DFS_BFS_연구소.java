package 지연.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.341 연구소 / 난이도 중
 * 백준 골드4 https://www.acmicpc.net/problem/14502
 */
public class DFS_BFS_연구소 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr; // 초기배열
    static int[][] temp; // 벽설치 후 배열
    static int n, m;
    static int result;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
          }
        dfs(0); // 울타리 설치
        System.out.println(result);
    }

    private static void dfs(int count) {
        temp = new int[n][m];
        if (count == 3) {
            // 배열 복사
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = arr[i][j];
                }
            }

            // 각 바이러스 위치에서 전파
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) {
                        virus(i, j);
                    }
                }
            }
            // 안전영역 최댓값 계산
            result = Math.max(result, safeArea());
            return;
        }
        // 빈 공간에 울타리 설치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = 1;
                    count++;
                    dfs(count);
                    arr[i][j] = 0;
                    count--;
                }

            }
        }
    }

    private static int safeArea() {
        // 안전영역 계산
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) {
                    num++;
                }
            }
        }
        return num;
    }

    // 바이러스 퍼뜨리기(DFS)
    private static void virus(int x, int y) {
        // 상하좌우 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            // 바이러스 퍼질 수 있는 곳 바이러스 퍼뜨리고 재귀 수행
            if (temp[nx][ny] == 0) {
                temp[nx][ny] = 2;
                virus(nx, ny);
            }
        }
    }
}

/*
이 문제는 두개의 dfs를 이용하여 풀 수 있다.
1. 빈 공간에 울타리를 설치하는 DFS
2. 울타리 설치가 3개가 됐을 때 바이러스 전파를 하여 안전영역 개수를 확인하는 DFS
 */