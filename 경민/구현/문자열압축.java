package 구현;

public class 문자열압축 {

    public static void main(String[] args) {
        String s = "aabbaccc";
//        String s = "ababcdcdababcdcd";
//        String s = "abcabcdede";
//        String s = "abcabcabcabcdededededede";
//        String s = "xababcdcdababcdcd";

        Solution solution = new Solution();
        System.out.println(solution.solution(s));
    }

    static class Solution {
        public int solution(String s) {
            int answer = s.length();
            // 1개 단위(step)부터 압축 단위를 늘려가며 확인
            for (int step = 1; step <= s.length() / 2; step++) {
                String compressed = "";
                String prev = s.substring(0, step); // 앞에서부터 step만큼의 문자열 추출
                int count = 1;

                // 단위(step) 크기만큼 증가시키며 이전 문자열과 비교
                for (int j = step; j < s.length(); j += step) {
                    String sub = s.substring(j, Math.min(j + step, s.length())); // 현재 부분 문자열

                    if (prev.equals(sub)) {
                        count++; // 이전 부분 문자열과 동일하면 카운트 증가
                    } else {
                        compressed += (count > 1 ? count + prev : prev); // 카운트가 2 이상이면 압축된 형태로 추가
                        prev = sub; // 새로운 부분 문자열로 초기화
                        count = 1; // 카운트 초기화
                    }
                }

                // 남아있는 문자열 처리
                compressed += (count > 1 ? count + prev : prev);

                // 만들어지는 압축 문자열이 가장 짧은 것이 정답
                answer = Math.min(answer, compressed.length());
            }
            return answer;
        }
    }

}
