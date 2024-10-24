package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.303 커리큘럼 / 난이도 상
 */
public class 그래프이론_커리큘럼 {
    public static int v;
    public static int[] indegree;
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public static int[] times;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());

        indegree = new int[v + 1];
        times = new int[v + 1];


        // 그래프 초기화
        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        // 방향 그래프의 모든 간선 정보를 입력받기
        for (int i = 1; i <= v; i++) {
            st = new StringTokenizer(br.readLine());
            // 첫 번째 수는 시간 정보를 담고 있음
            int x = Integer.parseInt(st.nextToken());
            times[i] = x;
            // 해당 강의를 듣기 위해 먼저 들어야하는 강의들의 번호 입력
            while (true) {
                x = Integer.parseInt(st.nextToken());
                if (x == -1) {
                    break;
                }
                indegree[i] += 1;
                graph.get(x).add(i);
            }
        }
        topologySort();
    }

    // 위상 정렬 함수
    private static void topologySort() {
        // 알고리즘 수행 결과를 담을 배열
        int[] result = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            result[i] = times[i];
        }

        Queue<Integer> queue = new LinkedList<>();

        // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
        for (int i = 1; i <= v; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        // 큐가 빌때까지 반복
        while (!queue.isEmpty()) {
            int now = queue.poll();

            // 해당 원소와 연결된 노드들의 진입차수에서 1 뺴기
            for (int next : graph.get(now)) {
                indegree[next]--;
                result[next] = Math.max(result[next], result[now] + times[next]);
                // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int i = 1; i <= v; i++) {
            System.out.println(result[i]);
        }

    }
}

/*
이 문제는 위상 정렬 알고리즘의 응용 문제이다.
각 노드에 대하여 인접한 노드를 확인할 때, 인접한 노드에 대하여 현재보다 강의 시간이 더 긴 경우를 찾는다면
더 오랜 시간이 걸리는 경의 시간 값을 저장하는 방식으로 결과 테이블을 갱신하여 답을 구할 수 있다.
따라서 위상 정렬을 수행하면서 매번 간선 정보를 확인하여 결과 테이블을 갱신한다.
 */