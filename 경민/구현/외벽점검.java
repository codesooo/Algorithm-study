package 구현;

import java.util.*;

public class 외벽점검 {
    public static void main(String[] args) {
        int n = 12;

        int[] weak = { 1, 5, 6, 10 };
        int[] dist = { 1, 2, 3, 4 };

//        int[] weak = { 1, 3, 4, 9, 10 };
//        int[] dist = { 3, 5, 7 };

        Solution solution = new Solution();
        int result = solution.solution(n, weak, dist);
        System.out.println(result);
    }

    static class Solution {
        public int solution(int n, int[] weak, int[] dist) {
            // 원형 구조의 외벽을 일자 형태로 변환하기 위해 weak 배열의 길이를 2배로 확장
            // 취약 지점의 위치를 2배로 늘려 배열에 추가
            ArrayList<Integer> weakList = new ArrayList<>();
            for (int i = 0; i < weak.length; i++) {
                weakList.add(weak[i]);  // 원래의 취약 지점을 추가
            }
            for (int i = 0; i < weak.length; i++) {
                weakList.add(weak[i] + n);  // 원래의 취약 지점에 n을 더한 값(외벽의 길이)을 추가하여 직선 형태로 변환
            }

            // 친구의 최소 투입 수를 찾기 위해 초기 값을 친구 수보다 큰 값으로 설정
            int answer = dist.length + 1;

            // 모든 친구들의 순열을 계산 (순서가 다를 경우를 모두 고려하기 위함)
            Permutation perm = new Permutation(dist.length, dist.length);
            perm.permutation(dist, 0);
            ArrayList<ArrayList<Integer>> distList = perm.getResult(); // 모든 순열 결과 저장

            // weak 배열의 각 지점을 시작점으로 설정하여 탐색
            for (int start = 0; start < weak.length; start++) {
                // 각 순열에 대해 모든 가능성을 탐색
                for (int i = 0; i < distList.size(); i++) {
                    int cnt = 1;  // 첫 번째 친구부터 시작
                    // 첫 번째 친구가 점검할 수 있는 마지막 위치 계산
                    int position = weakList.get(start) + distList.get(i).get(cnt - 1);

                    // 시작점부터 모든 취약 지점을 탐색
                    for (int index = start; index < start + weak.length; index++) {
                        // 친구가 점검할 수 있는 마지막 위치를 벗어나는 경우
                        if (position < weakList.get(index)) {
                            cnt += 1;  // 다음 친구를 투입
                            if (cnt > dist.length) {  // 더 이상 친구를 투입할 수 없으면 종료
                                break;
                            }
                            // 새 친구가 점검할 수 있는 마지막 위치 갱신
                            position = weakList.get(index) + distList.get(i).get(cnt - 1);
                        }
                    }
                    // 현재 순열에서 필요한 최소 친구 수를 기록
                    answer = Math.min(answer, cnt);
                }
            }

            // 만약 모든 친구를 투입해도 외벽을 점검할 수 없다면 -1 반환
            if (answer > dist.length) {
                return -1;
            }
            return answer; // 최소 투입 친구 수 반환
        }
    }
}

class Permutation {
    private int n; // 전체 배열의 크기
    private int r; // 선택할 요소의 개수 (여기서는 n과 동일)
    private int[] now; // 현재 순열을 저장할 배열
    private ArrayList<ArrayList<Integer>> result; // 생성된 모든 순열을 저장할 리스트

    public ArrayList<ArrayList<Integer>> getResult() {
        return result; // 생성된 순열을 반환
    }

    public Permutation(int n, int r) {
        this.n = n;
        this.r = r;
        this.now = new int[r];
        this.result = new ArrayList<>();
    }

    // 배열의 두 요소를 교환하는 메서드
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 재귀적으로 순열을 생성하는 메서드
    public void permutation(int[] arr, int depth) {
        // 현재 순열의 길이가 r에 도달하면 결과 리스트에 추가
        if (depth == r) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int i = 0; i < now.length; i++) {
                temp.add(now[i]);
            }
            result.add(temp); // 완성된 순열을 결과 리스트에 추가
            return;
        }

        // depth를 기준으로 순열을 생성
        for (int i = depth; i < n; i++) {
            swap(arr, i, depth); // 현재 위치의 요소와 교환
            now[depth] = arr[depth]; // 현재 순열에 요소 추가
            permutation(arr, depth + 1); // 다음 단계로 재귀 호출
            swap(arr, i, depth); // 원상복구
        }
    }
}
