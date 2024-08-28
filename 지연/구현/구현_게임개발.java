package 지연.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 실전문제
 * p.118 게임 개발 / 난이도 중
 */
public class 구현_게임개발 {
    static int direction;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());   // 방향

        int[][] map = new int[n][m];
        int[][] visited = new int[n][m];    // 방문한 곳

        // 맵 설정
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[x][y] = 1;  // 처음 서있는 곳 방문처리
        // 북, 동, 남, 서
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};

        int cnt = 1;    // 방문횟수
        int turnCnt = 0;    // 회전횟수
        while (true) {
            turnLeft(); // 왼쪽회전
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            // 회전 이후 정면에 가보지 않은 곳이 있을경우
            if (visited[nx][ny] == 0 && map[nx][ny] == 0) {
                visited[nx][ny] = 1;    // 방문처리
                // 캐릭터 위치 변경
                x = nx;
                y = ny;
                cnt++;  // 방문횟수 증가
                turnCnt = 0;    // 회전횟수 초기화
                continue;
            // 회전 이후 가본 곳이거나 바다일경우
            } else {
                turnCnt++;
                // 네방향 모두 못갈경우
                if (turnCnt == 4) {
                    nx = x - dx[direction];
                    ny = y - dy[direction];

                    // 뒤로 갈 수 있다면 이동
                    if (map[nx][ny] == 0) {
                        x = nx;
                        y = ny;
                    } else {
                        // 뒤가 바다로 막힌경우
                        break;
                    }
                    turnCnt = 0;
                }
            }
        }
        System.out.println(cnt);
    }

    private static void turnLeft() {
        direction -= 1;
        if(direction == -1) direction = 3;
    }
}

/*
이 문제는 구현 문제이다
문제를 찬찬히 읽어보며 캐릭터 이동시키는 것을 구현하면 된다.

1. 캐릭터의 방향에 맞게 좌표를 계산한 다음 그 칸이 0 이거나 방문하지 않은 칸이면 방문하고 방문 횟수를 1 증가.
2. 앞으로 갈 수 없다면 캐릭터를 회전시키고 회전한 횟수를 1 증가 시킨다.
3. 회전한 횟수가 4라면 뒤로 갈 수 있는지 없는지 판단하는데 만약 갈 수 없으면 종료하고, 갈 수 있다면 뒤로 가고 회전한 횟수를 0으로 초기화

 */