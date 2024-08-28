package 지연.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.322 문자열 재정렬 / 난이도 하
 */
public class 구현_문자열재정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        List<Character> list = new ArrayList<>();

        int num = 0;
        // 문자일경우 리스트에 추가, 숫자일경우 더하기
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                list.add(s.charAt(i));
            } else {
                num += s.charAt(i) - '0';
            }
        }

        // 문자 오름차순 정렬
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }

        // 숫자가 존재할 때 뒤에 숫자 추가
        if(num != 0) sb.append(num);

        System.out.println(sb);
    }
}
