package 정렬;

import java.util.*;

public class 두배열의원소교체 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        int[] A = new int[N];
        for (int i=0; i<N; i++){
            A[i] = scanner.nextInt();
        }

        Integer[] B = new Integer[N];
        for (int i=0; i<N; i++){
            B[i] = scanner.nextInt();
        }

        Arrays.sort(A);
        Arrays.sort(B, Collections.reverseOrder());

        for(int i=0; i<K; i++){
            // A의 원소가 B의 원소보다 작은 경우
            if (A[i] < B[i]) {
                // 두 원소를 교체
                int temp = A[i];
                A[i] = B[i];
                B[i] = temp;
            }
            // A의 원소가 B의 원소보다 크거나 같을 때, 반복문을 탈출
            else break;
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            result += A[i];
        }

        System.out.println(result);
    }
}
