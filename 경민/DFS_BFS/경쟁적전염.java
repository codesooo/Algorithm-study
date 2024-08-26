package DFS_BFS;

import java.util.*;

public class 경쟁적전염 {
    // 시험관의 크기와 바이러스 종류 변수
    public static int n, k;
    // 전체 시험관 상태를 담는 배열 (바이러스가 있는 위치와 빈 칸 정보를 저장)
    public static int[][] graph = new int[200][200];
    // 초기 바이러스 정보를 저장할 리스트
    public static ArrayList<Virus> viruses = new ArrayList<>();

    // 바이러스가 퍼져나갈 수 있는 4가지의 위치 (상, 우, 하, 좌)
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); // 시험관의 크기
        k = sc.nextInt(); // 바이러스의 종류

        // 시험관의 초기 상태 입력받기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
                // 해당 위치에 바이러스가 존재하는 경우
                if (graph[i][j] != 0) {
                    // 바이러스 리스트에 바이러스 객체 추가 (바이러스 종류, 전파된 시간, 위치 X, 위치 Y)
                    viruses.add(new Virus(graph[i][j], 0, i, j));
                }
            }
        }

        // 바이러스 리스트를 정렬 (낮은 번호의 바이러스부터 먼저 증식해야 하므로)
        Collections.sort(viruses);

        // 정렬된 바이러스를 큐에 추가 (BFS에서 낮은 번호의 바이러스가 먼저 큐에 들어가도록 함)
        Queue<Virus> q = new LinkedList<>();
        for (int i = 0; i < viruses.size(); i++) {
            q.offer(viruses.get(i)); // 정렬된 순서대로 바이러스 객체를 큐에 삽입
        }

        int targetS = sc.nextInt(); // 목표 시간
        // 목표 위치
        int targetX = sc.nextInt();
        int targetY = sc.nextInt();

        // 너비 우선 탐색(BFS) 진행
        while (!q.isEmpty()) {
            Virus virus = q.poll(); // 큐에서 바이러스 객체를 하나 꺼냄
            // 정확히 targetS 초가 지나거나, 큐가 빌 때까지 반복
            if (virus.getSecond() == targetS) break;
            // 현재 노드에서 주변 4가지 위치를 각각 확인
            for (int i = 0; i < 4; i++) {
                int nx = virus.getX() + dx[i];
                int ny = virus.getY() + dy[i];
                // 바이러스가 퍼질 수 있는 위치인지 확인 (시험관 범위 내에 있는지)
                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    // 아직 방문하지 않은 위치라면, 그 위치에 바이러스 넣기
                    if (graph[nx][ny] == 0) {
                        graph[nx][ny] = virus.getIndex(); // 해당 위치에 현재 바이러스를 배치
                        q.offer(new Virus(virus.getIndex(), virus.getSecond() + 1, nx, ny)); // 새로운 바이러스 객체를 큐에 추가
                    }
                }
            }
        }
        // 목표 위치의 바이러스 종류를 출력 (바이러스가 없다면 0 출력)
        System.out.println(graph[targetX - 1][targetY - 1]);
    }
    public static class Virus implements Comparable<Virus> {

        private int index; // 바이러스 종류
        private int second; // 바이러스가 전파된 시간
        private int x; // 바이러스의 x 좌표
        private int y; // 바이러스의 y 좌표

        // 생성자: 바이러스 객체 초기화
        public Virus(int index, int second, int x, int y) {
            this.index = index;
            this.second = second;
            this.x = x;
            this.y = y;
        }

        // 바이러스 종류를 반환하는 메서드
        public int getIndex() {
            return this.index;
        }

        // 전파된 시간을 반환하는 메서드
        public int getSecond() {
            return this.second;
        }

        // 바이러스의 x 좌표를 반환하는 메서드
        public int getX() {
            return this.x;
        }

        // 바이러스의 y 좌표를 반환하는 메서드
        public int getY() {
            return this.y;
        }

        // 정렬 기준은 '번호가 낮은 순서'
        @Override
        public int compareTo(Virus other) {
            if (this.index < other.index) {
                return -1;
            }
            return 1;
        }
    }
}

