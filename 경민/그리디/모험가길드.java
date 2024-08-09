package 그리디;

import java.util.*;

public class 모험가길드 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<>();

        int n = scanner.nextInt(); //모험가 수

        //모험가 공포도 리스트
        for (int i=0; i<n; i++){
            arrayList.add(scanner.nextInt());
        }

        Collections.sort(arrayList); //정렬

        int result = 0; //그룹 수
        int count = 0; //그룹 안에 모험가 수

        for (int i=0; i<n; i++){ //공포도 리스트 확인
            count += 1; //모험가 추가
            if (count >= arrayList.get(i)){ //그룹 안에 모험가 수가 공포도보다 크거나 같으면
                result += 1; //그룹 수 추가
                count = 0; //그룹 안 모험가 수 초기화
            }
        }
        System.out.println(result);
    }
}
