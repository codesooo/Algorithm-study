package 지연;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.329 기둥과 보 설치 / 난이도 중
 * 프로그래머스 https://school.programmers.co.kr/learn/courses/30/lessons/60061
 */
public class 구현_기둥과보설치 {
    public static void main(String[] args) {
        int[][] array = {
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {2, 1, 0, 1},
                {2, 2, 1, 1},
                {5, 0, 0, 1},
                {5, 1, 0, 1},
                {4, 2, 1, 1},
                {3, 2, 1, 1}
        };

        int n = 5;

        Solution solution = new Solution();
        int[][] result = solution.solution(5, array);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Solution {
        public int[][] solution(int n, int[][] build_frame) {
            ArrayList<ArrayList<Integer>> answer = new ArrayList<>();
            for (int i = 0; i < build_frame.length; i++) {
                int x = build_frame[i][0];
                int y = build_frame[i][1];
                int stuff = build_frame[i][2];
                int operate = build_frame[i][3];
                // 삭제하는 경우
                if (operate == 0) {
                    // 우선 삭제
                    int index = 0;
                    for (int j = 0; j < answer.size(); j++) {
                        // 현재 구조물 위치 가져오기
                        if(x == answer.get(j).get(0) && y == answer.get(j).get(1) && stuff == answer.get(j).get(2)){
                            index = j;
                        }
                    }
                    ArrayList<Integer> delete = answer.get(index);
                    answer.remove(index);
                    // 가능한 구조물인지 확인하고 아니라면 다시 설치
                    if(!possible(answer)) answer.add(delete);
                }
                // 설치하는 경우
                if (operate == 1) {
                    // 일단 설치
                    ArrayList<Integer> insert = new ArrayList<>();
                    insert.add(x);
                    insert.add(y);
                    insert.add(stuff);
                    answer.add(insert);
                    // 가능한 구조물인지 확인하고, 아니라면 다시 제거
                    if (!possible(answer)) answer.remove(answer.size() - 1);
                }
            }

            // 정렬
            ArrayList<Node> list = new ArrayList<>();
            for (int i = 0; i < answer.size(); i++) {
                list.add(new Node(answer.get(i).get(0), answer.get(i).get(1), answer.get(i).get(2)));
            }

            Collections.sort(list);

            // 배열로 변환
            int[][] result = new int[list.size()][3];
            for (int i = 0; i < list.size(); i++) {
                result[i][0] = list.get(i).getX();
                result[i][1] = list.get(i).getY();
                result[i][2] = list.get(i).getStuff();
            }

            return result;
        }

        // 핸저 설치된 구조물이 가능한 구조물인지 확인
        public boolean possible(ArrayList<ArrayList<Integer>> answer) {
            for (int i = 0; i < answer.size(); i++) {
                int x = answer.get(i).get(0);
                int y = answer.get(i).get(1);
                int stuff = answer.get(i).get(2);

                // 설치된 것이 기둥일경우
                if (stuff == 0) {
                    boolean check = false;
                    // 바닥 위라면 정상
                    if(y == 0) check = true;
                    // 보의 한 쪽 끝 부분 위 or 다른 기둥 위라면 정상
                    for (int j = 0; j < answer.size(); j++) {
                        // 왼쪽에 보
                        if(x - 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                            check = true;
                        }
                        // 현위치에 보
                        if(x == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                            check = true;
                        }
                        // 아래에 기둥
                        if(x == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)){
                            check = true;
                        }
                    }
                    if(!check) return false;

                } else if (stuff == 1){ // 설치된 것이 보일 경우
                    boolean check = false;
                    boolean left = false;
                    boolean right = false;

                    // 한쪽 끝 부분이 기둥 위 or 양쪽 끝 부분이 다른 보와 동시에 연결이라면 정상
                    for (int j = 0; j < answer.size(); j++) {
                        // 아래가 기둥
                        if(x == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)){
                            check = true;
                        }
                        // 오른쪽 아래가 기둥
                        if(x + 1 == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)){
                            check = true;
                        }
                        // 왼쪽에 보
                        if(x - 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                            left = true;
                        }
                        // 오른쪽에 보
                        if(x + 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)){
                            right = true;
                        }
                    }
                    if(left && right) check = true;
                    if(!check) return false;
                }
            }
            return true;
        }

    }

    static class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int stuff;

        public Node(int x, int y, int stuff) {
            this.x = x;
            this.y = y;
            this.stuff = stuff;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getStuff() {
            return stuff;
        }

        @Override
        public int compareTo(Node o) {
            // x, y 좌표가 모두 같을 경우 기둥이 보보다 우선
            if (this.x == o.x && this.y == o.y) {
                return Integer.compare(this.stuff, o.stuff);
            }
            // x가 같을경우 y기준 오름차순
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            // x기준 오름차순
            return Integer.compare(this.x, o.x);
        }
    }

}

/*
 이 문제는 시뮬레이션을 이용한 구현문제이다.
 1. 보와 기둥을 설치/삭제할 때마다 우선 설치/삭제한다.
 2. 전체 구조물을 확인하여 설치/삭제가 정상인지 확인한다.
 3. 정상이지 않을 경우 다시 설치/삭제한다.
 */