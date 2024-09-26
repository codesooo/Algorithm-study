package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.217 1로 만들기 / 난이도 중
 */
public class 다이나믹_1로만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] d = new int[30001];

        for (int i = 2; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i / 2] + 1);
            }
            if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }
            if( i % 5 == 0) {
                d[i] = Math.min(d[i], d[i / 5] + 1);
            }
        }
        System.out.println(d[n]);
    }
}

/*
바텀업 방식의 다이나믹 프로그래밍 방식으로 진행한다.
+1을 하는 이유는 함수 호출 횟수를 구하는 것이기 때문이다.
 */