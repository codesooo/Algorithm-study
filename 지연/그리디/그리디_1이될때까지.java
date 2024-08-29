package 지연.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 실전문제
 * p.99 1이 될 때까지 / 난이도 하
 */
public class 그리디_1이될때까지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int cnt = 0;
        while (N > 1) {
            if (N % K == 0) {
                N = N / K;
            }else {
                N = N - 1;
            }
            cnt++;
        }

        System.out.println(cnt);


        /*
        // 정답 소스
        Scanner sc = new Scanner(System.in);

        // N, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int k = sc.nextInt();
        int result = 0;

        while (true) {
            // N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
            int target = (n / k) * k;   // N 보다 작은 수 중에서, K의 배수이면서 가장 큰 수
            result += (n - target);
            n = target;
            // N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
            if (n < k) break;
            // K로 나누기
            result += 1;
            n /= k;
        }

        // 마지막으로 남은 수에 대하여 1씩 빼기
        result += (n - 1);  // N을 1로 만드는데 필요한 -1 연산 횟수
        System.out.println(result);
         */
    }

}

/*
이 문제는 그리디 알고리즘을 활용하는 문제이다.
주어진 N에 대하여 최대한 많이 나누기를 하면 된다.
1. N이 K로 나누어질 때 나누기
2. 나누어지지 않을 때 N - 1
 */