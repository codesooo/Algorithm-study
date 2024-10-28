package 삼성전자기출문제;

import java.util.*;

public class 아기상어 {
    static int n; // 맵의 크기
    static int[][] array; // 전체 맵 정보
    static int nowSize = 2; // 아기 상어의 현재 크기
    static int nowX, nowY; // 아기 상어의 현재 위치

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 맵의 크기 입력
        array = new int[n][n]; // 맵 초기화

        // 맵 정보 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = scanner.nextInt();
                if (array[i][j] == 9) {
                    nowX = i;
                    nowY = j;
                    array[nowX][nowY] = 0; // 시작 위치 처리
                }
            }
        }

        int result = 0; // 최종 거리
        int ate = 0; // 먹은 물고기 수

        while (true) {
            int[] value = find(bfs()); // 먹을 물고기 찾기
            if (value == null) {
                System.out.println(result); // 더 이상 먹을 물고기가 없으면 결과 출력
                break;
            } else {
                // 아기 상어 위치 갱신 및 이동 거리 추가
                nowX = value[0];
                nowY = value[1];
                result += value[2];
                array[nowX][nowY] = 0; // 먹은 위치 처리
                ate++;

                // 크기 증가 조건 확인
                if (ate >= nowSize) {
                    nowSize++;
                    ate = 0;
                }
            }
        }
    }

    // 모든 위치까지의 최단 거리를 계산하는 BFS 함수
    public static int[][] bfs() {
        int[][] dist = new int[n][n]; // 거리 저장 배열
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1); // 각 행을 개별적으로 초기화
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {nowX, nowY});
        dist[nowX][nowY] = 0; // 시작 위치는 거리가 0

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int x = pos[0];
            int y = pos[1];

            // 상하좌우 이동
            int[] dx = {-1, 0, 1, 0};
            int[] dy = {0, 1, 0, -1};

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    // 자신의 크기보다 작거나 같은 경우 지나갈 수 있음
                    if (dist[nx][ny] == -1 && array[nx][ny] <= nowSize) {
                        dist[nx][ny] = dist[x][y] + 1;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        }
        return dist;
    }

    // 먹을 물고기를 찾는 함수 (최단 거리 테이블에서)
    public static int[] find(int[][] dist) {
        int x = 0, y = 0;
        int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정

        int minDist = INF;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 도달 가능하면서 먹을 수 있는 물고기일 때
                if (dist[i][j] != -1 && array[i][j] >= 1 && array[i][j] < nowSize) {
                    if (dist[i][j] < minDist) {
                        x = i;
                        y = j;
                        minDist = dist[i][j];
                    }
                }
            }
        }
        if (minDist == INF) { // 먹을 수 있는 물고기가 없을 경우
            return null;
        } else {
            return new int[] {x, y, minDist}; // 물고기 위치와 최단 거리
        }
    }
}