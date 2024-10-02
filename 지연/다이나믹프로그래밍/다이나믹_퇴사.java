package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.377 퇴사 / 난이도 중
 * 백준 실버3 https://www.acmicpc.net/problem/14501
 */
public class 다이나믹_퇴사 {
    public static void main(String[] args) throws IOException {
        /*
        7
        3 10
        5 20
        1 10
        1 20
        2 15
        4 40
        2 200
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n + 1]; // 각 상담을 완료하는데 걸리는 시간
        int[] p = new int[n + 1]; // 각 상담을 완료했을 때 받을 수 있는 금액
        int[] dp = new int[n + 2]; // dp 테이블 초기화
        int maxValue = 0;

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // 뒤에서부터 확인
        for (int i = n - 1; i >= 0; i--) {
            int time = t[i] + i; // 현재 상담이 끝나는 시점 계산
            // 상담이 기간 안에 끝나는 경우
            if(time <= n) {
                // 현재 상담을 포함했을 때의 최대 이익과 현재까지의 최대 이익 중 큰 값 저장
                dp[i] = Math.max(p[i] + dp[time], maxValue);
                maxValue = dp[i];
            }
            // 상담이 기간 벗어나는 경우
            else dp[i] = maxValue;
        }

        System.out.println(maxValue);
    }
}
/*
뒤쪽 날짜부터 거꾸로 확인하는 방식의 다이나믹 프로그래밍으로 해결할 수 있다.
뒤쪽부터 매 상담에 대하여 현재 상담일자의 이윤 + 현재 상담을 마친 일자부터의 최대이윤을 계산하면 된다.
이후 계산된 각각의 값 중에서 최댓값을 출력한다.
 */
