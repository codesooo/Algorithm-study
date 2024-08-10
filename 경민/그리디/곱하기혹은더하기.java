package 그리디;

import java.util.Scanner;

public class 곱하기혹은더하기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();

        // 첫 번째 문자를 숫자로 변환하여 초기 결과로 설정
        long result = Character.getNumericValue(string.charAt(0));

        for (int i = 1; i < string.length(); i++) {
            int num = Character.getNumericValue(string.charAt(i));

            // 숫자가 0 또는 1이거나, 결과가 0 또는 1인 경우 더하기
            if (num <= 1 || result <= 1) {
                result += num;
            } else {
                result *= num;
            }
        }

        System.out.println(result);




    }
}
