import java.io.*;
import java.util.*;

public class dfsbfs_특정거리의도시찾기 {

    private static int n, m, k, x;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static int[] d;

    public static void main(String[] args) throws IOException{

        input();    // 값 입력받기

        d[x] = 0;   // 시작 도시 = 0 처리

        bfs();

        boolean flag = false;
        for (int i = 1; i < d.length; i++) {
            if (d[i] == k) {
                System.out.println(i);
                flag = true;
            }
        }

        if (!flag) {
            System.out.println(-1);
        }


    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());   // 도시 개수
        m = Integer.parseInt(st.nextToken());   // 도로 개수
        k = Integer.parseInt(st.nextToken());   // 거리 정보
        x = Integer.parseInt(st.nextToken());   // 출발 도시 번호

        d = new int[n+1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            d[i] = -1;
        }



        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                int nextNode = graph.get(now).get(i);
                if (d[nextNode] == -1) {
                    d[nextNode] = d[now] + 1;
                    queue.offer(nextNode);
                }
            }
        }
    }
}
