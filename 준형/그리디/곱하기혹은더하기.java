package 그리디;

import java.util.Scanner;

public class 곱하기혹은더하기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(solution(input));
    }

    private static int solution(final String input) {
        int firstIndex = Integer.parseInt(input.substring(0, 1));
        int answer = 0;

        for (int i = 1; i < input.length(); i++) {
            String indexStr = input.substring(i, i + 1);
            int index = Integer.parseInt(indexStr);

            if (i == 1) {
                answer = Math.max(firstIndex * index, firstIndex + index);
            } else {
                answer = Math.max(answer * index, answer + index);
            }
        }
        return answer;
    }
}
