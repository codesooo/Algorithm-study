package greedy;

import java.io.*;
import java.util.*;

public class greedy_볼링공고르기 {
    private static int N, M;
    private static int[] balls;
    private static int[] heights;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        balls = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            balls[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
//        System.out.println(solution2());
    }

    // 내 풀이
    private static int solution() {
        int cnt = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i; j < N; j++) {
                if (balls[i] != balls[j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    // 정답
    private static int solution2() {
        int cnt = 0;
        heights = new int[11];
        for (int i = 0; i < N; i++) {
            heights[balls[i]] += 1;
        }
        for (int i = 1; i <= M; i++) {
            N -= heights[i];
            cnt += N * heights[i];
        }
        return cnt;
    }

}
