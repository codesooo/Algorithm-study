package 이진탐색;

import java.util.Scanner;

public class 떡볶이떡만들기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // 떡의 개수
        int M = scanner.nextInt(); // 요청한 떡의 길이

        // 각 떡의 개별 높이 정보
        int[] list = new int[N];
        for(int i=0; i<N; i++){
            list[i] = scanner.nextInt();
        }

        // 이진 탐색을 위한 시작점과 끝점 설정
        int start = 0;
        int end = 100000000;

        // 가장 긴 떡의 길이를 찾기
        for (int i = 0; i < N; i++) {
            end = Math.max(end, list[i]);
        }

        int result = 0; // 절단기 높이 최댓값
        while (start <= end) {
            int mid = (start + end) / 2;
            long total = 0;

            // 현재 중간점(mid)에서 떡을 잘랐을 때 가져갈 수 있는 떡의 양 계산
            for (int i = 0; i < N; i++) {
                if (list[i] > mid) {
                    total += list[i] - mid;
                }
            }
            // 떡의 양이 충분한 경우
            if (total >= M) {
                result = mid; // 최대한 덜 잘랐을 때가 정답이므로 result에 저장
                start = mid + 1; // 더 많이 자를 수 있는지 확인
            }
            // 떡의 양이 부족한 경우
            else {
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}