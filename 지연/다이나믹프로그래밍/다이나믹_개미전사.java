package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.220 개미 전사 / 난이도 중
 */
public class 다이나믹_개미전사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] d = new int[100];
        d[0] = arr[0];
        d[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            d[i] = Math.max(d[i - 1], d[i - 2] + arr[i]);
        }

        System.out.println(d[n - 1]);
    }
}
/*
바텀업 방식의 다이나믹 프로그래밍 방식으로 풀 수 있다.
차례대로 최대값의 식량을 저장하여 이전 값의 식량값과 현재 식량 값이 큰 것을 선택하면 된다.

 */