package 정렬;

import java.util.*;

public class 위에서아래로 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 수의 개수 N을 입력받음
        int n = scanner.nextInt();

        // N개의 숫자를 저장할 배열을 생성
        Integer[] numbers = new Integer[n];

        // N개의 숫자를 입력받아 배열에 저장
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        // 배열을 내림차순으로 정렬
        Arrays.sort(numbers, Collections.reverseOrder());

        // 결과 출력
        for (int i = 0; i < n; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
