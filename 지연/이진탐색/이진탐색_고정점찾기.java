package 지연.이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.368 고정점 찾기 / 난이도 중
 */
public class 이진탐색_고정점찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = find(arr, 0, n - 1);
        System.out.println(result);

    }

    private static int find(int[] arr, int start, int end) {
        if(start > end) {
            return -1;
        }
        int mid = (start + end) / 2;

        // 고정점 찾은경우 중간 값 리턴
        if(arr[mid] == mid) {
            return mid;
        } else if(arr[mid] > mid) {
            // 중간점 값보다 중간 점이 작은 경우
            return find(arr, start, mid - 1);
        } else {
            return find(arr, mid + 1, end);
        }
    }
}

/*
이 문제는 정렬이 되어있고, nlogn의 시간제한을 가지고 있기 때문에 이진탐색을 이용해야 한다.
1. 고정점 찾은경우 중간 값 리턴
2. 중간점 값보다 중간 점이 작은 경우 중간점을 end로 변경하여 재귀실행
3. 중간점 값보다 중간 점이 큰 경우 중간점을 start로 변경하여 재귀실행
 */