package 삼성전자기출문제;

import java.util.*;

public class 청소년상어 {
    static final int[][] dx = {{-1, -1, 0, 1, 1, 1, 0, -1}};
    static final int[][] dy = {{0, -1, -1, -1, 0, 1, 1, 1}};
    static int result = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 4 X 4 크기 격자에 존재하는 각 물고기의 번호와 방향 값을 담는 배열
        int[][][] array = new int[4][4][2];

        for (int i = 0; i < 4; i++) {
            // 매 줄마다 4마리의 물고기를 하나씩 확인하며 각 위치에 [물고기 번호, 방향] 저장
            for (int j = 0; j < 4; j++) {
                array[i][j][0] = scanner.nextInt(); // 물고기 번호
                array[i][j][1] = scanner.nextInt() - 1; // 물고기 방향 (0부터 시작하도록 조정)
            }
        }

        // DFS를 통해 모든 경우를 탐색하여 최종 결과 출력
        dfs(array, 0, 0, 0);
        System.out.println(result);
    }

    // 특정한 번호의 물고기 위치 찾기
    public static int[] findFish(int[][][] array, int index) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (array[i][j][0] == index) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    // 모든 물고기 이동시키기
    public static void moveAllFishes(int[][][] array, int nowX, int nowY) {
        // 1번부터 16번까지의 물고기를 차례대로 확인
        for (int i = 1; i <= 16; i++) {
            int[] position = findFish(array, i);
            if (position != null) {
                int x = position[0];
                int y = position[1];
                int direction = array[x][y][1];

                // 물고기가 이동할 수 있는지 8방향 확인
                for (int j = 0; j < 8; j++) {
                    int nx = x + dx[0][direction];
                    int ny = y + dy[0][direction];

                    if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !(nx == nowX && ny == nowY)) {
                        array[x][y][1] = direction; // 방향 설정
                        int[] temp = array[nx][ny];
                        array[nx][ny] = array[x][y];
                        array[x][y] = temp;
                        break;
                    }
                    direction = turnLeft(direction);
                }
            }
        }
    }

    // 왼쪽으로 회전된 결과 반환
    public static int turnLeft(int direction) {
        return (direction + 1) % 8;
    }

    // 상어가 이동할 수 있는 위치 찾기
    public static List<int[]> getPossiblePositions(int[][][] array, int nowX, int nowY) {
        List<int[]> positions = new ArrayList<>();
        int direction = array[nowX][nowY][1];

        // 현재 방향으로 계속 이동하며 물고기가 있는지 확인
        for (int i = 0; i < 4; i++) {
            nowX += dx[0][direction];
            nowY += dy[0][direction];

            if (nowX >= 0 && nowX < 4 && nowY >= 0 && nowY < 4 && array[nowX][nowY][0] != -1) {
                positions.add(new int[]{nowX, nowY});
            }
        }
        return positions;
    }

    // 모든 경우 탐색을 위한 DFS 함수
    public static void dfs(int[][][] array, int nowX, int nowY, int total) {
        int[][][] tempArray = new int[4][4][2];

        // 배열 복사 (깊은 복사)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempArray[i][j][0] = array[i][j][0];
                tempArray[i][j][1] = array[i][j][1];
            }
        }

        total += tempArray[nowX][nowY][0]; // 물고기 먹기
        tempArray[nowX][nowY][0] = -1; // 물고기 제거

        // 모든 물고기 이동
        moveAllFishes(tempArray, nowX, nowY);

        // 상어가 이동할 수 있는 위치 찾기
        List<int[]> positions = getPossiblePositions(tempArray, nowX, nowY);

        // 이동할 수 있는 위치가 없으면 종료
        if (positions.isEmpty()) {
            result = Math.max(result, total);
            return;
        }

        // 상어가 이동할 수 있는 모든 위치에 대해 재귀적으로 탐색
        for (int[] pos : positions) {
            dfs(tempArray, pos[0], pos[1], total);
        }
    }
}