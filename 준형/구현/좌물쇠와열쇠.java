package 구현;

public class 좌물쇠와열쇠 {

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};

        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(solution(key, lock));
    }

    public static boolean solution(int[][] key, int[][] lock) {
        int lockLineLength = lock.length;
        int keyLineLength = key.length;

        // 확장된 잠금 배열의 크기
        int extendedSize = lockLineLength + 2 * (keyLineLength - 1);
        int[][] extendedLock = new int[extendedSize][extendedSize];

        // 확장된 배열의 중앙에 lock을 배치
        for (int i = 0; i < lockLineLength; i++) {
            for (int j = 0; j < lockLineLength; j++) {
                extendedLock[i + keyLineLength - 1][j + keyLineLength - 1] = lock[i][j];
            }
        }

        // 4가지 회전 방향에 대해 검사
        for (int times = 0; times < 4; times++) {
            int[][] matrix90Degree = rotateMatrixBy90Degree(key);

            // 확장된 배열에 키를 모든 위치에 맞춰보는 루프
            for (int x = 0; x <= extendedSize - keyLineLength; x++) {
                for (int y = 0; y <= extendedSize - keyLineLength; y++) {
                    if (tryUnlock(matrix90Degree, extendedLock, x, y, lockLineLength, keyLineLength)) {
                        return true;
                    }
                }
            }

            key = matrix90Degree; // 회전된 키로 업데이트
        }

        return false;
    }

    public static boolean tryUnlock(int[][] key, int[][] extendedLock, int startX, int startY, int lockSize, int keySize) {
        // 잠금 배열의 복사본을 생성하여 시도
        int[][] testLock = new int[extendedLock.length][extendedLock[0].length];
        for (int i = 0; i < extendedLock.length; i++) {
            for (int j = 0; j < extendedLock[0].length; j++) {
                testLock[i][j] = extendedLock[i][j];
            }
        }

        // 키를 잠금 배열 위에 놓음
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                testLock[startX + i][startY + j] += key[i][j];
            }
        }

        // 잠금의 중앙 부분이 모두 1인지 확인
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (testLock[i + keySize - 1][j + keySize - 1] != 1) {
                    return false;
                }
            }
        }

        return true;
    }


    public static int[][] rotateMatrixBy90Degree(int[][] key) {
        // 원본 배열의 행 길이
        int n = key.length;
        // 원본 배열의 열 길이
        int m = key[0].length;
        // 결과 배열 (열과 행이 뒤바뀜)
        int[][] result = new int[m][n];
        // 각 행을 순회
        for (int i = 0; i < n; i++) {
            // 각 열을 순회
            for (int j = 0; j < m; j++) {
                // 90도 회전 변환
                result[j][n - i - 1] = key[i][j];
            }
        }
        return result;
    }
}
