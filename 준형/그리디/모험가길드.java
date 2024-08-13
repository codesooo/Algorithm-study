package 그리디;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 모험가길드 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Integer[] group = new Integer[N];
        for (int i = 0; i < N; i++) {
            group[i] = scanner.nextInt();
        }

        Arrays.sort(group, Collections.reverseOrder());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            i = group[i] + i - 1;
            answer++;
        }

        System.out.println(answer);
    }
}
