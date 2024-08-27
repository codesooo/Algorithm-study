package DFS_BFS;

import java.util.*;

public class 연산자끼워넣기 {
    public static int N; // 수의 개수
    public static int[] numbers; // 입력받은 수 배열
    public static int plus, minus, multiply, divide; // 연산자 개수

    static int max = Integer.MIN_VALUE; // 최댓값 : 최솟값으로 초기화
    static int min = Integer.MAX_VALUE; // 최솟값 : 최댓값으로 초기화

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt(); // 수의 개수
        numbers = new int[N]; // 입력받은 수의 개수 크기만큼의 배열
        for (int i = 0; i < N; i++) {
            numbers[i] = scanner.nextInt(); // 배열에 수 입력
        }

        // 연산자 개수 입력받기
        plus = scanner.nextInt();
        minus = scanner.nextInt();
        multiply = scanner.nextInt();
        divide = scanner.nextInt();

        // dfs 시작 (첫번째 숫자을 초기값으로)
        dfs(numbers[0], 1);

        // 결과 출력
        System.out.println(max);
        System.out.println(min);

    }

    // dfs 함수 : result는 현재까지의 계산 결과, index는 다음에 사용할 숫자의 인덱스
    public static void dfs(int result, int index) {
        // 모든 숫자를 다 사용한 경우 (index가 N과 같을 때)
        if (index == N) {
            // 최댓값과 최솟값 갱신
            max = Math.max(max, result);
            min = Math.min(min, result);
        }

        // 덧셈 연산자가 남아있을 때
        if (plus > 0) {
            plus -= 1; // 덧셈 연산자 하나 사용
            dfs(result + numbers[index], index + 1); // 다음 숫자와 덧셈 수행 후 재귀 호출
            plus += 1; // 연산자 개수 복구
        }
        // 뺄셈 연산자가 남아있을 때
        if (minus > 0) {
            minus -= 1; // 뺄셈 연산자 하나 사용
            dfs(result - numbers[index], index + 1); // 다음 숫자와 뺄셈 수행 후 재귀 호출
            minus += 1; // 연산자 개수 복구
        }
        // 곱셈 연산자가 남아있을 때
        if (multiply > 0) {
            multiply -= 1; // 곱셈 연산자 하나 사용
            dfs(result * numbers[index], index + 1); // 다음 숫자와 곱셈 수행 후 재귀 호출
            multiply += 1; // 연산자 개수 복구
        }
        // 나눗셈 연산자가 남아있을 때
        if (divide > 0) {
            divide -= 1; // 나눗셈 연산자 하나 사용
            dfs(result / numbers[index], index + 1); // 다음 숫자와 나눗셈 수행 후 재귀 호출
            divide += 1; // 연산자 개수 복구
        }
    }
}
