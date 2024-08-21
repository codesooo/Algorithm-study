package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 럭키스트레이트 {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        int length = input.length();

        String front = input.substring(0, length / 2);
        char[] frontValueArray = front.toCharArray();

        String back = input.substring(length / 2, length);
        char[] backValueArray = back.toCharArray();

        int frontSum = 0;
        for (char c : frontValueArray) {
            frontSum += c;
        }

        int backSum = 0;
        for (char c : backValueArray) {
            backSum += c;
        }

        if (frontSum == backSum) {
            System.out.println("LUCKY");
        } else {
            System.out.println("READY");
        }
    }
}
