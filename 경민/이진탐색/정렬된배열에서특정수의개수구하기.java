package 이진탐색;

import java.util.*;

public class 정렬된배열에서특정수의개수구하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 수열의 원소 개수
        int x = sc.nextInt(); // 찾고자하는 값

        // 수열 입력받기
        int[] list = new int[n];
        for (int i = 0; i < n; i++) {
            list[i] = sc.nextInt();
        }

        // 값이 [x, x] 범위에 있는 데이터의 개수 계산
        int cnt = countByRange(list, x, x);

        // 값이 x인 원소가 존재하지 않는다면
        if (cnt == 0) System.out.println(-1);
            //  값이 x인 원소가 존재한다면
        else System.out.println(cnt);
    }

    // 배열에서 x보다 크거나 같은 값이 처음 나타나는 인덱스를 찾는 함수
    public static int lowerBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;  // 중간 인덱스 계산
            if (arr[mid] >= target)
                end = mid;  // 중간 값이 x보다 크거나 같으면 왼쪽으로 이동
            else
                start = mid + 1;  // 중간 값이 x보다 작으면 오른쪽으로 이동
        }
        return end;  // x 이상의 값이 처음 나타나는 인덱스 반환
    }

    // 배열에서 x보다 큰 값이 처음 나타나는 인덱스를 찾는 함수
    public static int upperBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;  // 중간 인덱스 계산
            if (arr[mid] > target)
                end = mid;  // 중간 값이 x보다 크면 왼쪽으로 이동
            else
                start = mid + 1;  // 중간 값이 x보다 작거나 같으면 오른쪽으로 이동
        }
        return end;  // x를 초과하는 값이 처음 나타나는 인덱스 반환
    }

    // 값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
    public static int countByRange(int[] arr, int leftValue, int rightValue) {
        // 유의: lowerBound와 upperBound는 end 변수의 값을 배열의 길이로 설정
        // rightIndex는 right_value보다 큰 값이 처음 나타나는 인덱스
        int rightIndex = upperBound(arr, rightValue, 0, arr.length);
        // leftIndex는 left_value보다 크거나 같은 값이 처음 나타나는 인덱스
        int leftIndex = lowerBound(arr, leftValue, 0, arr.length);
        // 두 인덱스의 차이가 left_value에서 right_value까지 값의 개수
        return rightIndex - leftIndex;
    }
}