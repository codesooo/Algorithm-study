package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.375 금광 / 난이도 중
 */
public class 다이나믹_금광 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 개수

        StringTokenizer st;
        for (int k = 0; k < t; k++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken()); // 행
            int m = Integer.parseInt(st.nextToken()); // 열

            int[][] arr = new int[n][m];
            int[][] dp = new int[n][m];
            st = new StringTokenizer(br.readLine());
            // 금광정보
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j] = arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 다이나믹 프로그래밍 진행
            for (int j = 1; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    int leftUp, leftDown, left;
                    // 왼쪽 위에서 오는 경우
                    if(i == 0) leftUp = 0;
                    else leftUp = dp[i - 1][j - 1];

                    // 왼쪽 아래에서 오는 경우
                    if(i == n - 1) leftDown = 0;
                    else leftDown = dp[i + 1][j - 1];
                    // 왼쪽에서 오는 경우
                    left = dp[i][j - 1];
                    dp[i][j] = dp[i][j] + Math.max(leftUp, Math.max(leftDown, left));
                }
            }
            int result = 0;
            for (int i = 0; i < n; i++) {
                result = Math.max(result, dp[i][m - 1]);
            }
            System.out.println(result);
        }
    }
}

/*
보텀 업 방식으로 다이나믹 프로그래밍을 푸는 문제이다.
왼쪽열부터 시작해서 각 방향에서의 최대값을 구하여 dp 테이블을 채워 나가면 된다.
 */