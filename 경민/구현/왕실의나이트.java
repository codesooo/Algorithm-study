package 구현;

import java.util.Scanner;

public class 왕실의나이트 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 현재 나이트의 위치 입력받기
        String inputData = scanner.nextLine();
        int row = inputData.charAt(1) - '0'; //숫자 추출
        int column = inputData.charAt(0) - 'a' + 1; //알파벳 추출 후 숫자로 변환

        // L자로 이동할 수 있는 8가지 방향
        int[][] moves = {
                {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
                {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
        };

        int result = 0;

        // 8가지 방향에 대하여 각 위치로 이동이 가능한지 확인
        for (int[] move : moves) {
            int testRow = row + move[0];
            int testColumn = column + move[1];
            // 해당 위치가 체스판 내에 있다면 카운트 증가
            if (testRow >= 1 && testRow <= 8 && testColumn >= 1 && testColumn <= 8) {
                result++;
            }
        }

        System.out.println(result);
    }

}
