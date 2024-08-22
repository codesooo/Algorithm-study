package implement;
import java.io.*;
import java.util.*;

class Node {

    private int time;
    private char direction;

    public Node(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }

    public int getTime() {
        return this.time;
    }

    public char getDirection() {
        return this.direction;
    }
}

class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

public class impl_뱀 {

    private static int n, k, l;
    private static int[][] arr = new int[101][101];
    private static ArrayList<Node> info = new ArrayList<>();

    private static int dx[] = {0, 1, 0, -1};
    private static int dy[] = {1, 0, -1, 0};

    private static int turn(int direction, char c) {
        if (c == 'L') direction = (direction == 0)? 3 : direction - 1;
        else direction = (direction + 1) % 4;
        return direction;
    }

    private static int simulate() {
        int x = 1, y = 1; // 뱀의 머리 위치
        arr[x][y] = 2; // 뱀이 존재하는 위치
        int direction = 0; 
        int time = 0; 
        int index = 0; 
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(x, y));

        while (true) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (1 <= nx && nx <= n && 1 <= ny && ny <= n && arr[nx][ny] != 2) {
                if (arr[nx][ny] == 0) {
                    arr[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                    Position prev = q.poll();
                    arr[prev.getX()][prev.getY()] = 0;
                }
                if (arr[nx][ny] == 1) {
                    arr[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                }
            }
            else {
                time += 1;
                break;
            }
            x = nx;
            y = ny;
            time += 1;
            if (index < l && time == info.get(index).getTime()) { 
                direction = turn(direction, info.get(index).getDirection());
                index += 1;
            }
        }
        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
        }

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            info.add(new Node(x, c));
        }

        System.out.println(simulate());
    }

}
