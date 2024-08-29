package 지연.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.92 큰 수의 법칙 / 난이도 하
 */
public class 그리디_큰수의법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 배열의 크기
        int M = Integer.parseInt(st.nextToken());   // 숫자가 더해지는 횟수
        int K = Integer.parseInt(st.nextToken());   // 반복가능 한 횟수

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum = 0;    // 더해진 값
        int cnt = 0;    // 더한 횟수

        int maxValue = arr[N - 1];  // 가장 큰 값
        int nextValue = arr[N - 2]; // 두번째로 큰 값

        while (cnt < M){
            sum += maxValue * K;    // 가장 큰 값을 반복 가능한 횟수 만큼 곱해주기
            cnt += K;   // 반복한 횟수 추가
            sum += nextValue;   // 두번째로 큰 값 한번 더해주기
            cnt++;  // 두번째로 큰 값은 한번만 더해주면 됨
        }

        System.out.println(sum);
    }

    /*
        // 정답 코드
        Scanner sc = new Scanner(System.in);

        // N, M, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // N개의 수를 공백을 기준으로 구분하여 입력 받기
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr); // 입력 받은 수들 정렬하기
        int first = arr[n - 1]; // 가장 큰 수
        int second = arr[n - 2]; // 두 번째로 큰 수

        // 가장 큰 수가 더해지는 횟수 계산
        int cnt = (m / (k + 1)) * k;
        cnt += m % (k + 1);

        int result = 0;
        result += cnt * first; // 가장 큰 수 더하기
        result += (m - cnt) * second; // 두 번째로 큰 수 더하기

        System.out.println(result);

        // 가장 큰 수가 더해지는 수
        // int(M / (K_1)) * K + M % (K + 1)

     */
}

/*
이 문제는 그리디 알고리즘을 사용한 문제이다.
1. 가장 큰 값과 두번째 큰 값만 이용하여 풀이를 하면 된다.
2. 가장 큰 값을 K만큼 곱하여 더해주고 두번째 큰 값을 한번 더해준다.
3. 더할 수 있는 횟수만큼 반복해준다.
 */