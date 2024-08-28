package 지연.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.312 곱하기 혹은 더하기 / 난이도 하
 */
public class 그리디_곱하기혹은더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int result = s.charAt(0) - '0'; // 첫번째 값 저장
        for (int i = 1; i < s.length(); i++) {
            // 두번째 수나 결과값이 0이거나 1일 때는 더하기. 그 외는 곱하기
            if (s.charAt(i) - '0' <= 1 || result <= 1) {
                result += s.charAt(i) - '0';
            } else {
                result *= s.charAt(i) - '0';
            }
        }
        System.out.println(result);
    }
}

/*
이 문제는 그리디 알고리즘으로 해결할 수 있다.
핵심은 최대한 곱하기를 해야지 수의 크기가 커지는 것이다.
 따라서 0과 1일 때는 더하기를 해주고 그 외에는 곱하기를 해주면 된다.
 */