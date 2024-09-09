package 정렬;

import java.util.*;

public class 카드정렬하기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 힙(Heap)에 초기 카드 묶음을 모두 삽입
        for (int i = 0; i < N; i++) {
            pq.add(scanner.nextInt());
        }

        int result = 0;

        // 힙에 원소가 하나일때까지 카드 묶음을 두 개씩 꺼내서 합치고 다시 넣는 과정을 반복
        while (pq.size() > 1) {
            // 가장 작은 두 수 꺼내기
            int first = pq.poll();
            int second = pq.poll();
            // 꺼낸 두 수 합한 후 다시 넣기
            int sum = first + second;
            result += sum;
            pq.add(sum);
        }
        System.out.println(result);
    }
}
