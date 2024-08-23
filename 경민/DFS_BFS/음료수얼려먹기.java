package DFS_BFS;

import java.util.*;

public class 음료수얼려먹기 {

    public static int n, m; // 얼음 틀의 세로 길이(n)와 가로 길이(m)를 나타내는 변수
    public static int[][] graph; // 얼음 틀의 구조를 저장할 2차원 배열 (0: 방문하지 않은 칸, 1: 방문한 칸 또는 벽)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력 받기
        n = sc.nextInt(); // 세로 길이 입력
        m = sc.nextInt(); // 가로 길이 입력
        sc.nextLine(); // 버퍼 지우기

        // 2차원 배열의 맵 정보 입력 받기
        graph = new int[n][m]; // 입력된 크기에 맞게 2차원 배열 초기화
        for (int i = 0; i < n; i++) { // 각 행에 대해 반복
            String str = sc.nextLine(); // 한 줄씩 입력받기
            for (int j = 0; j < m; j++) { // 각 열에 대해 반복
                // 문자로 입력받은 값을 정수로 변환하여 2차원 배열에 저장
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        // 모든 노드(위치)에 대하여 DFS를 통해 연결된 요소들 찾기
        int result = 0; // 결과 값 (아이스크림 개수)
        for (int i = 0; i < n; i++) { // 행을 순회
            for (int j = 0; j < m; j++) { // 열을 순회
                // 현재 위치에서 DFS 수행 (아직 방문하지 않은 부분이라면)
                if (dfs(i, j)) {
                    result += 1; // 하나의 아이스크림이 완성되었으므로 결과 값 1 증가
                }
            }
        }
        System.out.println(result); // 정답 출력 (아이스크림의 총 개수)
    }

    // DFS로 특정 노드를 방문하고 연결된 모든 노드들도 방문
    public static boolean dfs(int x, int y) {
        // 주어진 범위를 벗어나는 경우에는 즉시 종료 (유효하지 않은 위치)
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        // 현재 노드를 아직 방문하지 않았다면 (0인 경우)
        if (graph[x][y] == 0) {
            // 해당 노드 방문 처리 (1로 변경하여 방문 처리)
            graph[x][y] = 1;
            // 상, 하, 좌, 우의 위치들도 모두 재귀적으로 호출하여 방문
            dfs(x - 1, y); // 상
            dfs(x, y - 1); // 좌
            dfs(x + 1, y); // 하
            dfs(x, y + 1); // 우
            return true; // 현재 위치에서 시작한 DFS가 하나의 아이스크림을 형성
        }
        return false; // 이미 방문한 경우나 벽인 경우
    }
}
