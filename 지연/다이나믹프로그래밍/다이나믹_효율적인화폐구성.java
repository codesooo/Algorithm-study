package 지연.다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.226 효율적인 화폐 구성 / 난이도 중
 */
public class 다이나믹_효율적인화폐구성 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // N개의 화폐 단위 정보를 입력 받기
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] d = new int[m + 1];
        Arrays.fill(d, 10001);

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        d[0] = 0; // 0원을 만들기 위해 필요한 동전 개수는 0개
        for (int i = 0; i < n; i++) {
            // 각 화폐 단위를 하용하여 금액 j를 만들 수 있는지 확인
            for (int j = arr[i]; j <= m; j++) {
                // (i - k)원을 만드는 방법이 존재하는 경우
                if (d[j - arr[i]] != 10001) {
                    // 현재 금액 j를 만들기 위한 최소 동전 개수 갱신
                    d[j] = Math.min(d[j], d[j - arr[i]] + 1);
                }
            }
        }

        // 계산된 결과 출력
        if (d[m] == 10001) { // 최종적으로 M원을 만드는 방법이 없는 경우
            System.out.println(-1);
        }
        else {
            System.out.println(d[m]);
        }
    }
}

/*
주어진 여러 화폐 단위로 목표 금액을 만들기 위해 필요한 최소 동전 개수를 구하는 문제이다.
보텀업의 다이나믹 프로그래밍 방식으로 풀 수 있다.

- DP 테이블을 10001로 초기화하여 불가능한 경우를 표시
- 각 화폐 단위 arr[i]에 대해, 해당 화폐 단위를 사용하여 금액 j를 만들 수 있는지 확인
    - d[j] = Math.min(d[j], d[j - arr[i]] + 1):
    - d[j - arr[i]]가 초기값이 아닌 경우, 즉 j - arr[i] 금액을 만들 수 있는 경우
- 현재 금액 j를 만들기 위한 최소 동전 개수를 갱신
 */