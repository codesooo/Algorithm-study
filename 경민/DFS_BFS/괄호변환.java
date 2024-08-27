package DFS_BFS;

public class 괄호변환 {
    public static void main(String[] args) {
        String p = "(()())()";
        //String p = ")(";
        //String p = "()))((()";

        System.out.println(solution(p));
    }
    // "균형잡힌 괄호 문자열"의 인덱스 반환
    public static int balancedIndex(String p) {
        int count = 0; // '(' 왼쪽 괄호의 개수
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') count += 1;  // '('이면 count 증가
            else count -= 1; // ')'이면 count 감소
            // count가 0이 되는 인덱스를 반환 (균형잡힌 괄호 문자열이 완성되면)
            if (count == 0) return i;
        }
        return -1;
    }

    // "올바른 괄호 문자열"인지 판단
    public static boolean checkProper(String p) {
        int count = 0; // '(' 왼쪽 괄호의 개수
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') count += 1; // '('이면 count 증가
            else {
                if (count == 0) { // 쌍이 맞지 않는 경우에 false 반환
                    return false;
                }
                count -= 1;
            }
        }
        return true; // 쌍이 맞는 경우에 true 반환
    }

    public static String solution(String p) {
        String answer = ""; // 최종적으로 반환할 결과 문자열
        if (p.equals("")) return answer; // 입력이 빈 문자열이면 빈 문자열 반환

        int index = balancedIndex(p); // 균형잡힌 괄호 문자열 u의 마지막 인덱스
        String u = p.substring(0, index + 1); // 문자열을 u와 v로 분리
        String v = p.substring(index + 1);

        // u가 "올바른 괄호 문자열"이면, v에 대해 함수를 수행한 결과를 붙여 반환
        if (checkProper(u)) {
            answer = u + solution(v);
        }
        // u가 "올바른 괄호 문자열"이 아니라면 아래의 과정을 수행
        else {
            answer = "("; // 빈 문자열에 '(' 추가
            answer += solution(v); // v에 대해 재귀적으로 solution 함수를 적용한 결과를 붙임
            answer += ")"; // ')' 추가
            u = u.substring(1, u.length() - 1); // u의 첫 번째와 마지막 문자를 제거
            String temp = "";
            // u의 나머지 문자열의 괄호 방향을 뒤집음
            for (int i = 0; i < u.length(); i++) {
                if (u.charAt(i) == '(') temp += ")"; // '('를 ')'로 변환
                else temp += "("; // ')'를 '('로 변환
            }
            answer += temp; // 변환된 문자열을 answer에 추가
        }
        return answer;
    }
}
