package DFS_BFS;

import java.util.*;

public class 인구이동 {
    public static int N, L, R; // 땅 크기 NxN, 인구 차이의 최소값 L, 최대값 R
    public static int[][] A; // 각 나라의 인구 수를 저장하는 배열
    public static boolean[][] visited; // 방문 여부를 확인하기 위한 배열
    public static int[] dx = {-1, 1, 0, 0}; // 상하좌우 이동을 위한 x 좌표 변화량
    public static int[] dy = {0, 0, -1, 1}; // 상하좌우 이동을 위한 y 좌표 변화량

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt(); // 땅 크기
        L = scanner.nextInt(); // 인구 차이 최소값
        R = scanner.nextInt(); // 인구 차이 최대값

        A = new int[N][N]; // 땅 크기에 맞는 2차원 배열
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                A[i][j] = scanner.nextInt(); // 각 나라의 인구 수 입력받기
            }
        }

        int result = 0; // 인구 이동이 발생한 횟수
        while (true) {
            visited = new boolean[N][N]; // 매번 새로운 방문 배열 초기화
            boolean isMoved = false; // 이번 턴에 인구 이동이 있었는지 여부를 저장

            // 모든 나라에 대해 BFS 실행
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) { // 방문하지 않은 나라에 대해
                        if (bfs(new Position(i, j))) { // BFS를 통해 연합을 확인하고 인구 이동 처리
                            isMoved = true; // 인구 이동이 발생했음을 기록
                        }
                    }
                }
            }

            if (!isMoved) break; // 더 이상 인구 이동이 없으면 반복 종료
            result++; // 인구 이동 횟수 증가
        }

        System.out.println(result);
    }

    // BFS를 사용하여 인구 이동을 확인하고 처리하는 메서드
    public static boolean bfs(Position start) {
        Queue<Position> queue = new LinkedList<>(); // BFS를 위한 큐 생성
        ArrayList<Position> union = new ArrayList<>(); // 연합에 속한 나라들을 저장할 리스트

        queue.offer(start); // 시작 지점을 큐에 추가
        union.add(start); // 시작 지점을 연합에 추가
        visited[start.getX()][start.getY()] = true; // 시작 지점을 방문 처리

        int sum = A[start.getX()][start.getY()]; // 연합의 총 인구 수 초기화
        int count = 1; // 연합에 속한 나라의 수 초기화

        while (!queue.isEmpty()) { // BFS 실행
            Position current = queue.poll(); // 큐에서 현재 위치를 꺼내옴
            int cx = current.getX(); // 현재 x 좌표
            int cy = current.getY(); // 현재 y 좌표

            // 상하좌우 방향으로 인접한 나라를 확인
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i]; // 인접한 나라의 x 좌표
                int ny = cy + dy[i]; // 인접한 나라의 y 좌표

                // 맵 범위 내에 있고 아직 방문하지 않은 나라에 대해
                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny]) {
                    int populationDiff = Math.abs(A[cx][cy] - A[nx][ny]); // 인구 차이 계산

                    // 인구 차이가 L 이상 R 이하인 경우
                    if (populationDiff >= L && populationDiff <= R) {
                        Position next = new Position(nx, ny); // 인접한 나라의 위치를 객체로 생성
                        queue.offer(next); // 큐에 추가
                        union.add(next); // 연합에 추가
                        visited[nx][ny] = true; // 방문 처리
                        sum += A[nx][ny]; // 연합의 총 인구 수 갱신
                        count++; // 연합에 속한 나라의 수 증가
                    }
                }
            }
        }

        // 연합이 형성되면 인구 이동을 수행
        if (count > 1) { // 연합이 2개 이상의 나라로 이루어졌다면
            int newPopulation = sum / count; // 연합의 새로운 인구 수 계산
            for (Position pos : union) { // 연합에 속한 모든 나라의 인구 수를 갱신
                A[pos.getX()][pos.getY()] = newPopulation;
            }
            return true; // 인구 이동이 발생했음을 반환
        }
        return false; // 인구 이동이 발생하지 않았음을 반환
    }

    // Position 클래스: x, y 좌표를 저장하는 클래스
    static class Position {
        private int x; // x 좌표
        private int y; // y 좌표

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return this.x; // x 좌표 반환
        }

        public int getY() {
            return this.y; // y 좌표 반환
        }
    }
}
