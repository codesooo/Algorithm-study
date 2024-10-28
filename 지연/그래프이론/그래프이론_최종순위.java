package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.399 최종 순위 / 난이도 상
 * 백준 골드1 https://www.acmicpc.net/problem/3665
 */
public class 그래프이론_최종순위 {
    // 모든 노드에 대한 진입차수는 0으로 초기화
    public static int[] indegree = new int[501];
    // 각 노드에 연결된 간선 정보를 담기 위한 배열 초기화
    public static boolean[][] graph = new boolean[501][501];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        StringTokenizer st;

        while (t-- > 0) { // 테스트 케이스 반복
            // 그래프와 진입차수 초기화 (각 테스트 케이스마다)
            Arrays.fill(indegree, 0);
            for (int i = 0; i < 501; i++) {
                Arrays.fill(graph[i], false);
            }

            int n = Integer.parseInt(br.readLine()); // 팀 수
            ArrayList<Integer> arrayList = new ArrayList<>();

            // 작년 순위 입력 (n개의 팀 번호가 공백으로 구분되어 한 줄에 입력됨)
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(st.nextToken());
                arrayList.add(x);
            }

            // 방향 그래프와 간선 정보 설정
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int a = arrayList.get(i);
                    int b = arrayList.get(j);
                    graph[a][b] = true;
                    indegree[b]++;
                }
            }

            int m = Integer.parseInt(br.readLine()); // 변경된 순위 쌍의 수
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 간선 방향 뒤집기
                if (graph[a][b]) {
                    graph[a][b] = false;
                    graph[b][a] = true;
                    indegree[a]--;
                    indegree[b]++;
                } else {
                    graph[a][b] = true;
                    graph[b][a] = false;
                    indegree[a]++;
                    indegree[b]--;
                }
            }

            // 위상 정렬(Topology Sort) 시작
            ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과
            Queue<Integer> queue = new LinkedList<>();

            // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            boolean certain = true; // 위상 정렬 결과가 오직 하나인지의 여부
            boolean cycle = false; // 그래프 내 사이클이 존재하는지 여부

            // 노드 개수만큼 반복
            for (int i = 0; i < n; i++) {
                // 큐가 비어있다면 사이클이 발생했다는 의미
                if (queue.isEmpty()) {
                    cycle = true;
                    break;
                }
                // 큐의 원소가 2개 이상이라면 가능한 정렬 결과가 여러 개라는 의미
                if (queue.size() >= 2) {
                    certain = false;
                    break;
                }

                // 큐에서 원소 꺼내기
                int now = queue.poll();
                result.add(now);
                // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
                for (int j = 1; j <= n; j++) {
                    if (graph[now][j]) {
                        indegree[j]--;
                        // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                        if (indegree[j] == 0) {
                            queue.offer(j);
                        }
                    }
                }
            }

            // 결과 출력
            if (cycle) {
                System.out.println("IMPOSSIBLE");
            } else if (!certain) {
                System.out.println("?");
            } else {
                for (int num : result) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    }
}

/*
이 문제는 작년 순위와 상대적으로 순위가 바뀐 팀들의 목록이 주어졌을 때 올해 순위를 만들어야한다.
즉, 정해진 우선순위에 맞게 전체 팀들의 순서를 나열해야하는 점에서 위상 정렬 알고리즘을 떠올릴 수 있어야한다.
따라서 팀 간의 순위 정보를 그래프 정보로 표현한 뒤에 위상 정렬 알고리즘을 이용해 해결할 수 있다.
 */