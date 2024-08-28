package 지연.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 실전문제
 * p.96 숫자 카드 게임 / 난이도 하
 */
public class 그리디_숫자카드게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int minValue = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[M];
            for (int j = 0; j < M; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            minValue = arr[0];  // 각 행의 최솟값 저장
            maxValue = maxValue < minValue ? minValue : maxValue;   // 각 행의 최솟값 중 최대값 저장
        }

        System.out.println(maxValue);
    }

    /*

        // 정답코드
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int result = 0;

        // 한 줄씩 입력 받아 확인하기
        for (int i = 0; i < n; i++) {
            // 현재 줄에서 '가장 작은 수' 찾기
            int min_value = 10001;
            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                min_value = Math.min(min_value, x);
            }
            // '가장 작은 수'들 중에서 가장 큰 수 찾기
            result = Math.max(result, min_value);
        }

        System.out.println(result); // 최종 답안 출력

     */
}

/*
이 문제는 그리디 알고리즘 문제로 해결할 수 있다.
각 행마다 가장 작은 수를 구하고 그 중에서 가장 큰 수를 찾는 문제이다.

1. 각 행마다 정렬을 하여 최솟값을 구한다.
2. 각 행의 최솟값 중 가장 큰 값을 구하여 출력한다.
 */