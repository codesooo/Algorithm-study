package 지연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준
 * 1439 뒤집기 / 난이도 실버 하
 *
 * 다솜이는 0과 1로만 이루어진 문자열 S를 가지고 있다. 다솜이는 이 문자열 S에 있는 모든 숫자를 전부 같게 만들려고 한다. 다솜이가 할 수 있는 행동은 S에서 연속된 하나 이상의 숫자를 잡고 모두 뒤집는 것이다. 뒤집는 것은 1을 0으로, 0을 1로 바꾸는 것을 의미한다.
 * 예를 들어 S=0001100 일 때,
 * 전체를 뒤집으면 1110011이 된다.
 * 4번째 문자부터 5번째 문자까지 뒤집으면 1111111이 되어서 2번 만에 모두 같은 숫자로 만들 수 있다.
 * 하지만, 처음부터 4번째 문자부터 5번째 문자까지 문자를 뒤집으면 한 번에 0000000이 되어서 1번 만에 모두 같은 숫자로 만들 수 있다.
 * 문자열 S가 주어졌을 때, 다솜이가 해야하는 행동의 최소 횟수를 출력하시오.
 */
public class 그리디_뒤집기_1439 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        StringTokenizer st0 = new StringTokenizer(s, "0");  // 0기준으로 토큰 생성
        StringTokenizer st1 = new StringTokenizer(s, "1");  // 1기준으로 토큰 생성

        // 0기준의 토큰 개수와 1기준의 토큰 개수를 비교하여 작은 것 출력
        System.out.println(Math.min(st0.countTokens(), st1.countTokens()));
    }
}

/*
이 문제는 그리디 알고리즘으로 해결할 수 있다.
핵심은 문자열을 뒤집을 때 모든 연속된 0의 문자를 1로 바꾸거나 연속된 1의 문자를 0으로 바꾼 횟수가 적은 것을 찾으면 된다.
1. StringTokenizer에 0과 1을 매개변수로 넣어 연속된 해당 문자의 개수를 구한다.
2. 0과 1의 문자의 개수 중 적은 것을 출력
 */