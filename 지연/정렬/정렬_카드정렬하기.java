package 지연.정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.363 안테나 / 난이도 중
 * 백준 골드4 https://www.acmicpc.net/problem/1715
 */
public class 정렬_카드정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        int cnt = 0;
        while (queue.size() != 1) {
            int temp1 = queue.poll();
            int temp2 = queue.poll();
            // 카드 묶음 더하기
            int sum = temp1 + temp2;
            // 횟수 누적
            cnt += sum;
            // 다음 카드 묶음과 이전 카드 묶음 더하기 위해 큐에 넣기
            queue.offer(sum);
        }

        System.out.println(cnt);
    }
}

/*
이 문제는 우선순위 큐를 이용하여 정렬을 이용할 수 있다.
카드 두묶음의 합은 가장 작은 카드의 수끼리 더할 때 최소값이 나올 수 있다.
우선순위 큐를 사용시 오름차순으로 정렬을 해주고, 기존 묶음의 카드를 다시 큐에 넣어서 활용할 수 있다.

 */