package 그리디;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 만들수없는금액 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int[] array = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        int[] sortedArray = Arrays.stream(array).sorted().toArray();

        int target = 1; // 초기값 설정
        for (int i = 0; i < N; i++) {
            if (target < sortedArray[i]) {
                break;
            }
            target = target + sortedArray[i];
        }
        System.out.println(target);
    }
}
