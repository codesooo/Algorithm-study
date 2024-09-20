package 지연.정렬;

import java.util.*;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.552 실패율 / 난이도 하
 * 프로그래머스 https://school.programmers.co.kr/learn/courses/30/lessons/42889?language=java
 */
public class 정렬_실패율 {
    public static void main(String[] args) {

        int n = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};

        int[] result = Solution.solution(n, stages);
        System.out.println(Arrays.toString(result));

    }

    static class Solution {
        public static int[] solution(int N, int[] stages) {
            // 각 스테이지에 머물러 있는 플레이어 수를 저장하는 Map
            Map<Integer, Integer> stageCount = new HashMap<>();
            // 각 스테이지의 실패율을 저장하는 Map
            Map<Integer, Double> failureRates = new HashMap<>();
            // 총 플레이어 수
            int totalPlayers = stages.length;

            // 각 스테이지에 머물러 있는 플레이어 수 계산
            for (int stage : stages) {
                stageCount.put(stage, stageCount.getOrDefault(stage, 0) + 1);
            }

            // 각 스테이지의 실패율 계산
            for (int i = 1; i <= N; i++) {
                // 현재 스테이지에 머물러 있는 플레이어 수
                int playersAtStage = stageCount.getOrDefault(i, 0);
                if (totalPlayers > 0) {
                    // 실패율 계산: 현재 스테이지 플레이어 수 / 총 플레이어 수
                    double failureRate = (double) playersAtStage / totalPlayers;
                    failureRates.put(i, failureRate);
                    // 다음 스테이지를 위해 총 플레이어 수에서 현재 스테이지 플레이어 수를 뺌
                    totalPlayers -= playersAtStage;
                } else {
                    // 플레이어가 없는 경우 실패율은 0
                    failureRates.put(i, 0.0);
                }
            }

            // 스테이지를 실패율에 따라 정렬
            List<Integer> result = new ArrayList<>(failureRates.keySet());
            result.sort((a, b) -> {
                // 실패율 내림차순으로 정렬
                int compare = Double.compare(failureRates.get(b), failureRates.get(a));
                // 실패율이 같은 경우 스테이지 번호 오름차순으로 정렬
                return compare != 0 ? compare : Integer.compare(a, b);
            });

            // List를 int 배열로 변환하여 반환
            return result.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
/*
이 문제는 정렬과 구현으로 풀면된다.
1. Map을 이용해 각 스테이지에 머물러 있는 플레이어 수, 각 스테이지의 실패율을 저장한다.
2. 각 스테이지별 플레이어 수를 stageCount에 기록한다.
3. 실패율을 계산하여 failureRates 저장
4. 스태이지 실패율을 기준으로 내림차순으로 정렬한다.
    - 같을 경우 스테이지 번호 오름차순

 */
