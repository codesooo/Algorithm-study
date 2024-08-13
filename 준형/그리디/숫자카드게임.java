package 그리디;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class 숫자카드게임 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        int[][] boards = new int[N][M];


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                boards[i][j] = scanner.nextInt();
            }
        }

        int max = 0;
        for (int[] board : boards) {
            OptionalInt value = Arrays.stream(board).min();
            if (value.isEmpty()) {
                throw new RuntimeException();
            }

            int min = value.getAsInt();
            if (min > max) {
                max = min;
            }
        }

        System.out.println(max);
    }
}
