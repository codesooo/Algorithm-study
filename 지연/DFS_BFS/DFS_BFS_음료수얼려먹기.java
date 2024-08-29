package 지연.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.149 음료수 얼려먹기 / 난이도 중
 */
public class DFS_BFS_음료수얼려먹기 {
    static int n, m;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        // dfs
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        
        
        // graph 셋팅
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = s.charAt(j) - '0';
            }
        }


        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(i, j)) {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static boolean dfs(int x, int y) {
        // 범위 벗어날 경우 종료
        if(x < 0 || x >= n || y < 0 || y >= m){
            return false;
        }

        // 현재 노드 방문하지 않았다면
        if (graph[x][y] == 0) {
            graph[x][y] = 1; // 방문처리

            // 상하좌우 확인
            dfs(x, y - 1);
            dfs(x, y + 1);
            dfs(x - 1, y);
            dfs(x + 1, y);

            return true;
        }
        return false;
    }
}

/*
이 문제는 DFS를 이용해서 해결 할 수 있다.
상하좌우를 모두 재귀적으로 호출 해 방문하지 않은 곳을 확인한다.
 */