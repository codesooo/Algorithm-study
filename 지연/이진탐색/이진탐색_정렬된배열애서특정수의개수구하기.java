package 지연.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.367 정렬된배열애서특정수의개수구하기 / 난이도 중
 */
public class 이진탐색_정렬된배열애서특정수의개수구하기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 값이 (x,x) 범위에 있는 데이터의 개수 계산
        int result = find(arr, x, x);
        if(result == 0) System.out.println(-1);
        else System.out.println(result);
    }

    // 값이 (left, right)인 데이터의 개수 반환
    private static int find(int[] arr, int left, int right) {
        int rightIndex = upperBound(arr, right, 0, arr.length);
        int leftIndex = lowerBound(arr, left, 0, arr.length);
        return rightIndex - leftIndex;

    }

    private static int upperBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) end = mid;
            else start = mid + 1;
        }
        return end;
    }
    private static int lowerBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }
}

/*
이 문제는 정렬된 상태의 값이 주어지기 때문에 이진탐색으로 해결할 수 있다.
x값이 처음 등장한 인덱스와 마지막으로 등장하는 인덱스를 각각 계산하여 문제를 해결할 수 있다.
 */