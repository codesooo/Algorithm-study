package 그리디;

import java.util.Scanner;

public class 볼링공고르기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[] array = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (array[i] != array[j]) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
