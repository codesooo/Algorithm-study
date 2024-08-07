package 그리디;

import java.util.Scanner;

public class 숫자카드게임 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //숫자 n,m 입력받기
        int n = scanner.nextInt(); //행
        int m = scanner.nextInt(); //열

        int result = 0;

        // 한 줄씩 확인
        for (int i = 0; i < n; i++) {
            // 행별로 가장 작은 수 찾기
            int min = 1001;
            for (int j = 0; j < m; j++) {
                int x = scanner.nextInt();
                min = Math.min(min, x);
            }
            // 가장 작은 수 중에서 가장 큰 수 찾기
            result = Math.max(result, min);

        }
        System.out.println(result);
    }



}
