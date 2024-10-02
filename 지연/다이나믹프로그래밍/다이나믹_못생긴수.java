package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.381 못생긴 수 / 난이도 중
 */
public class 다이나믹_못생긴수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        arr[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        // 처음 값 초기화
        int next2 = 2, next3 = 3, next5 = 5;

        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(next2, Math.min(next3, next5));
            if (arr[i] == next2) {
                i2++;
                next2 = arr[i2] * 2;
            }
            if (arr[i] == next3) {
                i3++;
                next3 = arr[i3] * 3;
            }
            if (arr[i] == next5) {
                i5++;
                next5 = arr[i5] * 5;
            }
        }
        System.out.println(arr[n - 1]);

    }
}

/*
이 문제는 못생긴 수를 앞에서부터 하니씩 찾는 방식의 다이나믹 프로그래밍 방식으로 풀 수 있다.
2의 배수, 3의 배수, 5의 배수 변수에 대하여 가장 못생긴 수부터 오름차순으로 하나씩 확인하면서
각, 배수를 곱한 값도 못생긴 수가 될 수 있도록 처리하면 된다.

 */