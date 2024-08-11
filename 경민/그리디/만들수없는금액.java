package 그리디;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class 만들수없는금액 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<>();

        int n = scanner.nextInt(); //동전 수

        //동전 리스트
        for (int i=0; i<n; i++){
            arrayList.add(scanner.nextInt());
        }

        Collections.sort(arrayList); //정렬

        int target = 1;
        for (int i = 0; i < n; i++) {
            // 만들 수 없는 금액을 찾았을 때 반복 종료
            if (target < arrayList.get(i)) break;
            target += arrayList.get(i);
        }

        System.out.println(target);

    }
}
