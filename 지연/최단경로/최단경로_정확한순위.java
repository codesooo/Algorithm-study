package 지연.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.575 정확한 순위 / 난이도 중
 */
public class 최단경로_정확한순위 {
    public static void main(String[] args) throws IOException {
        final int INF = (int) 1e9;
        // 노드 개수, 간선 개수
        int n, m;
        int[][] graph = new int[501][501];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 501; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        // 각 간선에 대한 정보 입력 받아 그 값으로 초기화
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
        }

        // 점화식에 따라 플로이드 워셜 알고리즘 수행
        for (int k = 1; k <= n ; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int result = 0;
        // 각 학생을 번호에 따라 한 명씩 확인하며 도달 가능한지 체크
        for (int i = 1; i <= n; i++) {
            int cnt = 0;
            for (int j = 1; j <= n; j++) {
                if (graph[i][j] != INF || graph[j][i] != INF) {
                    cnt++;
                }
            }
            if (cnt == n) {
                result++;
            }
        }

        System.out.println(result);

    }
}

/*
이 문제는 최단 경로를 계산하는 문제이다.
성적이 낮은 학생이 성적이 높은 학생을 가리키는 방향 그래프로 표현할 수 있다.
A에서 B로 도달이 가능하다는 것은 A가 B보다 성적이 낮다는 의미이다.
따라서 A에서 B로 도달이 가능하거나, B에서 A로 도달이 가능하면 성적비교가 가능한 것이다.
플로이드 워셜 알고리즘을 이용하여 풀고, 모든 노드에 대해 다른 노드와 서로 도달이 가능한지를 체크하면된다.

 */