package 그리디;

import java.util.*;

public class 무지의먹방라이브v2 {

    public int solution(int[] food_times, long k) {
        List<Food> foodList = new LinkedList<>();
        int n = food_times.length;

        for (int i = 0; i < n; i++) {
            Food food = new Food(i + 1, food_times[i]);
            foodList.add(food);
        }

        foodList.sort(CompValue);
        int preValue = 0;
        int index = 0;

        for (Food food : foodList) {
            long diff = food.value - preValue;
            if (diff != 0) {
                long spend = diff * n;
                if (spend <= k) {
                    k -= spend;
                    preValue = food.value;
                } else {
                    k %= n;
                    List<Food> answerList = foodList.subList(index, food_times.length);
                    answerList.sort(CompIndex);
                    return answerList.get(index + (int) k).index;
                }
            }
            ++index;
            --n;
        }
        return -1;
    }

    class Food {
        int index;
        int value;

        public Food(final int index, final int value) {
            this.index = index;
            this.value = value;
        }
    }

    Comparator<Food> CompValue = (o1, o2) -> o1.value - o2.value;

    Comparator<Food> CompIndex = (o1, o2) -> o1.index - o2.index;
}




