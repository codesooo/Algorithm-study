package 다이나믹프로그래밍;

import java.util.*;

public class 효율적인화폐구성 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 화폐 종류 개수
        int M = scanner.nextInt(); // 화폐 가치의 합

        // N개의 화폐 단위 정보를 입력 받기
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }

        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] d = new int[M + 1];
        Arrays.fill(d, 10001);

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        d[0] = 0;
        for (int i = 0; i < N; i++) {
            for (int j = arr[i]; j <= M; j++) {
                // (i - k)원을 만드는 방법이 존재하는 경우
                if (d[j - arr[i]] != 10001) {
                    d[j] = Math.min(d[j], d[j - arr[i]] + 1);
                }
            }
        }

        if (d[M] == 10001) { // 최종적으로 M원을 만드는 방법이 없는 경우
            System.out.println(-1);
        }
        else {
            System.out.println(d[M]);
        }
    }
}