package 지연.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.368 공유기 설치 / 난이도 중
 */
public class 이진탐색_공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int start = 1; // 가능한 최소 거리(min gap)
        int end = arr[n - 1] - arr[0]; // 가능한 최대 거리(max gap)
        int result = 0;

        while (start <= end) {
            // mid: 가장 인접한 두 공유기 사이의 거리(gap)을 의미
            int mid = (start + end) / 2;
            int value = arr[0]; // 첫째 집에는 무조건 공유기 설치
            int count = 1;
            // 현재의 mid 값을 이용해 공유기를 설치하기
            // 앞에서부터 설치
            for (int i = 1; i < n; i++) {
                if (arr[i] >= value + mid) {
                    value = arr[i];
                    count++;
                }
            }

            // c개 이상의 공유기를 설치할 수 있는 경우, 거리를 증가시키기
            if (count >= c) {
                start = mid + 1;
                result = mid;
            }
            // c개 이상의 공유기를 설치할 수 없는 경우, 거리를 감소시키기
            else {
                end = mid - 1;
            }
        }
        System.out.println(result);

    }
}
/*
이 문제는 가장 인접한 두 공유기 사이의 거리가 최대인 것을 구하는 것이지만
결국, 모든 공유기들의 간격이 공평하게 설치되기를 원하는 것아기 때문에
모든 공유기들을 공평하게 살치할 수 있는 간격을 이분 탐색을 통해 찾아야한다.

1. 공유기 설치 좌표들을 오름차순으로 정렬한다.
2. 공유기를 설치할 수 있는 최소 간격과 최대 간격을 구한 뒤, 공유기를 가장 공평하게 설치할 수 있는 간격(mid)를 구한다.
3. 첫 번째 집에 공유기를 설치한 뒤, 첫 번째 집에서 나머지 집들 간의 간격을 확인하며, mid 이상으로 떨어져 있는 집을 탐색한다.
4. 첫 번째 집으로부터 mid 이상 떨어진 집을 찾은 경우, 해당 집에 공유기를 설치한 뒤, 해당 집 기준으로 다시 mid 만큼 떨어져있는 집을 탐색한다.
5. 모든 집을 탐색했다면, 공유기 설치 간격을 갱신한다.
    - 현재까지 설치한 공유기의 개수가 아직 c개 이하라면, 기존 각격이 너무 크다는 의미이므로 기존 간격보다 더 작은 간격으로 갱신
    - 현재까지 설치한 공유기의 개수가 c개 이상이라면, 기존 간격이 너무 작다는 의미이므로 기존 간격보다 더 큰 간격으로 갱신

 */