package 구현;

import java.util.*;

public class 뱀 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 맵의 크기 (N x N) 입력
        n = sc.nextInt();
        // 사과의 개수 K 입력
        k = sc.nextInt();

        // 사과의 위치를 맵에 표시
        for (int i = 0; i < k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            arr[a][b] = 1; // 사과가 있는 위치를 1로 표시
        }

        // 방향 회전 정보 L 입력
        l = sc.nextInt();
        for (int i = 0; i < l; i++) {
            int x = sc.nextInt(); // 회전하는 시간
            char c = sc.next().charAt(0); // 회전 방향 ('L' 또는 'D')
            info.add(new int[] {x, c});
        }

        // 시뮬레이션 결과 출력 (게임 종료 시 시간)
        System.out.println(simulate());
    }

        // 변수 선언
        public static int n, k, l;
        public static int[][] arr = new int[101][101]; // 맵 정보: 0은 빈 칸, 1은 사과, 2는 뱀의 몸
        public static List<int[]> info = new ArrayList<>(); // 방향 전환 정보를 담을 리스트

        // 방향 벡터: 동(오른쪽), 남(아래), 서(왼쪽), 북(위) 순서로 정의
        public static int[] dx = {0, 1, 0, -1}; // x 좌표의 변화량
        public static int[] dy = {1, 0, -1, 0}; // y 좌표의 변화량

        // 방향을 전환하는 함수
        // direction은 현재 방향, c는 'L' 또는 'D' (왼쪽 또는 오른쪽 회전)
        public static int turn(int direction, char c) {
            if (c == 'L') {
                // 왼쪽 회전: 현재 방향이 0(동)이면 3(북)으로, 그 외엔 1씩 감소
                return (direction == 0) ? 3 : direction - 1;
            } else {
                // 오른쪽 회전: 현재 방향에서 1 증가, 4가 되면 0(동)으로 돌아옴
                return (direction + 1) % 4;
            }
        }

        // 게임 시뮬레이션을 수행하는 함수
        public static int simulate() {
            int x = 1, y = 1; // 뱀의 초기 위치 (1,1)
            arr[x][y] = 2; // 뱀의 초기 위치를 표시 (2로 설정)
            int direction = 0; // 초기 방향은 동쪽 (오른쪽)
            int time = 0; // 경과 시간
            int index = 0; // 회전 정보의 현재 인덱스
            Queue<int[]> q = new LinkedList<>(); // 뱀의 몸을 표현하는 큐
            q.offer(new int[] {x, y}); // 뱀의 초기 위치를 큐에 추가

            while (true) {
                // 뱀의 머리를 다음 위치로 이동
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                // 다음 위치가 맵 안에 있고, 뱀의 몸이 없는 경우
                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n && arr[nx][ny] != 2) {
                    // 이동할 위치에 사과가 없다면
                    if (arr[nx][ny] == 0) {
                        arr[nx][ny] = 2; // 뱀의 머리가 이동한 위치 표시
                        q.offer(new int[] {nx, ny}); // 새로운 머리 위치를 큐에 추가
                        int[] prev = q.poll(); // 꼬리를 이동시킴 (큐에서 제거)
                        arr[prev[0]][prev[1]] = 0; // 꼬리가 있던 위치를 비워둠
                    }
                    // 이동할 위치에 사과가 있는 경우
                    else if (arr[nx][ny] == 1) {
                        arr[nx][ny] = 2; // 사과가 있던 위치에 머리 이동
                        q.offer(new int[] {nx, ny}); // 새로운 머리 위치를 큐에 추가
                        // 꼬리는 그대로 두어 몸길이가 증가
                    }
                } else {
                    // 벽이나 뱀의 몸통에 부딪힌 경우 게임 종료
                    time++;
                    break;
                }

                // 머리 위치를 업데이트
                x = nx;
                y = ny;
                time++; // 시간 경과

                // 회전할 시간이 되면 방향을 변경
                if (index < l && time == info.get(index)[0]) {
                    direction = turn(direction, (char) info.get(index)[1]);
                    index++;
                }
            }
            return time; // 게임이 끝난 시간(초)을 반환
        }
}
