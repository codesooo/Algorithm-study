package 이진탐색;

import java.util.Scanner;

public class 고정점찾기 {

    public static int bruteforce(int[] arr, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (arr[mid] == mid) {
            return mid;
        } else if (arr[mid] > mid) {
            return bruteforce(arr, start, mid - 1);
        } else {
            return bruteforce(arr, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int result = bruteforce(arr, 0, n - 1);

        System.out.println(result);
    }
}