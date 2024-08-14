package 구현;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 왕실의나이트 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String location = scanner.nextLine();

        int[][] moves = {
                {-2, +1}, {-1, +2},
                {+1, +2}, {+2, +1},
                {+2, -1}, {+1, -2},
                {-1, -2}, {-2, -1}
        };

        char x = location.charAt(0);
        char y = location.charAt(1);
        int intX = x - 96;
        int intY = Character.getNumericValue(y);

        int answer = 0;
        for (int[] move : moves) {
            int afterX = intX + move[0];
            int afterY = intY + move[1];

            if (afterX > 0 && afterY > 0) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
