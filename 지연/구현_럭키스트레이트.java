package 지연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.321 럭키 스트레이트 / 난이도 하
 *
 * 백준 https://www.acmicpc.net/problem/18406
 * 브론즈 2
 */
public class 구현_럭키스트레이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int len = s.length() / 2;
        String s1 = s.substring(0, len);
        String s2 = s.substring(len);

        int s1Sum = 0;
        int s2Sum = 0;
        for (int i = 0; i < len; i++) {
            s1Sum += s1.charAt(i) - '0';
            s2Sum += s2.charAt(i) - '0';
        }

        String result = "READY";
        if(s1Sum == s2Sum) result = "LUCKY";

        System.out.println(result);

    }

    /*

        public static String str;
    public static int summary = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        str = sc.next();

        // 왼쪽 부분의 자릿수의 합 더하기
        for (int i = 0; i < str.length() / 2; i++) {
            summary += str.charAt(i) - '0';
        }

        // 오른쪽 부분의 자릿수의 합 빼기
        for (int i = str.length() / 2; i < str.length(); i++) {
            summary -= str.charAt(i) - '0';
        }

        // 왼쪽 부분과 오른쪽 부분의 자릿수 합이 동일한지 검사
        if (summary == 0) System.out.println("LUCKY");
        else System.out.println("READY");
    }
     */
}
