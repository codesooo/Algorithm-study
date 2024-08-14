import java.util.*;
import java.io.*;

public class impl_왕실의나이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        System.out.println(solution(str));
//        System.out.println(solution2(str));

    }

    private static int solution(String str) {
        String[] location = str.split("");
        int x = Integer.parseInt(location[1]);
        int y = location[0].charAt(0) - 96;

        int[] dx = {-2, -2, 2, 2, -1, -1, 1, 1};
        int[] dy = {1, -1, 1, -1, -2, 2, -2, 2};

        int cnt = 0;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > 8 || ny > 8) {
                continue;
            }
            cnt++;
        }

        return cnt;
    }

    private static int solution2(String str) {
        int x = str.charAt(1) - '0';
        int y = str.charAt(0) - 'a' + 1;

        int cnt=0;
        int[][] steps = {
                {-2, 1}, {-2, -1}, {2, 1}, {2, -1},
                {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
        };

        for (int[] step : steps) {
            int nx = x + step[0];
            int ny = y + step[1];
            if (nx < 1 || ny < 1 || nx > 8 || ny > 8) {
                continue;
            }
            cnt++;
        }
        
        return cnt;
    }
}
