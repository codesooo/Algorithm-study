package 지연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.349 연산자 끼워 넣기 / 난이도 중
 * 백준 실버1 https://www.acmicpc.net/problem/14888
 */
public class DFS_BFS_연산자끼워넣기 {
    private static int min;
    private static int max;
    private static int n;
    private static int[] operator;
    private static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 개수
        StringTokenizer st = new StringTokenizer(br.readLine());

        number = new int[n]; // 입력 받은 수 배열
        operator = new int[4]; // 연산별 개수 배열
        // 입력 받은 수 셋팅
        for (int i = 0; i < n; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        // 입력받은 연산 셋팅
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        // dfs 수행
        dfs(number[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int num, int index) {
        // 모든 수의 연산이 끝났을 때
        if (index == n) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        // 연산별 재귀로 탐색
        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--;

                switch (i) {
                    case 0: dfs(num + number[index], index + 1); break;
                    case 1: dfs(num - number[index], index + 1); break;
                    case 2: dfs(num * number[index], index + 1); break;
                    case 3: dfs(num / number[index], index + 1); break;
                }
                operator[i]++;
            }
        }
    }

}
