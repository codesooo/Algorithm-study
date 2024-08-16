import java.io.*;
import java.util.*;
public class impl_럭키스트레이트 {
    private static String str;
    private static int summary = 0;
  
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        for (int i = 0; i < str.length() / 2; i++) {
            summary += str.charAt(i) - '0';
        }

        for (int i = str.length() / 2; i < str.length(); i++) {
            summary -= str.charAt(i) - '0';
        }

        if (summary == 0) System.out.println("LUCKY");
        else System.out.println("READY");
    }
}
