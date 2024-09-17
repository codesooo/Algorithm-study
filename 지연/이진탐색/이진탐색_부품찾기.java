package 지연.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 실전문제
 * p.197 부품 찾기 / 난이도 중
 */
public class 이진탐색_부품찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] stocks = new int[n];
        for (int i = 0; i < n; i++) {
            stocks[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(stocks);

        int findItemCount = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < findItemCount; i++) {
            int target = Integer.parseInt(st.nextToken());
            String result = findItem(stocks, target, 0, n - 1);
            sb.append(result).append(" ");
        }

        System.out.println(sb);

    }

    private static String findItem(int[] stocks, int target, int start, int end) {
        if (start > end) return "no";
        int mid = (start + end) / 2;

        if (stocks[mid] == target) return "yes";
            // 오른쪽 탐색
        else if (stocks[mid] < target) {
            return findItem(stocks, target, mid + 1, end);
            // 왼쪽 탐색
        } else {
            return findItem(stocks, target, start, mid - 1);
        }
    }
}

/*
이 문제는 이진탐색, 계수정렬, Set을 이용하여 풀 수 있다.
나는 이진탐색을 재귀를 이용하여 해결했다.
 */