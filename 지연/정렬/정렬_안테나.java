package 지연.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.360 안테나 / 난이도 하
 * 백준 실버3 https://www.acmicpc.net/problem/18310
 */
public class 정렬_안테나 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        System.out.println(arr[(arr.length - 1) / 2]);
    }
}
/*
해당 문제는 정렬 문제이다.
가운데에 위치한 집에 안테나를 설치했을 때 모든 집과의 거리가 제일 적게 나온다.
따라서 정렬을 수행 후 가운뎃값을 출력한다.
 */
