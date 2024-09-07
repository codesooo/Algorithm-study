package 정렬;

import java.util.*;

public class 안테나 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        Integer[] house = new Integer[N];
        for (int i=0; i<N; i++){
            house[i] = scanner.nextInt();
        }

        Arrays.sort(house);

        // 중간값 계산
        int m = (N-1) / 2;

        System.out.println(house[m]);
    }
}
