package 다이나믹프로그래밍;

import java.util.*;

public class 개미전사 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 식량창고 개수

        // 식량 정보 입력받기
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        int[] d = new int[100];

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        d[0] = array[0];
        d[1] = Math.max(array[0], array[1]);
        for (int i = 2; i < N; i++) {
            d[i] = Math.max(d[i - 1], d[i - 2] + array[i]);
        }

        System.out.println(d[N - 1]);
    }
}