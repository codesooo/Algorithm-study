package 지연.삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.402 아기 상어 / 난이도 중
 * 백준 골드3 https://www.acmicpc.net/problem/16236
 */
public class 삼성기출_아기상어 {
    public static int n, sharkRow, sharkCol, sharkSize = 2, time, eatCnt = 0;
    public static int[][] map;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 아기 상어 위치 초기화
                if (map[i][j] == 9) {
                    sharkRow = i;
                    sharkCol = j;
                    // 상어가 있던 자리는 빈 칸으로 만들기
                    map[i][j] = 0;
                }
            }
        }

        int timeSum = 0; // 총 걸린 시간 저장
        // 상어가 먹을 수 있는 물고기가 있을 때까지 반복
        while (sharkGo()) {
            timeSum += time; // 이동할 때마다 시간을 더하기
        }

        System.out.println(timeSum);
    }

    private static boolean sharkGo() {
        time = 0;
        // 먹은 물고기의 수가 몸의 크기와 같아지면 몸의 크기 증가
        if (eatCnt == sharkSize) {
            eatCnt = 0;
            sharkSize++;
        }

        boolean[][] visited = new boolean[n][n]; // 방문여부
        Queue<Node> queue = new LinkedList<>(); // BFS를 위한 큐
        // 초기 위치에서 BFS 시작
        queue.offer(new Node(sharkRow, sharkCol, 0));
        visited[sharkRow][sharkCol] = true; // 초기 위치 방문 처리

        // 먹을 물고기의 최소 위치와 최소 시간을 저장
        int minRow = Integer.MAX_VALUE;
        int minCol = Integer.MAX_VALUE;
        int minTime = Integer.MAX_VALUE;

        // BFS 시작
        while (!queue.isEmpty()) {
            Node a = queue.poll();
            // 최소 시간으로 물고기를 먹을 수 있는 시간을 넘으면 종료
            if(a.time > minTime) break;

            // 상 하 좌 우 이동
            for (int i = 0; i < 4; i++) {
                int nx = a.row + dx[i];
                int ny = a.col + dy[i];

                // 범위 벗어나면 무시
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                // 이미 방문했거나 상어 크기보다 큰 물고기가 있는 경우 무시
                if(visited[nx][ny] || map[nx][ny] > sharkSize) continue;

                // 아기 상어가 먹을 수 있는 물고기가 있는 칸에 들어옴
                if(map[nx][ny] > 0 && map[nx][ny] < sharkSize) {
                    // 기존 최소 시간보다 빠르게 도착한 경우 업데이트
                    if(a.time + 1 < minTime) {
                        minTime = a.time + 1;
                        minRow = nx;
                        minCol = ny;
                    } else if (a.time + 1 == minTime) {
                        // 시간이 같은 경우에는 위쪽, 왼쪽 우선 탐색
                        if (nx < minRow || (nx == minRow && ny < minCol)) {
                            minRow = nx;
                            minCol = ny;
                        }
                    }
                }
                // 큐에 다음 탐색 시작할 위치 추가
                queue.offer(new Node(nx, ny, a.time + 1));
                visited[nx][ny] = true;
            }
        }

        // 먹을 물고기 없으면 종료
        if(minTime == Integer.MAX_VALUE) return false;

        // 먹을 물고기가 있으면 상어의 위치를 이동하고 시간을 기록
        time = minTime;
        map[minRow][minCol] = 0; // 물고기를 먹었으므로 그 칸은 빈칸이 됨
        // 상어의 위치를 먹은 물고기의 위치로 갱신
        sharkRow = minRow;
        sharkCol = minCol;
        eatCnt++; // 먹은 물고기의 수 증가
        // 더 먹을 물고기가 있으면 true 반환
        return true;
    }

    static class Node {
        int row, col, time;

        public Node(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
/*
아기 상어는 먹을 수 있는 물고기 중에서 가장 가까운 물고기를 먼저 먹는다고 했기 때문에
가장 가까운 물고기는 최단 거리 알고리즘을 이용해서 찾을 수 있다. 따라서 BFS를 이용하여 최단 거리를 찾으면 된다.

매번 현재 위치에서 도달 가능한 다른 모든 위치까지의 최단 거리를 구한 뒤에, 먹을 물고기의 위치를 찾는 과정을 반복한다.
1. 자신의 크리보다 큰 물고기가 있는 칸은 지나갈 수 없다.
2. 자신의 크기보다 작은 물고기만 먹을 수 있다
이 점을 주위해서 구현하면 된다.
 */