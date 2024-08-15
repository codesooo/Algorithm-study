package 구현;

import java.util.Scanner;

public class 게임개발 {
    // 맵의 크기(N x M), 캐릭터의 위치(x, y), 캐릭터의 방향(direction)
    public static int n, m, x, y, direction;
    // 방문한 위치
    public static int[][] d = new int[50][50];
    // 전체 맵
    public static int[][] arr = new int[50][50];

    // 북, 동, 남, 서 방향 정의
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

    // 왼쪽으로 회전
    // 방향이 0에서 -1이 되면, 서쪽(3)으로 바뀜
    public static void turn_left() {
        direction -= 1;
        if (direction == -1) direction = 3;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력받기
        n = scanner.nextInt(); //행
        m = scanner.nextInt(); //열

        // 현재 캐릭터의 X 좌표, Y 좌표, 방향을 입력받기
        x = scanner.nextInt();
        y = scanner.nextInt();
        direction = scanner.nextInt();
        d[x][y] = 1; // 현재 좌표 방문 처리

        // 전체 맵 정보를 입력 받기 (0: 육지, 1: 바다)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        // 시뮬레이션 시작
        int count = 1; // 방문 칸 수
        int turn_time = 0; // 네 방향 모두 갈 수 없는 경우를 세기 위한 변수

        while (true) {
            // 왼쪽으로 회전
            turn_left();
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            // 회전한 이후 정면에 가보지 않은 칸이 존재하는 경우 이동
            if (d[nx][ny] == 0 && arr[nx][ny] == 0) {
                d[nx][ny] = 1; // 이동한 위치를 방문한 것으로 처리
                x = nx;
                y = ny;
                count += 1; // 방문한 칸의 수 증가
                turn_time = 0; // 회전 횟수 초기화
                continue;
            }
            // 회전한 이후 정면에 가보지 않은 칸이 없거나 바다인 경우
            else turn_time += 1;
            // 네 방향 모두 갈 수 없는 경우 (회전 횟수가 4번이 된 경우)
            if (turn_time == 4) {
                nx = x - dx[direction];
                ny = y - dy[direction];
                // 뒤로 갈 수 있다면 이동하기
                if (arr[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                }
                // 뒤가 바다로 막혀있는 경우
                else break;
                turn_time = 0;
            }
        }

        System.out.println(count);
    }

}
