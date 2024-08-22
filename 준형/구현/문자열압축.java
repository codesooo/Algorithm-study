package 구현;

public class 문자열압축 {
    public static void main(String[] args) {

    }

    public int solution(String s) {
        int length = s.length();
        int answer = length;
        for (int i = 1; i <= length / 2; i++) {
            int position = 0;
            int len = s.length();

            while (position + i <= s.length()) {
                String unit = s.substring(position, position + i);
                position += i;

                int count = 0;

                while (position + i <= s.length()) {
                    if (unit.equals(s.substring(position, position + i))) {
                        ++count;
                        position += i;
                    } else {
                        break;
                    }
                }

                if (count > 0) {
                    len -= i * count;

                    if (count < 9) {
                        len += 1;
                    } else if (count < 99) {
                        len += 2;
                    } else if (count < 999) {
                        len += 3;
                    } else {
                        len += 4;
                    }
                }
            }

            answer = Math.min(len, answer);
        }
        return answer;
    }
}
