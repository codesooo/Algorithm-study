package 구현;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 문자열재정렬 {
    public static void main(String[] args) {

        ArrayList<Character> letter = new ArrayList<>(); // 알파벳 저장할 리스트
        int total = 0; // 숫자 합계 저장할 변수

        Scanner scanner = new Scanner(System.in);
        String string = scanner.next(); // 입력 값

        // 하나씩 입력값 확인
        for(int i=0; i<string.length(); i++){
            // 문자이면 알파벳 리스트에 추가
            if (Character.isLetter(string.charAt(i))) {
                letter.add(string.charAt(i));
            }
            // 알파벳이 아니면(숫자면) 숫자 합계에 더하기
            else {
                total += string.charAt(i) - '0';
            }
        }

        // 알파벳 정렬
        Collections.sort(letter);

        // 알파벳 리스트 출력
        for (int i=0; i<letter.size(); i++) {
            System.out.print(letter.get(i));
        }

        // 합계가 0이 아니면 뒤에 출력
        if (total != 0) {
            System.out.println(total);
        }

    }
}
