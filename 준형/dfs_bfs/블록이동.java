package dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class 블록이동 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}};
        int answer = solution(board);
        System.out.println(answer);
    }

    public static int solution(int[][] board) {
        // 맵 외곽에 벽을 두는 형태로 맵 변형
        int n = board.length;
        int[][] newBoard = new int[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                newBoard[i][j] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }
        // 너비 우선 탐색(BFS) 수행
        Queue<Node> q = new LinkedList<>();
        ArrayList<Node> visited = new ArrayList<>();
        Node pos = new Node(1, 1, 1, 2, 0);
        q.offer(pos);
        // 방문 처리
        visited.add(pos);
        while (!q.isEmpty()) {
            pos = q.poll();
            // (n, n) 위치에 로봇이 도달했다면, 최단 거리이므로 반환
            if ((pos.getPos1X() == n && pos.getPos1Y() == n) || (pos.getPos2X() == n && pos.getPos2Y() == n)) {
                return pos.getDistance();
            }
            // 현재 위치에서 이동할 수 있는 위치 확인
            ArrayList<Node> nextPos = getNextPos(pos, newBoard);
            for (Node nextPo : nextPos) {
                // 아직 방문하지 않은 위치라면 큐에 삽입하고 방문 처리
                boolean check = true;
                pos = nextPo;
                for (Node node : visited) {
                    if (pos.getPos1X() == node.getPos1X() && pos.getPos1Y() == node.getPos1Y()
                            && pos.getPos2X() == node.getPos2X() && pos.getPos2Y() == node
                            .getPos2Y()) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    q.offer(pos);
                    visited.add(pos);
                }
            }
        }
        return 0;
    }

    public static ArrayList<Node> getNextPos(Node pos, int[][] board) {
        ArrayList<Node> nextPos = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int pos1NextX = pos.getPos1X() + dx[i];
            int pos1NextY = pos.getPos1Y() + dy[i];
            int pos2NextX = pos.getPos2X() + dx[i];
            int pos2NextY = pos.getPos2Y() + dy[i];
            int distanceNext = pos.getDistance() + 1;
            // 이동하고자 하는 두 칸이 모두 비어 있다면
            if (board[pos1NextX][pos1NextY] == 0 && board[pos2NextX][pos2NextY] == 0) {
                nextPos.add(new Node(pos1NextX, pos1NextY, pos2NextX, pos2NextY, distanceNext));
            }
        }
        // 현재 로봇이 가로로 놓여 있는 경우
        int[] hor = {-1, 1};
        if (pos.getPos1X() == pos.getPos2X()) {
            { // 위쪽으로 회전하거나, 아래쪽으로 회전
            for (int i = 0; i < 2; i++)
                // 위쪽 혹은 아래쪽 두 칸이 모두 비어 있다면
                if (board[pos.getPos1X() + hor[i]][pos.getPos1Y()] == 0
                        && board[pos.getPos2X() + hor[i]][pos.getPos2Y()] == 0) {
                    nextPos.add(new Node(pos.getPos1X(), pos.getPos1Y(), pos.getPos1X() + hor[i], pos.getPos1Y(),
                            pos.getDistance() + 1));
                    nextPos.add(new Node(pos.getPos2X(), pos.getPos2Y(), pos.getPos2X() + hor[i], pos.getPos2Y(),
                            pos.getDistance() + 1));
                }
            }
        }
        // 현재 로봇이 세로로 놓여 있는 경우
        int[] ver = {-1, 1};
        if (pos.getPos1Y() == pos.getPos2Y()) {
            for (int i = 0; i < 2; i++) { // 왼쪽으로 회전하거나, 오른쪽으로 회전
                // 왼쪽 혹은 오른쪽 두 칸이 모두 비어 있다면
                if (board[pos.getPos1X()][pos.getPos1Y() + ver[i]] == 0
                        && board[pos.getPos2X()][pos.getPos2Y() + ver[i]] == 0) {
                    nextPos.add(new Node(pos.getPos1X(), pos.getPos1Y(), pos.getPos1X(), pos.getPos1Y() + ver[i],
                            pos.getDistance() + 1));
                    nextPos.add(new Node(pos.getPos2X(), pos.getPos2Y(), pos.getPos2X(), pos.getPos2Y() + ver[i],
                            pos.getDistance() + 1));
                }
            }
        }
        // 현재 위치에서 이동할 수 있는 위치를 반환
        return nextPos;
    }

    static class Node {
        private final int pos1X;
        private final int pos1Y;
        private final int pos2X;
        private final int pos2Y;
        private final int distance;

        public Node(int pos1X, int pos1Y, int pos2X, int pos2Y, int distance) {
            this.pos1X = pos1X;
            this.pos1Y = pos1Y;
            this.pos2X = pos2X;
            this.pos2Y = pos2Y;
            this.distance = distance;
        }

        public int getPos1X() {
            return pos1X;
        }

        public int getPos1Y() {
            return pos1Y;
        }

        public int getPos2X() {
            return pos2X;
        }

        public int getPos2Y() {
            return pos2Y;
        }

        public int getDistance() {
            return distance;
        }
    }
}