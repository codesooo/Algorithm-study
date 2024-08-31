package DFS_BFS;

import java.util.*;

public class 블록이동하기 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] board = {
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 1},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0}
        };
        int result = solution.solution(board);
        System.out.println("최소 이동 시간: " + result);
    }

    static class Solution {
        public ArrayList<Node> getNextPos(Node pos, int[][] board) {
            // 반환할 이동 가능한 위치들
            ArrayList<Node> nextPos = new ArrayList<>();
            // 상, 하, 좌, 우로 이동하는 경우 처리
            int[] dx = {-1, 1, 0, 0};
            int[] dy = {0, 0, -1, 1};
            for (int i = 0; i < 4; i++) {
                // 로봇의 두 칸에 대해 각각 이동한 후의 좌표 계산
                int pos1NextX = pos.getPos1X() + dx[i];
                int pos1NextY = pos.getPos1Y() + dy[i];
                int pos2NextX = pos.getPos2X() + dx[i];
                int pos2NextY = pos.getPos2Y() + dy[i];
                int distanceNext = pos.getDistance() + 1;
                // 두 칸이 모두 빈 칸이라면 이동 가능하므로 리스트에 추가
                if (board[pos1NextX][pos1NextY] == 0 && board[pos2NextX][pos2NextY] == 0) {
                    nextPos.add(new Node(pos1NextX, pos1NextY, pos2NextX, pos2NextY, distanceNext));
                }
            }
            // 현재 로봇이 가로로 놓여 있는 경우의 회전 처리
            int[] hor = {-1, 1};
            if (pos.getPos1X() == pos.getPos2X()) {
                for (int i = 0; i < 2; i++) { // 위쪽 또는 아래쪽으로 회전
                    // 회전할 위치가 모두 빈 칸이면 새로운 위치로 리스트에 추가
                    if (board[pos.getPos1X() + hor[i]][pos.getPos1Y()] == 0 && board[pos.getPos2X() + hor[i]][pos.getPos2Y()] == 0) {
                        nextPos.add(new Node(pos.getPos1X(), pos.getPos1Y(), pos.getPos1X() + hor[i], pos.getPos1Y(), pos.getDistance() + 1));
                        nextPos.add(new Node(pos.getPos2X(), pos.getPos2Y(), pos.getPos2X() + hor[i], pos.getPos2Y(), pos.getDistance() + 1));
                    }
                }
            }
            // 현재 로봇이 세로로 놓여 있는 경우의 회전 처리
            int[] ver = {-1, 1};
            if (pos.getPos1Y() == pos.getPos2Y()) {
                for (int i = 0; i < 2; i++) { // 왼쪽 또는 오른쪽으로 회전
                    // 회전할 위치가 모두 빈 칸이면 새로운 위치로 리스트에 추가
                    if (board[pos.getPos1X()][pos.getPos1Y() + ver[i]] == 0 && board[pos.getPos2X()][pos.getPos2Y() + ver[i]] == 0) {
                        nextPos.add(new Node(pos.getPos1X(), pos.getPos1Y(), pos.getPos1X(), pos.getPos1Y() + ver[i], pos.getDistance() + 1));
                        nextPos.add(new Node(pos.getPos2X(), pos.getPos2Y(), pos.getPos2X(), pos.getPos2Y() + ver[i], pos.getDistance() + 1));
                    }
                }
            }
            // 현재 위치에서 이동할 수 있는 모든 위치 반환
            return nextPos;
        }

        public int solution(int[][] board) {
            // 맵 외곽에 벽을 추가하여 맵을 확장
            int n = board.length;
            int[][] newBoard = new int[n + 2][n + 2];
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    newBoard[i][j] = 1; // 맵의 모든 외곽을 벽으로 초기화
                }
            }
            // 원래 맵의 내용으로 확장된 맵의 내부 채우기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    newBoard[i + 1][j + 1] = board[i][j];
                }
            }
            // 너비 우선 탐색(BFS) 시작
            Queue<Node> q = new LinkedList<>();
            ArrayList<Node> visited = new ArrayList<>();
            Node pos = new Node(1, 1, 1, 2, 0); // 시작 위치 설정 (1,1)과 (1,2)에서 시작
            q.offer(pos); // 시작 위치를 큐에 삽입
            visited.add(pos); // 시작 위치를 방문 처리
            // 큐가 빌 때까지 반복
            while (!q.isEmpty()) {
                pos = q.poll();
                // (n, n) 위치에 도달하면, 최단 거리 반환
                if ((pos.getPos1X() == n && pos.getPos1Y() == n) || (pos.getPos2X() == n && pos.getPos2Y() == n)) {
                    return pos.getDistance();
                }
                // 현재 위치에서 이동할 수 있는 위치 계산
                ArrayList<Node> nextPos = getNextPos(pos, newBoard);
                for (int i = 0; i < nextPos.size(); i++) {
                    // 아직 방문하지 않은 위치라면 큐에 삽입하고 방문 처리
                    boolean check = true;
                    pos = nextPos.get(i);
                    for (int j = 0; j < visited.size(); j++) {
                        if (pos.getPos1X() == visited.get(j).getPos1X() && pos.getPos1Y() == visited.get(j).getPos1Y() && pos.getPos2X() == visited.get(j).getPos2X() && pos.getPos2Y() == visited.get(j).getPos2Y()) {
                            check = false; // 이미 방문한 위치라면 무시
                            break;
                        }
                    }
                    if (check) {
                        q.offer(pos); // 새 위치를 큐에 삽입
                        visited.add(pos); // 방문 처리
                    }
                }
            }
            return 0; // 예외적으로 도달할 수 없는 경우(문제의 조건 상 발생하지 않음)
        }
    }

    static class Node {
        // 로봇이 차지하는 두 위치의 좌표와 현재까지 이동한 거리를 저장하는 클래스
        private int pos1X;
        private int pos1Y;
        private int pos2X;
        private int pos2Y;
        private int distance;

        // 각 좌표와 이동 거리에 대한 getter 메서드들
        public int getPos1X() {
            return this.pos1X;
        }
        public int getPos1Y() {
            return this.pos1Y;
        }
        public int getPos2X() {
            return this.pos2X;
        }
        public int getPos2Y() {
            return this.pos2Y;
        }
        public int getDistance() {
            return this.distance;
        }

        // Node 객체를 초기화하는 생성자
        public Node(int pos1X, int pos1Y, int pos2X, int pos2Y, int distance) {
            this.pos1X = pos1X;
            this.pos1Y = pos1Y;
            this.pos2X = pos2X;
            this.pos2Y = pos2Y;
            this.distance = distance;
        }
    }
}