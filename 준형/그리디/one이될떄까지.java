package 그리디;

import java.util.Scanner;

public class one이될떄까지 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int answer = solution(N, K);
        System.out.println(answer);
    }

    private static int solution(int n, int k) {
        int answer = 0;
        while (n != 0) {
            if ((n & k) == 0) {
                n = n / k;
            } else {
                n = n - 1;
            }
            answer++;
        }

        return answer;
    }
}
