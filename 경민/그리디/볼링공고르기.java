package 그리디;

import java.util.Scanner;

public class 볼링공고르기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); //볼링공 수
        int m = scanner.nextInt();

        // 무게별 볼링공 갯수 배열
        int[] counts = new int[m + 1];

        // 볼링공의 무게 세기
        for (int i = 0; i < n; i++) {
            int w = scanner.nextInt();
            counts[w]++;
        }

        int result = 0;
        int totalBalls = n;

        // 각 무게에 대해 두사람이 선택할 수 있는 경우의 수 계산
        for (int i = 1; i <= m; i++) {
            totalBalls -= counts[i]; // A가 무게 i를 선택할 경우를 제외
            result += counts[i] * totalBalls; // B가 선택하는 경우의 수 계산
        }

        System.out.println(result);
    }
}
