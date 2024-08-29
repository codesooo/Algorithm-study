package 지연.구현;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.323 문자열 압축 / 난이도 중
 * 프로그래머스 https://school.programmers.co.kr/learn/courses/30/lessons/60057
 */
public class 구현_문자열압축 {
    public static void main(String[] args) {
        String s = "aabbaccc";
        Solution solution = new Solution();
        System.out.println(solution.solution(s));
    }

    static class Solution {
        public int solution(String s) {
            int answer = s.length();

            // 1부터 길이의 반까지 반복
            for (int i = 1; i < s.length() / 2 + 1; i++) {
                int cnt = 1;
                String str = "";
                // 앞에서부터 비교할 길이만큼 문자 추출
                String prev = s.substring(0, i);

                // 비교할 길이만큼 증가시켜 이전 문자열과 비교
                for (int j = i; j < s.length(); j += i) {
                    String sub = "";    // 현재 비교할 문자열 조각 저장
                    for (int k = j; k < j + i; k++) {
                        if(k < s.length()) sub += s.charAt(k);
                    }
                    if(prev.equals(sub)) cnt++;
                    // 다른 문자열이 나왔을 떄
                    else {
                        // cnt가 2이상일 때 cnt와 prev를 str에 추가. 그 외에는 prev만 추가
                        if(cnt >= 2) str += cnt + prev;
                        else str += prev;

                        sub = "";
                        for (int k = j; k < j + i; k++) {
                            if(k < s.length()) sub += s.charAt(k);
                        }
                        prev = sub; // 상태 초기화
                        cnt = 1;
                    }
                }
                // 마지막으로 남은 cnt와 prev를 str에 추가
                if(cnt >= 2) str += cnt + prev;
                else str += prev;

                answer = Math.min(answer, str.length());
            }
            return answer;
        }
    }
}
