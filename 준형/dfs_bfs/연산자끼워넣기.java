package dfs_bfs;

import java.util.Scanner;

public class 연산자끼워넣기 {

    static int N;
    static int[] numbers;
    static int plusTime;
    static int minusTime;
    static int timesTime;
    static int divideTime;
    static int minValue = Integer.MAX_VALUE;
    static int maxValue = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt();
        }

        plusTime = scanner.nextInt();
        minusTime = scanner.nextInt();
        timesTime = scanner.nextInt();
        divideTime = scanner.nextInt();

        dfs(1, numbers[0]);
        System.out.println(maxValue);
        System.out.println(minValue);
    }

    // 깊이 우선 탐색 (DFS) 메서드
    public static void dfs(int i, int now) {
        if (i == N) {
            minValue = Math.min(minValue, now);
            maxValue = Math.max(maxValue, now);
        } else {
            // 각 연산자에 대하여 재귀적으로 수행
            if (plusTime > 0) {
                plusTime -= 1;
                dfs(i + 1, now + numbers[i]);
                plusTime += 1;
            }
            if (minusTime > 0) {
                minusTime -= 1;
                dfs(i + 1, now - numbers[i]);
                minusTime += 1;
            }
            if (timesTime > 0) {
                timesTime -= 1;
                dfs(i + 1, now * numbers[i]);
                timesTime += 1;
            }
            if (divideTime > 0) {
                divideTime -= 1;
                dfs(i + 1, now / numbers[i]);
                divideTime += 1;
            }
        }
    }

}
