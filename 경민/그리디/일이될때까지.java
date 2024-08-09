package 그리디;

import java.util.Scanner;

public class 일이될때까지 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //숫자 n,k 입력받기
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int result = 0; //n을 1로 만드는 최소 횟수

        while(true){
            //나머지(나누어 떨어지기 위해 빼야할 수) 구하기
            int rest = (n % k);
            result += rest;
            //나누어 떨어지는 수 만들기
            n -= rest;
            //n이 k보다 작으면(더이상 나눌 수 없으면) 반복 중단
            if(n<k) break;
            //k로 나누기
            n /= k;
            result += 1;
        }
        //마지막 남은 수에서 1남기고 빼는 횟수
        result += (n-1);
        System.out.println(result);
    }
}
