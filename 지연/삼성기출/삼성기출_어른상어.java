package 지연.삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.407 어른 상어 / 난이도 중상
 * 백준 골드1 https://www.acmicpc.net/problem/19237
 */
public class 삼성기출_어른상어 {
    // 1, 2, 3, 4 는 각각 위, 아래, 왼쪽, 오른쪽
    static class Shark {
        int id, x, y, dir;
        int[][] priority = new int[5][5];

        Shark() { }

        int findNextDir(Set<Integer> candidates) {
            for (int i = 1; i < 5; i++) {
                if (candidates.contains(priority[dir][i])) {
                    return priority[dir][i];
                }
            }
            return 0;
        }
    }

    static int N, M, k;
    static int[][] arr = new int[21][21];
    static int[][] smellOwner = new int[21][21];
    static int[][] leftTime = new int[21][21];
    static Map<Integer, Shark> sharks = new HashMap<>();
    static int time = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] > 0) {
                    Shark s = new Shark();
                    s.id = arr[i][j];
                    s.x = i;
                    s.y = j;
                    sharks.put(s.id, s);

                    smellOwner[i][j] = s.id;
                    leftTime[i][j] = k;
                }
            }
        }

        // direction of sharks
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            Shark s = sharks.get(i + 1);
            s.dir = Integer.parseInt(st.nextToken());
        }

        // priority of sharks
        for (int i = 0; i < M; i++) {
            Shark s = sharks.get(i + 1);

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());

                for (int z = 0; z < 4; z++) {
                    s.priority[j + 1][z + 1] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // solution
        solution();
    }

    static void solution() {
        while (time++ < 1000) {
            moveShark();
            decreaseSmellTime();
            createSmell();

            if (sharks.size() == 1) {
                System.out.println(time);
                return;
            }
        }

        System.out.println(-1);
    }

    // 상어 이동 후 겹친 애 쫓아내기
    static void moveShark() {
        // dx dy 는 위, 아래, 왼쪽, 오른쪽 순서
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Integer> willRemove = new LinkedList<Integer>();

        for (Integer id : sharks.keySet()) {
            Set<Integer> noSmellSet = new HashSet<>();
            Set<Integer> mySmellSet = new HashSet<>();
            Shark s = sharks.get(id);

            for (int i = 0; i < 4; i++) {
                int nx = s.x + dx[i];
                int ny = s.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (smellOwner[nx][ny] == 0) {
                    noSmellSet.add(i + 1);
                } else if (smellOwner[nx][ny] == s.id) {
                    mySmellSet.add(i + 1);
                }
            }

            // 냄새 없는 곳부터 스캔하고 자기 냄새 있는 곳을 스캔해서 이동할 방향 구하기
            int nextDir = s.findNextDir(noSmellSet);

            if (nextDir == 0) {
                nextDir = s.findNextDir(mySmellSet);
            }

            // 상어 이동
            arr[s.x][s.y] = 0;
            if (nextDir == 1) {
                s.x -= 1;
            } else if (nextDir == 2) {
                s.x += 1;
            } else if (nextDir == 3) {
                s.y -= 1;
            } else if (nextDir == 4) {
                s.y += 1;
            }

            // 이동할 위치에 상어 있으면 경쟁 후 작은 번호가 승리
            if (arr[s.x][s.y] == 0 || s.id < arr[s.x][s.y]) {
                arr[s.x][s.y] = s.id;
                s.dir = nextDir;
            } else {
                willRemove.add(s.id);
            }
        }

        while (!willRemove.isEmpty()) {
            sharks.remove(willRemove.poll());
        }
    }

    // 맵 전체의 냄새 시간을 하나씩 감소 시키고 0 이 되면 냄새 주인 정보도 지움
    static void decreaseSmellTime() {
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (leftTime[i][j] == 0) continue;

                leftTime[i][j]--;

                if (leftTime[i][j] == 0) {
                    smellOwner[i][j] = 0;
                }
            }
        }
    }

    // 상어가 이동한 곳에 새로운 냄새 생성
    static void createSmell() {
        for (Integer id : sharks.keySet()) {
            Shark s = sharks.get(id);

            smellOwner[s.x][s.y] = s.id;
            leftTime[s.x][s.y] = k;
        }
    }
}
/*
1번 상어가 남거나 1000 초가 지날 때까지 다음 순서대로 반복하면 됩니다.
1. moveShark() : 상어 이동
    - 다음 방향 계산
    - 이동
    - 경쟁을 통해 작은 번호의 상어 생존
2. decreaseSmellTime() : 모든 냄새들 1 씩 감소
3. createSmell() : 상어가 이동한 자리에 새로운 냄새 생성

* 주의
- 상어가 이동한 후에는 상어의 방향을 이동한 방향으로 바꿔주어야 합니다.
- 위 순서에서 2번과 3번을 바꾸게 되면 상어가 새로운 냄새를 생성하자마자 1 씩 깎이기 때문에 순서를 지켜야 합니다.
- 상어 리스트를 for 문으로 돌면서 경쟁에서 패배한 상어를 바로 제거하니 런타임 에러가 발생했습니다. for 문 도중에는 요소를 삭제하지 말고 기록해두었다가 나중에 삭제합니다.
 */