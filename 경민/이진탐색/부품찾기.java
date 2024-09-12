package 이진탐색;

import java.util.Scanner;

public class 부품찾기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N(가게의 부품 개수)
        int N = scanner.nextInt();

        int[] list = new int[1000001];
        for(int i=0; i<N; i++) {
            int x = scanner.nextInt();  // 부품 번호 입력 받기
            list[x] = 1;  // 해당 번호의 부품이 있음을 표시
        }

        // M(손님이 확인 요청한 부품 개수)
        int M = scanner.nextInt();

        int[] mList = new int[M];
        for(int i=0; i<M; i++) {
            mList[i] = scanner.nextInt();
        }

        // 손님이 확인 요청한 부품 번호를 하나씩 확인
        for (int i = 0; i < M; i++) {
            // 해당 부품이 존재하는지 확인
            if (list[mList[i]] == 1) {
                System.out.print("yes ");
            }
            else {
                System.out.print("no ");
            }
        }
    }
}