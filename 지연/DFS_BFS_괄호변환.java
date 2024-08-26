package 지연;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.346 괄호 변환 / 난이도 하
 * 프로그래머스 https://school.programmers.co.kr/learn/courses/30/lessons/60058
 */
public class DFS_BFS_괄호변환 {
    public static void main(String[] args) {
//        String p = "(()())()";
        String p = "()))((()";
        System.out.println(Solution.solution(p));
    }

    class Solution {
        public static String solution(String p) {
            return make(p);
        }

    }

    // 문자 생성
    private static String make(String p) {
        if (p.isEmpty()) return p;

        int idx = balanceString(p);
        String u = p.substring(0, idx);
        String v = p.substring(idx);

        if (correctString(u)) {
            return u + make(v);
        } else {
            return "(" + make(v) + ")" + reverse(u.substring(1, u.length() - 1));
        }
    }

    // 반대방향으로 만들기
    private static String reverse(String p) {
        char[] chars = p.toCharArray();
        for (int i = 0; i < p.length(); i++) {
            chars[i] = chars[i] == '(' ? ')' : '(';
        }
        return String.valueOf(chars);
    }


    // 균형잡힌 괄호 문자열인지 확인
    private static int balanceString(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    // 올바른 괄호 문자열인지 확인
    private static boolean correctString(String str) {
        int cnt = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            if (cnt < 0) {
                return false;
            }
        }
        return true;
    }
}
