package 지연.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.327 뱀 / 난이도 중
 * 백준 골드4 https://www.acmicpc.net/problem/3190
 */
public class 구현_뱀 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];    // 맵

        // 동남서북
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        Map<Integer, String> moveInfo = new HashMap<>();    // 이동정보

        int k = Integer.parseInt(br.readLine());
        StringTokenizer st;
        // 사과 위치 저장
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col] = 1;
        }

        // 이동정보 저장
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            moveInfo.put(time, direction);
        }

        Queue<Position> snake = new LinkedList<>(); // 뱀
        int time = 0;
        // 뱀 머리 위치
        int x = 1, y = 1;
        map[x][y] = 2;  // 뱀 존재하는 위치는 맵에서 2로 표시
        int d = 0; // 처음에는 동쪽을 바라봄
        snake.offer(new Position(x, y));   // 뱀이 차지하고 있는 위치(꼬리가 왼쪽)

        while (true) {
            // 뱀 이동
            int nx = x + dx[d];
            int ny = y + dy[d];

            time++;

            // 벽이거나 몸통이 부딪힘
            if (nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] == 2) {
                break;
            }

            // 뱀 이동
            // 사과가 있다면
            if (map[nx][ny] == 1) {
                map[nx][ny] = 2;    // 뱀 있는 곳은 2로 표시
                snake.offer(new Position(nx, ny));  // 뱀 위치 이동
            } else {
                // 사과가 없다면 이동 후 꼬리 제거
                map[nx][ny] = 2;
                snake.offer(new Position(nx, ny));  // 뱀 위치 이동
                Position poll = snake.poll();   // 꼬리 제거
                map[poll.getX()][poll.getY()] = 0;  // 뱀 있던 곳 맵 0으로 변경
            }

            // 회전해야될 시간일경우
            if (moveInfo.containsKey(time)) {
                if (moveInfo.get(time).equals("D")) {
                    d += 1;
                    if (d == 4) d = 0;
                } else {
                    d -= 1;
                    if (d == -1) d = 3;
                }
            }

            // 다음 위치로 머리 이동
            x = nx;
            y = ny;

        }
        System.out.println(time);
    }

    private static class Position {
        private int x;
        private int y;
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

/*
이 문제는 구현의 시뮬레이션 문제로 문제의 설명을 따라 한줄씩 코드를 작성하면 된다.
1. 시간재기
2. 뱀 이동하기
3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
4. 사과가 있을 때 없을 때 처리
5. 방향을 바꿔주는 시간을 만날 때 방향 변경
6. 현재값 업데이트
 */