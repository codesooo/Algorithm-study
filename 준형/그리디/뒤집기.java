package 그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 뒤집기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        int answer = 0;
        char[] charArray = input.toCharArray();
        char firstIndex = charArray[0];

        int countZeroBlocks = 0;
        int countOneBlocks = 0;

        // 첫번째 블록 확인
        if (firstIndex == '0') {
            countZeroBlocks++;
        } else {
            countOneBlocks++;
        }

        for (int i = 1; i < input.length(); i++) {
            if (charArray[i] != charArray[i - 1]) {
                if (charArray[i] == '0') {
                    countZeroBlocks++;
                } else {
                    countOneBlocks++;
                }
            }
            // 더 작은 블록 수를 뒤집어야 최소로 만들 수 있음
            answer = Math.min(countZeroBlocks, countOneBlocks);
        }
        System.out.println(answer);
    }
}
