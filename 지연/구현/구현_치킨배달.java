package 지연.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.332 치킨 배달 / 난이도 중
 * 백준 골드5 https://www.acmicpc.net/problem/15686
 */
public class 구현_치킨배달 {
    static int n, m;
    static List<Node> houseList; // 집의 위치
    static List<Node> chickenList; // 치킨집의 위치
    static int min = Integer.MAX_VALUE; // 최소 치킨 거리
    static boolean[] chickenVisited; // 치킨집 선택 여부
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        houseList = new ArrayList<>();
        chickenList = new ArrayList<>();

        int[][] map = new int[n][n];
        // 도시 맵 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 집 위치 저장
                if(map[i][j] == 1) houseList.add(new Node(i, j));
                // 치킨집 위치 저장
                else if (map[i][j] == 2) chickenList.add(new Node(i, j));
            }
        }

        chickenVisited = new boolean[chickenList.size()];
        backtracking(0, 0); // m개의 치킨집 뽑기

        System.out.println(min);

    }

    private static void backtracking(int count, int idx) {
        if (count == m) {   // 치킨 거리의 최솟값 구하기
            int total = 0;  // 도시의 치킨 거리
            for (int i = 0; i < houseList.size(); i++) {
                int sum = Integer.MAX_VALUE; // 각 집의 최소 치킨 거리
                for (int j = 0; j < chickenList.size(); j++) {
                    // i번째 집에서부터 j번짜 치킨집 까지의 거리 중 최소값을 구한다.
                    if (chickenVisited[j]) {
                        // 치킨 거리 계산
                        int dist = Math.abs(houseList.get(i).getX() - chickenList.get(j).getX())
                                + Math.abs(houseList.get(i).getY() - chickenList.get(j).getY());
                        sum = Math.min(sum, dist); // 최소 치킨 거리 갱신
                    }
                }
                total += sum; // 도시의 치킨 거리에 더하기
            }
            min = Math.min(total, min); // 최소 치킨 거리 갱신
            return;
        }

        // 모든 치킨집 탐색
        for (int i = idx; i < chickenList.size(); i++) {
            if (!chickenVisited[i]) { // 방문하지 않은 치킨집이면
                chickenVisited[i] = true; // 방문표시
                backtracking(count + 1, i + 1); // 다음 치킨집 선택
                chickenVisited[i] = false; // 방문 표시 해제
            }
        }
    }

    private static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

/*
이 문제는 백트래킹과 조합을 이용한 구현 문제이다.

1. 치킨집들 중 M개의 치킨집을 고른다.
2. M개의 치킨집과 집의 최소거리를 구한다.
3. 치킨집들 중 이전과 다른 M개의 치킨집을 골라 반복한다.
 */