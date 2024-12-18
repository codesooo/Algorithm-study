package 지연.삼성기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.404 청소년 상어 / 난이도 중상
 * 백준 골드2 https://www.acmicpc.net/problem/19236
 */
public class 삼성기출_청소년상어 {
    // 0 부터 7 까지 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        // input
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                Fish f = new Fish();
                f.id = Integer.parseInt(st.nextToken());
                f.dir = Integer.parseInt(st.nextToken()) - 1;
                f.x = i;
                f.y = j;

                fishes.add(f);
                arr[i][j] = f.id;
            }
        }

        // 물고기는 작은 순서부터 이동해야 하기 때문에 미리 정렬해둠
        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.id - o2.id;
            }
        });

        // 상어는 (0, 0) 물고기를 먹고 시작하며 위치는 -1 로 표현
        Fish f = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, f.dir, f.id);
        f.isAlive = false;
        arr[0][0] = -1;

        // solution
        dfs(arr, shark, fishes);
        System.out.println(maxSum);
    }
    // 재귀로 상어가 이동할 수 없을 때까지 반복한다.
    static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        // 잡아먹은 양의 최대값 구하기
        if (maxSum < shark.eatSum) {
            maxSum = shark.eatSum;
        }

        // 모든 물고기 순서대로 이동
        fishes.forEach(e -> moveFish(e, arr, fishes));

        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.dir] * dist;
            int ny = shark.y + dy[shark.dir] * dist;

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > 0) {
                // 물고기 잡아먹고 dfs
                int[][] arrCopies = copyArr(arr);
                List<Fish> fishCopies = copyFishes(fishes);

                arrCopies[shark.x][shark.y] = 0;
                Fish f = fishCopies.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(f.x, f.y, f.dir, shark.eatSum + f.id);
                f.isAlive = false;
                arrCopies[f.x][f.y] = -1;

                dfs(arrCopies, newShark, fishCopies);
            }
        }
    }

    // 이동가능한 칸은 빈칸, 다른 물고기가 있는 칸
    // 45도 반시계 방향으로 회전하면서 스캔
    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (fish.isAlive == false) return;

        for (int i = 0; i < 8; i++) {
            int nextDir = (fish.dir + i) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;

                if (arr[nx][ny] == 0) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.id;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.id;
                fish.dir = nextDir;
                return;
            }
        }
    }
    static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    static List<Fish> copyFishes(List<Fish> fishes) {
        List<Fish> temp = new ArrayList<>();
        fishes.forEach(e -> temp.add(new Fish(e.x, e.y, e.id, e.dir, e.isAlive)));
        return temp;
    }

    static class Shark {
        int x, y, dir, eatSum;

        Shark() { }

        Shark(int x, int y, int dir, int eatSum) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatSum = eatSum;
        }
    }

    static class Fish {
        int x, y, id, dir;
        boolean isAlive = true;

        Fish() { }

        Fish(int x, int y, int id, int dir, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }
}

/*
백트래킹을 이용해서 상어가 이동하는 모든 경우의 수를 구하면 됩니다.
상어는 한 방향밖에 가지 못하기 때문에 4 x 4 배열에서 이동하는 경우의 수는 최대 3 개입니다.
물고기가 먼저 이동해야 하기 때문에 DFS 시작하자마자 물고기를 전부 이동시키고 이동 가능한 경우의 수를 전부 확인하여 다시 DFS 를 들어갑니다.
원래 백트래킹을 할 때는 빠져 나오면서 값들을 원래 값으로 돌려놓아야 하는데
배열이나 물고기 리스트들이 너무 바뀌는게 많기 때문에 다음 DFS 로 넘기는 값들은 전부 복사한 새로운 값으로 했습니다.
그래서 DFS 를 빠져나온 후에도 기존 값들을 갖고 다음 경우의 수를 확인할 수 있습니다.

- 주의
물고기들은 번호 순서대로 이동해야 합니다. 물고기가 순서대로 주어지지 않기 때문에 정렬이 필요합니다.
상어는 물고기가 있는 공간으로만 이동할 수 있습니다.
물고기는 빈칸 또는 또다른 물고기가 있는 곳으로만 이동할 수 있습니다.
상어나 물고기가 이동한 후에는 이동하기 전의 공간 뒷처리를 잘 해줘야합니다.

 */