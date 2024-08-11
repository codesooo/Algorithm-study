package greedy;

import java.util.*;
import java.io.*;
public class greedy_곱하기혹은더하기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        // 첫 번째 문자를 숫자로 변경한 값을 대입
        long result = str.charAt(0) - '0';

        for (int i = 1; i < str.length(); i++) {
            int num = str.charAt(i) - '0';
            if (num <= 1 || result <= 1) {
                result += num;
            }
            else {
                result *= num;
            }
        }

        System.out.println(result);
    }
}
