package 삼성전자기출문제;

import java.util.*;

public class 어른상어 {
    static int n, m, k;
    static int[][] array;
    static int[] directions;
    static int[][][] smell;
    static int[][][] priorities;

    // 4가지 이동 방향 정의 (상, 하, 좌, 우)
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // n, m, k 입력 받기
        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();

        // 상어의 위치 및 방향 정보 저장할 배열
        array = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        // 상어의 현재 방향 입력
        directions = new int[m];
        for (int i = 0; i < m; i++) {
            directions[i] = scanner.nextInt() - 1; // 0부터 시작하도록 조정
        }

        // 각 위치에 냄새 정보 저장할 배열 (특정 냄새의 상어 번호, 냄새 남은 시간)
        smell = new int[n][n][2];

        // 상어의 우선순위 정보 저장할 배열
        priorities = new int[m][4][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                for (int l = 0; l < 4; l++) {
                    priorities[i][j][l] = scanner.nextInt() - 1; // 우선순위 입력
                }
            }
        }

        int time = 0;
        while (true) {
            updateSmell(); // 모든 위치의 냄새 업데이트
            array = move(); // 모든 상어 이동

            time++; // 시간 증가

            // 1번 상어만 남았는지 확인
            if (checkOnlyOneShark()) {
                System.out.println(time);
                break;
            }

            // 1000초가 지나도 끝나지 않으면
            if (time >= 1000) {
                System.out.println(-1);
                break;
            }
        }
    }

    // 냄새 정보를 업데이트하는 함수
    public static void updateSmell() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 냄새가 존재하는 경우, 시간 1 감소
                if (smell[i][j][1] > 0) {
                    smell[i][j][1]--;
                }
                // 상어가 있는 곳은 새로운 냄새 생성
                if (array[i][j] != 0) {
                    smell[i][j][0] = array[i][j];
                    smell[i][j][1] = k;
                }
            }
        }
    }

    // 상어 이동을 처리하는 함수
    public static int[][] move() {
        int[][] newArray = new int[n][n]; // 새로운 배열 생성

        // 모든 상어 위치 확인
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (array[x][y] != 0) {
                    int shark = array[x][y]; // 현재 상어 번호
                    int direction = directions[shark - 1]; // 상어의 현재 방향
                    boolean found = false;

                    // 냄새가 없는 곳 우선 이동
                    for (int i = 0; i < 4; i++) {
                        int nx = x + dx[priorities[shark - 1][direction][i]];
                        int ny = y + dy[priorities[shark - 1][direction][i]];
                        if (nx >= 0 && nx < n && ny >= 0 && ny < n && smell[nx][ny][1] == 0) {
                            directions[shark - 1] = priorities[shark - 1][direction][i];
                            if (newArray[nx][ny] == 0) {
                                newArray[nx][ny] = shark;
                            } else {
                                newArray[nx][ny] = Math.min(newArray[nx][ny], shark);
                            }
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        // 모든 방향에 냄새가 있을 경우, 자신의 냄새가 있는 곳으로 이동
                        for (int i = 0; i < 4; i++) {
                            int nx = x + dx[priorities[shark - 1][direction][i]];
                            int ny = y + dy[priorities[shark - 1][direction][i]];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && smell[nx][ny][0] == shark) {
                                directions[shark - 1] = priorities[shark - 1][direction][i];
                                newArray[nx][ny] = shark;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return newArray;
    }

    // 1번 상어만 남았는지 확인하는 함수
    public static boolean checkOnlyOneShark() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i][j] > 1) {
                    return false;
                }
            }
        }
        return true;
    }
}