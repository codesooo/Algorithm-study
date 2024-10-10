package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.382 편집 거리 / 난이도 중
 */
public class 다이나믹_편집거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];
        // dp 테이블 초기화. 문자의 최소 편집거리 셋팅
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 문자열이 같다면 왼쪽 위의 편집거리 그대로 입력
                if(str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 문자열 다르면 삽입(왼쪽), 삭제(위), 교체(왼쪽 위) 중 최소비용 대입 후 + 1
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        System.out.println(dp[n - 1][m - 1]);

    }
}

/*
이 문제는 최소 편집 거리를 담을 2차원 테이블을 초기화 한 뒤에 최소편집거리를 계산하여 테이블에 저장하는 과정으로 다이나믹 프로그래밍 방식으로 풀 수 있다.
1. 두 문자가 같은 경우 dp[i][j] = dp[i - 1][j - 1]
    - 행과 열에 해당하는 문자가 서로 같아면, 왼쪽 위에 해당하는 수를 그대로 대입
2. 두 문자가 다른 경우 dp[i][j] = 1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
    - 행과 열에 해당하는 문자가 서로 다르다면, 삽입(왼쪽), 삭제(위), 교체(왼쪽 위) 중 최소비용 대입 후 + 1

 */