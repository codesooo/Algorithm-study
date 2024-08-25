package 지연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.530 특정 거리의 도시 찾기 / 난이도 중
 * 백준 실버2 https://www.acmicpc.net/problem/18352
 */
public class DFS_BFS_특정거리의도시찾기 {
    static int n, m, k, x;
    static List<List<Integer>> cityList;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 도시 개수
        m = Integer.parseInt(st.nextToken()); // 도로 개수
        k = Integer.parseInt(st.nextToken()); // 거리 정보
        x = Integer.parseInt(st.nextToken()); // 출발 도시 번호

        cityList = new ArrayList<>();
        distance = new int[n + 1];

        // 도시 초기화, 최단 거리 초기화
        for (int i = 0; i <= n; i++) {
            cityList.add(new ArrayList<>());
            distance[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cityList.get(a).add(b);
        }

        // 너비 우선 탐색
        bfs();

        StringBuilder sb = new StringBuilder();
        // 최단 거리가 k인 곳 모두 출력
        for (int i = 1; i <= n; i++) {
            if (distance[i] == k) {
                sb.append(i).append("\n");
            }
        }
        System.out.println(sb.length() == 0 ? -1 : sb.toString());
    }

    private static void bfs() {
        // 출발 도시까지의 거리는 0으로 설정
        distance[x] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            // 현재 도시에서 이동할 수 있는 모든 거리 탐색
            for (int i = 0; i < cityList.get(now).size(); i++) {
                int nextNode = cityList.get(now).get(i);
                // 방문하지 않은 곳일경우
                if (distance[nextNode] == -1) {
                    queue.offer(nextNode);
                    // 최단 거리 갱신
                    distance[nextNode] = distance[now] + 1;
                }
            }
        }
    }
}

/*
이 문제는 BFS(너비 우선 탐색)으로 풀이할 수 있다.
1. 시작점을 기준으로 모든 도시의 최단 거리를 계산 한다.
2. 모든 도시의 최단 거리와 최단 거리 정보가 일치한 곳을 출력한다.
 */