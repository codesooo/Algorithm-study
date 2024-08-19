import java.util.*;
import java.io.*;

public class impl_문자열재정렬 {
    private static String str;
    private static ArrayList<Character> result = new ArrayList<>();
    private static int value = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                result.add(str.charAt(i));
            }
            else {
                value += str.charAt(i) - '0';
            }
        }

        Collections.sort(result);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
        }
        if (value != 0) System.out.print(value);
        System.out.println();
    }
}
