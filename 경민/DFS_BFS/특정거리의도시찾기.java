package DFS_BFS;

import java.util.*;

public class 특정거리의도시찾기 {

    public static void main(String[] args) {
        // 도시의 개수, 도로의 개수, 거리 정보, 출발 도시 번호
        int n, m, k, x;
        // 각 도시마다 연결된 도시 목록을 저장
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        // 모든 도시에 대한 최단 거리 초기화
        int[] d = new int[300001];

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 도시의 개수
        m = sc.nextInt(); // 도로의 개수
        k = sc.nextInt(); // 목표 거리
        x = sc.nextInt(); // 출발 도시

        // 그래피 및 최단 거리 테이블 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>()); // 각 도시에 빈 리스트 추가
            d[i] = -1; // 모든 도시의 최단 거리를 -1로 초기화 (방문하지 않은 상태)
        }

        // 모든 도로 정보 입력 받기
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt(); // 출발 도시
            int b = sc.nextInt(); // 도착 도시
            graph.get(a).add(b); // a 도시에서 b 도시로 가는 도로 추가
        }

        // 출발 도시까지의 거리는 0으로 설정
        d[x] = 0;

        // 너비 우선 탐색(BFS) 수행
        Queue<Integer> q = new LinkedList<>();
        q.offer(x); // 출발 도시를 큐에 추가
        while (!q.isEmpty()) {
            int now = q.poll(); // 현재 도시를 큐에서 꺼냄
            // 현재 도시에서 이동할 수 있는 모든 도시를 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int nextNode = graph.get(now).get(i); // 다음 이동할 도시
                // 아직 방문하지 않은 도시라면
                if (d[nextNode] == -1) {
                    // 최단 거리를 현재 도시의 거리 +1로 갱신
                    d[nextNode] = d[now] + 1;
                    q.offer(nextNode); // 다음 도시를 큐에 추가
                }
            }
        }

        // 최단 거리가 K인 모든 도시의 번호를 오름차순으로 출력
        boolean check = false; // 최단 거리가 K인 도시가 있는지 확인
        for (int i = 1; i <= n; i++) {
            if (d[i] == k) { // 최단 거리가 K인 경우
                System.out.println(i); // 도시 번호 출력
                check = true;
            }
        }

        // 만약 최단 거리가 K인 도시가 없다면, -1 출력
        if (!check) {
            System.out.println(-1);
        }
    }
}