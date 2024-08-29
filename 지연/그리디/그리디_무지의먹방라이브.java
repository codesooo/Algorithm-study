package 지연.그리디;

import java.util.*;
/**
 * 이것이 코딩테스트다, 프로그래머스 / 기출문제
 * p.316 무지의 먹방 라이브 / 난이도 하
 */
public class 그리디_무지의먹방라이브 {
    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        int k = 5;
        Solution solution = new Solution();

        System.out.println(solution.solution(food_times, k));

    }

    static class Solution {
        public int solution(int[] food_times, long k) {
            int answer = 0;
            long totalTime = 0;

            for (int i = 0; i < food_times.length; i++) {
                totalTime += food_times[i];
            }

            // 전체 음식을 먹는 시간이 k보다 작으면 -1 리턴
            if(totalTime <= k) return -1;

            // 시간이 적은 음식부터 빼기 위해 우선순위 큐 이용
            Queue<Food> q = new PriorityQueue<>();
            for (int i = 0; i < food_times.length; i++) {
                q.offer(new Food(food_times[i], i + 1));
            }

            totalTime = 0;  // 먹기위해 사용한 시간
            long previous = 0;  // 직전에 다 먹은 시간
            long length = food_times.length;    // 남은 음식 개수

            // 사용시간 + (현재 음식시간 - 이전 음식시간) * 현재 음식개수
            while (totalTime + ((q.peek().getTime() - previous) * length) <= k) {
                int now = q.poll().getTime();
                totalTime += (now - previous) * length;
                length -= 1;    // 다 먹은 음식 제외
                previous = now; // 이전 음식 시간 재설정
            }

            // 남은 음식 중 몇 번째 음식인지 확인하기 위해 출력
            ArrayList<Food> result = new ArrayList<>();

            while (!q.isEmpty()) {
                result.add(q.poll());
            }

            // 음식 번호 기준으로 정렬
            Collections.sort(result, new Comparator<Food>() {
                @Override
                public int compare(Food o1, Food o2) {
                    return Integer.compare(o1.getIdx(), o2.getIdx());
                }
            });

            return result.get((int) ((k - totalTime) % length)).getIdx();
        }

        class Food implements Comparable<Food>{
            private int time;
            private int idx;

            public Food(int time, int idx) {
                this.time = time;
                this.idx = idx;
            }

            public int getTime() {
                return time;
            }

            public int getIdx() {
                return idx;
            }


            // 시간이 짧은 것이 우선순위를 가지도록 설정
            @Override
            public int compareTo(Food o) {
                return Integer.compare(this.time, o.time);
            }
        }
    }
}

/*
이 문제는 그리디 알고리즘을 이용하여 해결해야 한다.
일반 완전 탐색을 이용해서는 효율성 테스트에서 실패하게 된다.
이 문제의 핵심은 시간이 적게 걸리는 음식부터 제거해 나가야 한다는 것이다.
이를 위해 우선순위 큐를 이용한다.

예)
1번음식: 8초 소요
2번음식: 6초 소요
3번음식: 4초 소요

1. 모든 음식을 우선순위 큐에 삽입한다.
    - 마지막에는 K초 후에 먹어야할 음식의 번호를 출력해야 하므로 우선순위 큐에 삽입할 때 (음식시간, 음식번호)의 튜플 형식으로 삽입한다.
    - 전체 남은시간(K): 15초
    - 남은 음식: 3개
2. 첫 단계에서는 가장 적게 걸리는 음식인 3번 음식을 뺀다.
    - 다만, 음식이 3개 남아 있으므르 3(남은 음식의 개수) * 4(3번 음식을 먹는 시간) = 12를 빼야한다.
    - 다시말해 3번 음식을 다 먹기 위해서는 12초가 걸린다는 의미이다.
    - 결과적으로 전체 남은 시간이 15초에서 3초로 줄어들게 된다.
    - 전체 남은시간(K): 3초
    - 남은 음식: 2개
    - 먹은 음식들: 4/3
3. 전체 남은 시간은 3초이고, 이번 단계에서는 2번 음식으 ㄹ빼야한다.
    - 전체 음식이 2개 남아있으므로 이번 단계에서 뺄 시간은 2(남음 음식의 개수) * 6(2번 음식을 다 먹는시간) = 12초가 된다.
    - 하지만 현재 전체 남은 시간이 3초인데, 이는 12보다 작으므로 빼지 않도록 한다.
    - 따라서 '다음으로 먹어야할' 음식의 번호를 찾아 출력하면 된다.
    - 매초 먹어야 할 음식들을 나열해보면 전체 남은 시간이 3초이므로, 4번째 음식으 ㅣ번호를 출력함녀 정답이다.
    - 8/1, 6/2, 8/1, 6/2
    - 전체 남은시간(K): 3초
    - 남은 음식: 2개

    따라서 2번 음식을 출력한다.
 */