package 그리디;

import java.util.Scanner;

public class 큰수의법칙 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrayLength = scanner.nextInt();
        int plusTimes = scanner.nextInt();
        int trigger = scanner.nextInt();
        int[] array = new int[arrayLength];

        for (int i = 0; i < arrayLength; i++) {
            array[i] = scanner.nextInt();
        }

        int max1 = 0;
        int max2 = 0;
        int maxIndex = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max1) {
                max1 = array[i];
                maxIndex = i;
            }
        }

        array[maxIndex] = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] > max2) {
                max2 = array[i];
            }
        }

        int answer = 0;
        for (int i = 1; i <= plusTimes; i++) {
            if (i % (trigger + 1) == 0) {
                answer += max2;
            } else {
                answer += max1;
            }
        }

        System.out.println(answer);
    }
}
