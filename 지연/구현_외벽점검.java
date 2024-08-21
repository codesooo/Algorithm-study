package 지연;

/**
 * 이것이 코딩테스트다 / 기출문제
 *  p.335 외벽 점검 / 난이도 상
 *  프로그래머스 https://school.programmers.co.kr/learn/courses/30/lessons/60062
 */
public class 구현_외벽점검 {
    static int[] weak_append; // 확장한 취약지점의 위치
    static int answer;
    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4,};

        Solution solution = new Solution();
        int result = solution.solution(n, weak, dist);
        System.out.println(result);


    }

    static class Solution {
        public int solution(int n, int[] weak, int[] dist) {
            answer = Integer.MAX_VALUE;
            // 반시계방향도 체크할 수 있게 확장된 배열 생성
            weak_append = new int[weak.length * 2];
            int i = 0;
            while (i < weak.length) {
                weak_append[i] = weak[i];
                weak_append[i + weak.length] = weak[i++] + n;
            }

            // 시작점 옮기기
            for (int k = 0; k < weak.length; k++) {
                dfs(k, 0, dist, new boolean[dist.length], new int[dist.length]);
            }

            if (answer == Integer.MAX_VALUE) return -1;
            return answer;
        }

        public void dfs(int start, int depth, int[] dist, boolean[] visited, int[] permuted) {
            // dist 순열
            if (depth == dist.length) {
                answer = Math.min(answer, check(start, start + weak_append.length / 2, permuted));
                return;
            }
            for (int i = 0; i < dist.length; i++) {
                if(visited[i]) continue;
                visited[i] = true;
                permuted[depth] = dist[i];
                dfs(start, depth + 1, dist, visited, permuted);
                visited[i] = false;
            }
        }

        private int check(int start, int end, int[] permuted) {
            // permuted index
            int friend = 1;
            // 첫 취약점 위치 + 친구거리에서 시작
            int pos = weak_append[start] + permuted[friend - 1];
            for (int i = start; i < end; i++) {
                // 점검 위치 벗어나면
                if (pos < weak_append[i]) {
                    friend++;
                    // 친구 수 초과하면 실패
                    if(friend > permuted.length) return Integer.MAX_VALUE;
                    pos = weak_append[i] + permuted[friend - 1];
                }
            }
            return friend;
        }
    }
}

/*
이 문제는 완전탐색과 순열을 이용한 구현 문제이다.
건물이 원형인것을 일자로 풀어서 확인하는 것이 특징이다.
1. 원형인 배열의 길이를 두배로 늘려서 일자로 만들기(확장)
    - 즉 weak=[1, 5, 6, 10], n=12 일때 1부터 시작하면 1, 5, 6, 10 이고
    5부터 시작하면 5, 6, 10, 1+12=13 이 되듯이 계속 진행하면
    weak = [1, 5, 6, 10, 13, 17, 18, 22] 가 된다.
2. 각 위치에서 어떤 친구를 먼저 배치할 지 정한다.
    - dfs를 이용한 순열을 구한다.
 */