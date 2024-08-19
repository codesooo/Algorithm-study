package 구현;

public class 자물쇠와열쇠 {

    public static void main(String[] args) {
        int[][] key = { {0, 0, 0}, {1, 0, 0}, {0, 1, 1}};

        int[][] lock = { {1, 1, 1}, {1, 1, 0}, {1, 0, 1} };

        System.out.println(solution(key, lock));
    }

        // 2차원 배열을 90도 회전시키는 함수
        public static int[][] rotateMatrixBy90Degree(int[][] a) {
            int n = a.length;
            int m = a[0].length;
            int[][] result = new int[n][m]; // 90도 회전된 배열을 저장할 배열

            // 배열의 각 원소를 90도 회전된 위치로 이동
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    result[j][n - i - 1] = a[i][j];
                }
            }
            return result;
        }

        // 열쇠가 자물쇠에 맞는지 확인하는 함수
        public static boolean match(int[][] key, int[][] lock, int x, int y) {
            int n = lock.length;
            int m = key.length;

            // 열쇠와 자물쇠를 맞춰보는 과정
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    int lockX = x + i; // 자물쇠의 x 좌표
                    int lockY = y + j; // 자물쇠의 y 좌표

                    // 열쇠의 현재 위치가 자물쇠 안에 있을 경우에만 확인
                    if (lockX >= 0 && lockX < n && lockY >= 0 && lockY < n) {
                        // 자물쇠와 열쇠의 값을 더했을 때 1이 아니라면 자물쇠를 잠글 수 없음
                        if (lock[lockX][lockY] + key[i][j] != 1) {
                            return false; // 자물쇠를 잠글 수 없으므로 false 반환
                        }
                    }
                }
            }
            return true; // 자물쇠를 정확히 잠글 수 있는 경우 true 반환
        }

        // 자물쇠와 열쇠가 맞는지 확인하는 함수
        public static boolean solution(int[][] key, int[][] lock) {
            int n = lock.length; // 자물쇠의 크기
            int m = key.length;  // 열쇠의 크기

            // 열쇠를 4가지 방향(0도, 90도, 180도, 270도)으로 회전시켜가며 확인
            for (int rotation = 0; rotation < 4; rotation++) {
                key = rotateMatrixBy90Degree(key); // 열쇠를 90도 회전

                // 자물쇠의 모든 위치에 열쇠를 맞춰보는 과정
                // 자물쇠의 바깥쪽에 있을 수도 있으므로, -m + 1부터 n까지 반복
                for (int x = -m + 1; x < n; x++) {
                    for (int y = -m + 1; y < n; y++) {
                        // 현재 위치에서 열쇠가 자물쇠에 맞는지 확인
                        if (match(key, lock, x, y)) {
                            return true; // 자물쇠를 잠글 수 있는 경우 true 반환
                        }
                    }
                }
            }
            return false; // 모든 경우의 수를 확인한 후에도 맞지 않으면 false 반환
        }

}
