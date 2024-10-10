package 지연.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.385 플로이드 / 난이도 중
 * 백준 골드4 https://www.acmicpc.net/problem/11404
 */
public class 최단경로_플로이드 {
    final static int INF = (int)1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[][] graph = new int[101][101];
        // 최단 거리 테이블 모두 무한으로 초기화
        for (int i = 0; i < 101; i++) {
            Arrays.fill(graph[i], INF);
        }

        // 자기 자신에서 자기 자신으로 가는 비용은 0으로 초기화
        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                if(a == b) graph[a][b] = 0;
            }
        }

        // 각 간선에 대한 정보를 입력받아, 그 값으로 초기화
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            // A에서 B로 가는 비용은 C
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 가장 짧은 간선 정보만 저장
            if( c < graph[a][b]) graph[a][b] = c;
        }

        // 점화식에 따라 플로이드 워셜 알고리즘 실행
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }

        // 결과 출력
        for (int a = 1; a <= n ; a++) {
            for (int b = 1; b <= n ; b++) {
                if(graph[a][b] == INF) System.out.print("0 ");
                else System.out.print(graph[a][b] + " ");
            }
            System.out.println();
        }
    }
}

/*
전형적인 최단 경로 문제이다.
주의할 점은 시작도시 A와 도착도시 B를 연결하는 간선이 여라개일 수 있다는 점이다.
도시의 개수 n이 100 이하의 정수이기 떄문에 플로이드 워셜 알고리즘을 이용할 수 있다.
그러므로 가장 짧은 간선 정보만 저장한 후 플로이드 워셜 알고리즘 사용할면 된다.
 */