import java.util.*;

class Node {
    private int pos1X;
    private int pos1Y;
    private int pos2X;
    private int pos2Y;
    private int distance;

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

    public Node(int pos1X, int pos1Y, int pos2X, int pos2Y, int distance) {
        this.pos1X = pos1X;
        this.pos1Y = pos1Y;
        this.pos2X = pos2X;
        this.pos2Y = pos2Y;
        this.distance = distance;
    }
}

class Solution {

    public ArrayList<Node> getNextPos(Node pos, int[][] board) {
        ArrayList<Node> nextPos = new ArrayList<Node>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int pos1NextX = pos.getPos1X() + dx[i];
            int pos1NextY = pos.getPos1Y() + dy[i];
            int pos2NextX = pos.getPos2X() + dx[i];
            int pos2NextY = pos.getPos2Y() + dy[i];
            int distanceNext = pos.getDistance() + 1;
            if (board[pos1NextX][pos1NextY] == 0 && board[pos2NextX][pos2NextY] == 0) {
                nextPos.add(new Node(pos1NextX, pos1NextY, pos2NextX, pos2NextY, distanceNext));
            }
        }
        // 현재 로봇이 가로로 놓여 있는 경우
        int[] hor = {-1, 1};
        if (pos.getPos1X() == pos.getPos2X()) {
            for (int i = 0; i < 2; i++) { 
                if (board[pos.getPos1X() + hor[i]][pos.getPos1Y()] == 0 && board[pos.getPos2X() + hor[i]][pos.getPos2Y()] == 0) {
                    nextPos.add(new Node(pos.getPos1X(), pos.getPos1Y(), pos.getPos1X() + hor[i], pos.getPos1Y(), pos.getDistance() + 1));
                    nextPos.add(new Node(pos.getPos2X(), pos.getPos2Y(), pos.getPos2X() + hor[i], pos.getPos2Y(), pos.getDistance() + 1));
                }
            }
        }
        int[] ver = {-1, 1};
        if (pos.getPos1Y() == pos.getPos2Y()) {
            for (int i = 0; i < 2; i++) { 
                if (board[pos.getPos1X()][pos.getPos1Y() + ver[i]] == 0 && board[pos.getPos2X()][pos.getPos2Y() + ver[i]] == 0) {
                    nextPos.add(new Node(pos.getPos1X(), pos.getPos1Y(), pos.getPos1X(), pos.getPos1Y() + ver[i], pos.getDistance() + 1));
                    nextPos.add(new Node(pos.getPos2X(), pos.getPos2Y(), pos.getPos2X(), pos.getPos2Y() + ver[i], pos.getDistance() + 1));
                }
            }
        }
        return nextPos;
    }
    
    public int solution(int[][] board) {
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

      
      Queue<Node> q = new LinkedList<>();
        ArrayList<Node> visited = new ArrayList<>();
        Node pos = new Node(1, 1, 1, 2, 0); 
        q.offer(pos); 
        visited.add(pos); 
        while (!q.isEmpty()) {
            pos = q.poll();
            if ((pos.getPos1X() == n && pos.getPos1Y() == n) || (pos.getPos2X() == n && pos.getPos2Y() == n)) {
                return pos.getDistance();
            }

          ArrayList<Node> nextPos = getNextPos(pos, newBoard);
            for (int i = 0; i < nextPos.size(); i++) {
                boolean check = true;
                pos = nextPos.get(i);
              
                for (int j = 0; j < visited.size(); j++) {
                    if (pos.getPos1X() == visited.get(j).getPos1X() && pos.getPos1Y() == visited.get(j).getPos1Y() && pos.getPos2X() == visited.get(j).getPos2X() && pos.getPos2Y() == visited.get(j).getPos2Y()) {
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
}
