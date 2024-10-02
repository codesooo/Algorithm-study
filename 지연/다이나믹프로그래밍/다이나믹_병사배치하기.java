package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.380 병사 배치하기 / 난이도 중
 * 백준 실버2 https://www.acmicpc.net/problem/18353
 */
public class 다이나믹_병사배치하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st =  new StringTokenizer(br.readLine());


        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        // 순서를 뒤집어 최장 증가 부분 수열 문제로 변환
        Collections.reverse(list);

        int[] dp = new int[n];

        // dp 테이블 초기화
        Arrays.fill(dp, 1);

        // 가장 긴 증가하는 부분 수열(LIS) 알고리즘 수행
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if(list.get(j) < list.get(i)){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // 열외해야 하는 병사의 최소 수 출력
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, dp[i]);
        }

        System.out.println(n - maxValue);
    }
}

/*

이 문제는 가장 긴 증가하는 부분 수열(LIS)의 다이나믹 프로그래밍으로 해결할 수 있다.
가장 긴 증가하는 부분 수열 문제랑 하나의 수열이 주어졌을 떄 값들이 증가하는 형태의 가장 긴 부분 수열을 찾는 문제이다.
예) array = {10, 20, 10, 30, 20, 50}일 떄, 가장 긴 증가하는 부분 수열은 {10, 20, 30, 50}이 된다.
D[i] = array[i]를 마지막 원소로 가지는 부분 수열의 최대길이라고 정의하면, 가장 긴 증가하는 부분 수열을 계산하는 점화식은
max(D[i], D[j] + 1) if array[j] < array[i] 가 된다.
최종적으로 테이블의 값은 [1, 2, 1, 3, 2, 4]이고, 이렇게 테이블에 남아있는 값 중에서 가장 큰 값은 4가 최장 길이가 된다

 */