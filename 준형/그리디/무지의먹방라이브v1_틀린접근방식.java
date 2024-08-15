package 그리디;

import java.util.Arrays;

public class 무지의먹방라이브v1_틀린접근방식 {
    public static void main(String[] args) {

        int[] food_times = {3, 1, 2};
        int k = 5;

        System.out.println(solution(food_times, k));
    }

    public static int solution(int[] food_times, long k) {
        int count = 0;

        boolean isEmpty = Arrays.stream(food_times).allMatch(value -> value == 0);
        if (isEmpty) {
            return -1;
        }

        for (int i = 0; i <= k; i++) {
            if (i >= food_times.length) {
                i = i % food_times.length;
            }

            if (count == k) {
                return food_times[i];
            }

            if (food_times[i] == 0) {
                boolean isDelegated = true;
                int j = i;
                while (isDelegated) {
                    j++;
                    if (j >= food_times.length) {
                        j = j % food_times.length;
                    }
                    if (food_times[j] != 0) {
                        food_times[j] = food_times[j] - 1;
                        count++;
                        i = j;
                        isDelegated = false;
                    }
                }
            } else {
                food_times[i] = food_times[i] - 1;
                count++;
            }
        }
        return -1;
    }
}
