package 지연.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.315 볼링공 고르기 / 난이도 하
 */
public class 그리디_볼링공고르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[11];    // 1~10까지의 무게

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[Integer.parseInt(st.nextToken())]++;
        }

        int result = 0;
        for (int i = 1; i <= m; i++) {
            n -= arr[i];    // 전체 개수 중 중복 제거
            result += arr[i] * n;   // 선택할 수 있는 경우의 수 * 동일한 무게의 볼링공의 수
        }

        System.out.println(result);
    }
}

/*
이 문제는 그리디 알고리즘을 이용하여 해결 할 수 있다.

1. 무게마다 볼링공이 몇 개 있는지 계산한다.
무게가 1인 볼링공: 1개
무게가 2인 볼링공: 2개
무게가 3인 볼링공: 2개
2. A를 기준으로 무게가 낮은 볼링공부터 무게가 높은 볼링공까지 순서대로 하나씩 선택한다.
3. A가 특정한 무게의 볼링공을 선택하는 경우의 수와 B가 A와 다른 무게의 볼링공을 선택하는 경우의 수를 확인한다.
4. B가 볼링공을 선택할 때 이미 계산 했던 경우는 제외한다.

예)
step1> A가 무게 1인 볼링공을 선택할 때 경우의 수
1 (무게가 1인 공의 개수) × 4 (B가 선택하는 경우의 수 : 무게가 2인 볼링공 + 무게가 3인 볼링공) = 4
고려된 무게 조합: (1, 2), (1,3)

step2> A가 무게 2인 볼링공을 선택할 때 경우의 수
2 (무게가 2인 공의 개수) × 2 (B가 선택하는 경우의 수 : 무게가 3인 볼링공) = 4
여기서 무게가 (1,2) 인 조합은 이미 step1에서 계산했기 때문에, B는 무게가 1인 볼링공을 제외시킨다.
고려된 무게 조합: (2, 3)

step3> A가 무게 3인 볼링공을 선택할 때 경우의 수
2 (무게가 3인 공의 개수) × 0 (B가 선택하는 경우의 수) = 0
이전 단계에서 모든 무게 조합이 고려됬기 때문에 경우의 수는 0이 된다.
 */