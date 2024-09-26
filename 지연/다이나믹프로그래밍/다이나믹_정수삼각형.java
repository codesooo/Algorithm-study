package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.376 정수 삼각형 / 난이도 중
 */
public class 다이나믹_정수삼각형 {
    public static void main(String[] args) throws IOException {

        /*
        5
        7
        3 8
        8 1 0
        2 7 4 4
        4 5 2 6 5
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());

            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int upLeft, up;
                // 왼쪽 위
                if(j == 0) upLeft = 0;
                else upLeft = dp[i - 1][j - 1];
                // 바로 위
                if(j == i) up = 0;
                else up = dp[i - 1][j];

                // 최대 합 저장
                dp[i][j] = dp[i][j] + Math.max(upLeft, up);
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, dp[n - 1][i]);
        }
        System.out.println(result);
    }
}

/*
보텀업 방식의 다이나믹 프로그래밍으로 해결할 수 있다.
왼쪽 위, 바로 위 2가지 위치를 기준으로 최적의 합 중 큰 값을 선택하면 된다.
 */