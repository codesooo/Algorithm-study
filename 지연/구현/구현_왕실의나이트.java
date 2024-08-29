package 지연.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩테스트다 / 실전문제
 * p.115 왕실의 나이트 / 난이도 하
 */
public class 구현_왕실의나이트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int col = s.charAt(0) - 'a' + 1;    // 열
        int row = s.charAt(1) - '0';        // 헹

        // 상좌, 상우, 하좌, 하우, 우상, 우하, 좌상, 좌하
        int[] dx = {-2, -2, -1, 1, 2, 2, -1, -1};   // 행
        int[] dy = {-1, 1, 2, 2, -1, 1, -2, -2};     // 열

        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            // 체스판 밖으로 나가는 것 제외
            if (nx < 1 || nx > 8 || ny < 1 || ny > 8) {
                continue;
            }
            cnt++;
        }

        System.out.println(cnt);
    }
}

/*
이 문제는 구현 문제로 완전탐색으로 풀 수 있다.
나이트가 이동할 수 있는 8가지의 경로를 모두 확인하여 체스판 밖으로 나가는 것을 제외하고 횟수를 더해주면 된다.
 */