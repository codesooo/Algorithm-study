package DFS_BFS;

import java.util.*;

public class 연구소 {

    public static int n, m, result = 0; // 연구소의 크기 n x m, 최대 안전 영역 크기를 저장할 변수
    public static int[][] arr = new int[8][8]; // 초기 연구소 상태 배열
    public static int[][] temp = new int[8][8]; // 벽을 설치한 뒤의 연구소 상태 배열

    // 4가지 이동 방향에 대한 배열 (상, 우, 하, 좌)
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 연구소의 크기 n,m 입력받기
        n = sc.nextInt();
        m = sc.nextInt();

        // 연구소의 초기 상태 입력받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt(); // 0: 빈칸, 1: 벽, 2: 바이러스
            }
        }

        dfs(0);

        System.out.println(result);
    }

    // 깊이 우선 탐색(DFS)을 이용해 각 바이러스가 사방으로 퍼지도록 하기
    public static void virus(int x, int y) {
        // 상, 하, 좌, 우로 이동하며 바이러스 전파
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 바이러스가 전파될 수 있는 빈 칸인지 확인
            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (temp[nx][ny] == 0) { // 빈 칸이라면 바이러스 전파
                    temp[nx][ny] = 2; // 해당 위치에 바이러스 배치
                    virus(nx, ny); // 재귀적으로 전파
                }
            }
        }
    }

    // 현재 연구소 상태에서 안전 영역의 크기 계산
    public static int getScore() {
        int score = 0; // 안전 영역 크기
        // 연구소의 모든 칸을 탐색하면서 빈 칸의 개수 확인
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) { // 빈 칸이면
                    score += 1; // 안전 영역 크기 증가
                }
            }
        }
        return score;
    }

    // 깊이 우선 탐색(DFS)을 이용해 벽을 설치하면서, 매번 안전 영역의 크기 계산
    public static void dfs(int count) {
        // 벽이 3개 설치된 경우
        if (count == 3) {
            // 연구소의 상태를 복사하여 temp 배열에 저장
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = arr[i][j];
                }
            }
            // 각 바이러스의 위치에서 전파 진행
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) { // 바이러스가 있는 위치라면
                        virus(i, j); // 바이러스 전파 시작
                    }
                }
            }
            // 안전 영역의 최대값 계산
            result = Math.max(result, getScore());
            return;
        }
        // 빈 공간에 벽을 설치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) { // 해당 위치가 빈 공간(0)인 경우
                    arr[i][j] = 1; // 빈 공간에 벽(1)을 설치
                    count += 1; // 설치된 벽의 개수를 증가
                    dfs(count); // 다음 벽을 설치하기 위해 재귀 호출
                    arr[i][j] = 0; // 설치했던 벽을 다시 제거
                    count -= 1; // 설치된 벽의 개수를 감소시켜 원상복구
                }
            }
        }
    }
}
