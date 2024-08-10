package 지연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.314 만들 수 없는 금액 / 난이도 하
 */
public class 그리디_만들수없는금액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int target = 1;
        // 화폐 단위가 작은 순서대로 동작을 확인하며, 현재 확인하는 동전을 이용해 target 금액 또한 만들 수 있는지 확인
        for (int i = 0; i < n; i++) {
            if(target < arr[i]) break;
            target += arr[i];
        }

        System.out.println(target);
    }
}

/*
이 문제는 그리디 알고리즘으로 해결할 수 있다.
핵심은 target 이하의 값은 모두 만들 수 있다고 정의하는 것이다.

예)
target = 1
→ 우리에게는 1원이 있다.
→ target 갱신 = 1 + 1 (1까지의 모든 금액을 만들 수 있다.)

target = 2
→ 우리에게는 2원이 있다.
→ target 갱신 = 2 + 2 (3까지의 모든 금액을 만들 수 있다.)

target = 4
→ 우리에게는 3원이 있다.
→ target 갱신 = 4 + 3 (6까지의 모든 금액을 만들 수 있다.)

target = 7
→ 7원보다 작은 동전들로는 6원까지밖에 못 만들며, 남은 건 7원보다 큰 8원밖에 없다. 그래서 1, 2, 3, 8원으로 만들 수 없는 금액의 최솟값은 7원이다.

 */