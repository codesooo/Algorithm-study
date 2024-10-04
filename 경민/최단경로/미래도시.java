package 최단경로;

import java.util.*;

public class 미래도시 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 노드의 개수(N)
        int m = scanner.nextInt(); // 간선의 개수(M)

        int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
        int[][] graph = new int[101][101]; // 2차원 배열(그래프 표현)를 만들기

        // 최단 거리 테이블을 모두 무한으로 초기화
        for (int i = 0; i < 101; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if (a == b) graph[a][b] = 0;
            }
        }

        // 각 간선에 대한 정보를 입력 받아, 그 값으로 초기화
        for (int i = 0; i < m; i++) {
            // A와 B가 서로에게 가는 비용은 1이라고 설정
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        int x = scanner.nextInt(); // 거쳐 갈 노드(X)
        int k = scanner.nextInt(); // 최종 목적지 노드(K)

        // 점화식에 따라 플로이드 워셜 알고리즘을 수행
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        // 수행된 결과를 출력
        int distance = graph[1][k] + graph[k][x];

        // 도달할 수 없는 경우, -1을 출력
        if (distance >= INF) {
            System.out.println(-1);
        }
        // 도달할 수 있다면, 최단 거리를 출력
        else {
            System.out.println(distance);
        }
    }
}