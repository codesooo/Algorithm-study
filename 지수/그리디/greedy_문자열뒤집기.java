package greedy;

import java.util.*;
import java.io.*;
public class greedy_문자열뒤집기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();

        int cnt_0 = 0;
        int cnt_1 = 0;
        if (str.charAt(0) == '1') {
            cnt_0++;
        }
        else {
            cnt_1++;
        }

        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) != str.charAt(i + 1)) {
                if (str.charAt(i + 1) == '1') {
                    cnt_0++;
                } else {
                    cnt_1++;
                }
                    
            }
        }
        System.out.println(Math.min(cnt_0, cnt_1));
    }
}
