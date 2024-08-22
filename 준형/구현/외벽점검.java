package 구현;

import java.util.*;

class 외벽점검 {

    public static void main(String[] args) {
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution(12, weak, dist));
    }

    static int N, INF = 987654321, min;
    // 약한 지점 배열과 일꾼의 거리 배열 선언
    static int[] Weak;
    static int[] Dist;

    public static int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        min = INF;
        N = n;
        Weak = weak;

        // 일꾼의 거리 배열을 오름차순으로 정렬
        Arrays.sort(dist);
        // 정렬된 일꾼의 거리 배열을 전역 변수 Dist에 저장
        Dist = dist;
        // 약한 지점의 각 위치에서 순열을 시도
        for (int i = 0; i < weak.length; i++) {
            // 순열을 통해 가능한 모든 일꾼 배치 시도
            permutaion(1, i, new boolean[weak.length]);
        }

        // 최종적으로 구한 최소 값이 무한대라면 -1 반환, 아니면 최소 값을 반환
        answer = min == INF ? -1 : min;
        return answer;
    }

    // 순열을 통해 일꾼의 배치를 시도하는 메서드
    static void permutaion(int depth, int pos, boolean[] visit) {
        // 일꾼의 수보다 탐색 깊이가 깊어지면 종료
        if (depth > Dist.length)
            return;
        // 이미 구한 최소 값보다 현재 깊이가 깊으면 더 이상 탐색할 필요가 없으므로 종료
        if (min <= depth)
            return;
        // 방문 여부 배열을 복사하여 사용
        boolean[] copyVisit = Arrays.copyOf(visit, visit.length);
        // 현재 위치에서부터 가능한 지점들을 방문 처리
        for (int i = 0; i < Weak.length; i++) {
            // 다음 약한 지점의 인덱스 계산
            int nextPos = (pos + i) % Weak.length;
            // 현재 위치에서 다음 위치까지의 거리 계산
            int diff = nextPos >= pos ? Weak[nextPos] - Weak[pos] : Weak[nextPos] + N - Weak[pos];
            // 현재 일꾼의 이동 거리 내에 약한 지점이 포함되면 방문 처리
            if (diff <= Dist[Dist.length - depth]) {
                copyVisit[nextPos] = true;
            } else
                break;
        }
        // 모든 약한 지점을 커버했다면 최소 값을 갱신하고 종료
        if (isValid(copyVisit)) {
            min = Math.min(min, depth);
            return;
        }
        // 아직 커버하지 못한 약한 지점이 있다면, 다음 일꾼을 배치해보는 재귀 호출
        for (int i = 0; i < Weak.length; i++) {
            if (!copyVisit[i])
                permutaion(depth + 1, i, copyVisit);
        }
    }

    // 모든 약한 지점이 커버되었는지 확인하는 메서드
    static boolean isValid(boolean[] visit) {
        // 방문 여부 배열에 방문하지 않은 지점이 있으면 false 반환
        for (int i = 0; i < visit.length; i++) {
            if (!visit[i])
                return false;
        }
        // 모든 지점이 방문되었다면 true 반환
        return true;
    }
}
