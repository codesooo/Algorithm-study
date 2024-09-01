package 지연.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.178 위에서 아래로 / 난이도 하
 */
public class 정렬_위에서아래로 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}
