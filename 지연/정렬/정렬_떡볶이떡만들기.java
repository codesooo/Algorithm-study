package 지연.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.201 떡볶이 떡 만들기 / 난이도 중
 */
public class 정렬_떡볶이떡만들기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 떡 개수
        int m = Integer.parseInt(st.nextToken()); // 떡 길이

        st = new StringTokenizer(br.readLine());
        // 떡 높이
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = (int) 1e9; // 이진탐색 끝점

        int result = 0;
        // 이진탐색 - 반복문 방식
        while (start <= end) {
            long total = 0;
            int mid = (start + end) / 2;
            for (int i = 0; i < n; i++) {
                // 잘랐을 때 떡의 양 계산
                if (arr[i] > mid) {
                    total += arr[i] - mid;
                }
            }
            // 떡 양 부족할 때 왼쪽 부분 탐색
            if (total < m) {
                end = mid - 1;
            } else { // 떡 양 많을 때 오른쪽 부분 탐색
                result = mid;
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}

/*
이 문제는 이진 탐색을 이용하여 절단기의 높이를 점자 증가 또는 감소 시켜 적절한 높이를 찾는 문제이다.
떡의 양이 부족할 때 왼쪽 부분을 탐색 하고, 떡의 양이 많을 때 오른쪽 부분 탐색을 하면 된다.
 */