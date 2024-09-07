package 지연.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.182 두 배열의 원소 교체 / 난이도 하
 */
public class 정렬_두배열의원소교체 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Integer[] arr1 = new Integer[n];
        Integer[] arr2 = new Integer[n];

        // arr1 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        // arr2 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);  // 순차 정렬
        Arrays.sort(arr2, Collections.reverseOrder());  // 역순 정렬

        for (int i = 0; i < k; i++) {
            // arr1의 값이 더 작을 때 교환
            if (arr1[i] < arr2[i]) {
                int temp = arr1[i];
                arr1[i] = arr2[i];
                arr2[i] = temp;
            }
        }

        int sum = 0;
        for (int i = 0; i < arr1.length; i++) {
            sum += arr1[i];
        }

        System.out.println(sum);
    }
}
