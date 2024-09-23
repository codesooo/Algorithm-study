package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.223 바닥 공사 / 난이도 중
 */
public class 다이나믹_바닥공사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] d = new int[1001];

        d[1] = 1; // 2×1 크기의 직사각형을 채우는 방법은 1가지
        d[2] = 3; // 2×2 크기의 직사각형을 채우는 방법은 3가지(1×2 타일 2개, 2×1 타일 2개, 2×2 타일 1개)
        for (int i = 3; i <= n; i++) {
            d[i] = (d[i - 1] + 2 * d[i - 2]) % 796796;
        }

        System.out.println(d[n]);
    }
}

/*
바텀업 방식을 이용한 다이나믹프로그래밍으로 풀 수 있다.
보통 다이나믹 프로그래밍 문제는 오버플로우 방지를 위해 특정 수를 나눈 나머지를 저장하라고 한다.

d[i - 1]: 2×(i-1) 크기의 직사각형을 채우는 방법의 수에 1×2 타일을 추가하는 경우.
2 * d[i - 2]: 2×(i-2) 크기의 직사각형을 채우는 방법의 수에 2×2 타일을 추가하는 경우와 1×2 타일 2개를 추가하는 경우.
 */