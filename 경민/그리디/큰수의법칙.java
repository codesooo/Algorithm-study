package 그리디;

import java.util.Arrays;
import java.util.Scanner;

public class 큰수의법칙 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //숫자 n,m,k 입력받기
        int n = scanner.nextInt(); //자연수 갯수
        int m = scanner.nextInt(); //숫자 더해지는 횟수
        int k = scanner.nextInt(); //연속으로 최대 더할 수 있는 횟수

        //입력받은 수로 배열 생성
        int[] array = new int[n];
        for(int i=0; i<n; i++){
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array); //정렬
        int max1 = array[n - 1]; //가장 큰수
        int max2 = array[n - 2]; //두번째 큰수

        //가장 큰 수가 더해지는 횟수
        int count = (m / (k+1)) * k;
        count += m % (k+1);

        int result = max1 * count; //가장 큰수 더하기
        result += (m - count) * max2; //두번째 큰 수 더하기

        System.out.println(result);

    }

}
