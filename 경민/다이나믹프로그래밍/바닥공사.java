package 다이나믹프로그래밍;

import java.util.*;

public class 바닥공사 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 가로 길이

        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] d = new int[1001];

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        d[1] = 1;
        d[2] = 3;
        for (int i = 3; i <= N; i++) {
            d[i] = (d[i - 1] + 2 * d[i - 2]) % 796796;
        }

        System.out.println(d[N]);
    }
}
