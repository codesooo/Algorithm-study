package 정렬;

import java.util.*;

public class 실패율 {
    public static void main(String[] args) {
        int N = 5;
        int stages[] = {2, 1, 2, 6, 2, 4, 3, 3};

//        int N = 4;
//        int stages[] = {4, 4, 4, 4, 4};

        int[] result = solution(N, stages);
        System.out.println(Arrays.toString(result));
    }
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        double[] failRates = new double[N]; // 각 스테이지에 대한 실패율 저장
        int length = stages.length;

        // 스테이지 번호를 1부터 N까지 증가시키며
        for (int i = 1; i <= N; i++) {
            // 해당 스테이지에 머물러 있는 사람의 수 계산
            int cnt = 0;
            for (int stage : stages) {
                if (stage == i) {
                    cnt++;
                }
            }

            // 실패율 계산
            if (length > 0) {
                failRates[i - 1] = (double) cnt / length;
            } else {
                failRates[i - 1] = 0;
            }

            // 다음 스테이지를 위한 남은 사용자 수 갱신
            length -= cnt;
        }

        // 스테이지 번호와 실패율을 함께 정렬하기 위해 인덱스 배열 생성
        Integer[] stagesArr = new Integer[N];
        for (int i = 0; i < N; i++) {
            stagesArr[i] = i + 1; // 1부터 N까지의 스테이지 번호를 저장
        }

        // 실패율을 기준으로 정렬 (내림차순), 실패율이 같으면 스테이지 번호 기준 오름차순
        Arrays.sort(stagesArr, (a, b) -> {
            if (failRates[a - 1] == failRates[b - 1]) {
                return Integer.compare(a, b); // 실패율이 같으면 스테이지 번호로 오름차순
            } else {
                return Double.compare(failRates[b - 1], failRates[a - 1]); // 실패율 기준 내림차순
            }
        });

        // 정렬된 스테이지 번호를 answer 배열에 저장
        for (int i = 0; i < N; i++) {
            answer[i] = stagesArr[i];
        }
        return answer;
    }
}