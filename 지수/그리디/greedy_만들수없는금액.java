package greedy;

import java.io.*;
import java.util.*;
public class greedy_만들수없는금액 {
    private static int N;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());

    }

    private static int solution() {
        Arrays.sort(arr);
        int target = 1;
        for (int i = 0; i < N; i++) {
            if (target < arr[i]) {
                break;
            }
            target += arr[i];
        }
        return target;
    }
}
