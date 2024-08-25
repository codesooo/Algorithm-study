package 지연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.344 경쟁적 전염 / 난이도 중
 * 백준 골드5 https://www.acmicpc.net/problem/18405
 */
public class DFS_BFS_경쟁적전염 {
    static int n, k;
    static int[][] arr;
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 시험관 크기
        k = Integer.parseInt(st.nextToken()); // 바이러스 종류

        arr = new int[n][n];
        List<Virus> virusList = new ArrayList<>();
        // 시험관 맵 생성, 바이러스 위치를 리스트에 추가
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    virusList.add(new Virus(arr[i][j], i, j, 0));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); // 초
        int x = Integer.parseInt(st.nextToken()); // x 위치
        int y = Integer.parseInt(st.nextToken()); // y 위치

        // 바이러스를 낮은 순으로 정렬
        Collections.sort(virusList);
        bfs(virusList);

        System.out.println(arr[x - 1][y - 1]);
    }

    private static void bfs(List<Virus> virusList) {
        Queue<Virus> queue = new LinkedList<>(virusList);
        while (!queue.isEmpty()) {
            Virus virus = queue.poll();
            if (virus.time == s) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (arr[nx][ny] == 0){
                   arr[nx][ny] = virus.type;
                   queue.add(new Virus(virus.type, nx, ny, virus.time + 1));
                }
            }
        }
    }

    private static class Virus implements Comparable<Virus> {
        int type, x, y, time;

        public Virus(int type, int x, int y, int time) {
            this.type = type;
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Virus o) {
            // 바이러스 번호가 낮은 순 정렬
            return this.type - o.type;
        }
    }
}

/*
이 문제는 bfs를 이용하여 풀 수 있다.
BFS 사용 이유
    - 시간 순서 보장: BFS는 각 레벨(시간 단위)별로 탐색을 진행하기 때문에, 바이러스가 퍼지는 시간을 정확히 반영할 수 있다.
    - 동시 전파 처리: 여러 바이러스가 동시에 퍼질 때, BFS는 이를 자연스럽게 처리할 수 있다.
 */