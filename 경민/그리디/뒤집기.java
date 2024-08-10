package 그리디;

import java.util.Scanner;

public class 뒤집기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        int count0 = 0; // 전부 0으로 바꾸는 경우
        int count1 = 0; // 전부 1로 바꾸는 경우

        // 첫 번째 문자에 따라 초기 카운트 설정
        if (str.charAt(0) == '0') {
            count1++;
        } else {
            count0++;
        }

        // 문자열을 순회하며 연속된 다른 숫자가 나올 때마다 카운트 증가
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i - 1)) {
                if (str.charAt(i) == '0') {
                    count1++;
                } else {
                    count0++;
                }
            }
        }

        // 두 카운트 중 최소값 출력
        System.out.println(Math.min(count0, count1));
    }
}
