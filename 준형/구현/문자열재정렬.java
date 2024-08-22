package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 문자열재정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();

        char[] charArray = input.toCharArray();
        List<Character> list = new ArrayList<>();
        int sum = 0;
        for (char c : charArray) {
            if (Character.isDigit(c)) {
                sum += Integer.parseInt(String.valueOf(c));;
            } else {
                list.add(c);
            }
        }

        Collections.sort(list);

        StringBuilder stringBuilder = new StringBuilder();

        for (Character character : list) {
            stringBuilder.append(character);
        }
        stringBuilder.append(sum);
        String answer = stringBuilder.toString();

        System.out.println(answer);
    }
}
