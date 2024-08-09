package greedy;

import java.io.*;
import java.util.*;
public class 숫자카드게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        /* 배열 선언 O */
//        int[][] arr = new int[N][M];
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < M; j++) {
//                arr[i][j] = Integer.parseInt(st.nextToken());
//            }
//            Arrays.sort(arr[i]);
//        }
//        int max=0;
//        for (int i = 0; i < N; i++) {
//            if (max < arr[i][0]) {
//                max = arr[i][0];
//            }
//        }
//        System.out.println(max);

        /* 배열 선언 X */
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int min = Integer.parseInt(st.nextToken());
            for (int j = 0; j < M-1; j++) {
                int now = Integer.parseInt(st.nextToken());
                min = Math.min(min, now);
            }
            max = Math.max(min, max);
        }
        System.out.println(max);
    }
}
