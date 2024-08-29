package DFS_BFS;

import java.util.*;

public class 감시피하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 복도의 크기 N 입력받기
        board = new char[n][n]; // 복도 크기에 맞는 2차원 배열 초기화

        // 복도의 각 칸에 대한 정보 입력받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.next().charAt(0); // 해당 칸에 대한 정보(S, T, X)를 입력받음
                if (board[i][j] == 'T') {
                    teachers.add(new Position(i, j)); // 선생님 위치를 리스트에 추가
                }
                if (board[i][j] == 'X') {
                    spaces.add(new Position(i, j)); // 빈 공간 위치를 리스트에 추가
                }
            }
        }

        // DFS를 통해 3개의 장애물을 설치하여 모든 학생이 감시로부터 피할 수 있는지 확인
        if (dfs(0)) {
            System.out.println("YES"); // 가능하다면 "YES" 출력
        } else {
            System.out.println("NO"); // 불가능하다면 "NO" 출력
        }
    }

    public static int n; // 복도의 크기
    public static char[][] board; // 복도 정보를 저장하는 2차원 배열
    public static ArrayList<Position> teachers = new ArrayList<>(); // 선생님 위치 저장 리스트
    public static ArrayList<Position> spaces = new ArrayList<>(); // 빈 공간 위치 저장 리스트

    // 특정 방향으로 감시를 진행하는 메소드
    public static boolean watch(int x, int y, int direction) {
        if (direction == 0) { // 왼쪽 방향으로 감시
            while (y >= 0) {
                if (board[x][y] == 'S') return true; // 학생 발견 시 true 반환
                if (board[x][y] == 'O') return false; // 장애물 발견 시 false 반환
                y--;
            }
        } else if (direction == 1) { // 오른쪽 방향으로 감시
            while (y < n) {
                if (board[x][y] == 'S') return true; // 학생 발견 시 true 반환
                if (board[x][y] == 'O') return false; // 장애물 발견 시 false 반환
                y++;
            }
        } else if (direction == 2) { // 위쪽 방향으로 감시
            while (x >= 0) {
                if (board[x][y] == 'S') return true; // 학생 발견 시 true 반환
                if (board[x][y] == 'O') return false; // 장애물 발견 시 false 반환
                x--;
            }
        } else if (direction == 3) { // 아래쪽 방향으로 감시
            while (x < n) {
                if (board[x][y] == 'S') return true; // 학생 발견 시 true 반환
                if (board[x][y] == 'O') return false; // 장애물 발견 시 false 반환
                x++;
            }
        }
        return false; // 학생을 발견하지 못한 경우 false 반환
    }

    // 장애물 설치 이후에 학생이 감지되는지 검사하는 메소드
    public static boolean process() {
        for (int i = 0; i < teachers.size(); i++) {
            int x = teachers.get(i).getX();
            int y = teachers.get(i).getY();
            for (int j = 0; j < 4; j++) {
                if (watch(x, y, j)) {
                    return true; // 학생이 감지되면 true 반환
                }
            }
        }
        return false; // 모든 학생이 감지되지 않으면 false 반환
    }

    // DFS를 이용하여 장애물을 설치하는 메소드
    public static boolean dfs(int count) {
        if (count == 3) {  // 장애물 3개 설치 완료
            return !process(); // 학생이 감지되지 않으면 true 반환
        }

        for (int i = 0; i < spaces.size(); i++) {
            int x = spaces.get(i).getX();
            int y = spaces.get(i).getY();
            if (board[x][y] == 'X') {  // 빈 공간에만 설치
                board[x][y] = 'O'; // 장애물 설치
                if (dfs(count + 1)) {
                    return true;  // 학생들이 모두 숨을 수 있는 경우 true 반환
                }
                board[x][y] = 'X';  // 설치를 되돌리기
            }
        }
        return false; // 가능한 경우를 찾지 못하면 false 반환
    }

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
