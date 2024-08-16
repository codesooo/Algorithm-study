package 구현;

import java.util.Scanner;

public class 럭키스트레이트 {
    public static String string;
    public static int left = 0;
    public static int right = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        string = sc.next();

        // 왼쪽 부분의 자릿수의 합 더하기
        for (int i = 0; i < string.length() / 2; i++) {
            left += string.charAt(i) - '0';
        }

        // 오른쪽 부분의 자릿수의 합 더하기
        for (int i = string.length() / 2; i < string.length(); i++) {
            right += string.charAt(i) - '0';
        }

        // 왼쪽 부분과 오른쪽 부분의 자릿수 합이 동일한지 검사
        if (right == left) System.out.println("LUCKY");
        else System.out.println("READY");
    }
}
