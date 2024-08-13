package 그리디;

import java.util.*;

public class 무지의먹방라이브 {

    public static void main(String[] args) {
        int[] food_times = {3, 1, 2};
        int k = 5;
        Solution solution = new Solution();

        System.out.println(solution.solution(food_times, k));

    }

    static class Solution {

        class Food implements Comparable<Food> {

            private int time; // 음식 소요 시간
            private int index; // 음식의 원래 인덱스

            public Food(int time, int index) {
                this.time = time;
                this.index = index;
            }

            public int getTime() {
                return this.time;
            }

            public int getIndex() {
                return this.index;
            }

            // 시간이 짧은 것이 높은 우선순위를 가지도록 설정
            @Override
            public int compareTo(Food other) {
                return Integer.compare(this.time, other.time);
            }
        }

        public int solution(int[] food_times, long k) {
            // 전체 음식 시간을 계산하고, 네트워크 중단 시점(k)보다 작거나 같다면 모든 음식을 먹을 수 있으므로 -1 반환
            long foodTime = 0;
            for (int i = 0; i < food_times.length; i++) {
                foodTime += food_times[i];
            }
            if (foodTime <= k) return -1;

            // 시간이 작은 음식부터 빼야 하므로 우선순위 큐를 이용
            PriorityQueue<Food> pq = new PriorityQueue<>();
            for (int i = 0; i < food_times.length; i++) {
                // (음식 시간, 음식 번호) 형태로 우선순위 큐에 삽입
                pq.offer(new Food(food_times[i], i + 1));
            }

            foodTime = 0; // 먹기 위해 사용한 시간
            long previous = 0; // 직전에 다 먹은 음식 시간
            long length = food_times.length; // 남은 음식의 개수

            // 현재 가장 시간이 적게 남은 음식부터 순차적으로 먹으면서, 네트워크 중단 시점까지의 총 시간을 계산
            // foodTime + (현재의 음식 시간 - 이전 음식 시간) * 현재 음식 개수와 k 비교
            while (foodTime + ((pq.peek().getTime() - previous) * length) <= k) {
                int now = pq.poll().getTime(); // 현재 큐에서 가장 시간이 적게 걸리는 음식을 가져옴
                foodTime += (now - previous) * length; // 그 음식을 다 먹는 데 걸리는 시간을 총 시간에 더함
                length -= 1; // 다 먹은 음식 제외
                previous = now; // 이전 음식 시간 재설정
            }

            // 남아있는 음식 중 네트워크가 중단된 시점 이후에 먹어야 할 음식을 찾기
            ArrayList<Food> result = new ArrayList<>();
            while (!pq.isEmpty()) {
                result.add(pq.poll()); // 큐에서 남은 음식을 모두 빼서 리스트에 추가
            }
            // 남은 음식들을 원래 인덱스 기준으로 오름차순 정렬
            Collections.sort(result, new Comparator<Food>() {
                @Override
                public int compare(Food a, Food b) {
                    return Integer.compare(a.getIndex(), b.getIndex());
                }
            });
            // 남은 시간을 음식 개수로 나눈 나머지를 인덱스로 사용하여, 해당 음식을 반환
            return result.get((int) ((k - foodTime) % length)).getIndex();
        }
    }
}
