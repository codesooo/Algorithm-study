package 지연;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이것이 코딩테스트다 / 기출문제
 * p.311 모험가 길드 / 난이도 하
 */
public class 그리디_모험가길드 {
     public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int N = Integer.parseInt(br.readLine());
         StringTokenizer st = new StringTokenizer(br.readLine());

         int[] arr = new int[N];
         for (int i = 0; i < N; i++) {
             arr[i] = Integer.parseInt(st.nextToken());
         }

         Arrays.sort(arr);

         int cnt = 0;       // 현재 그룹원의 수
         int result = 0;    // 총 그룹 수

         for (int i = 0; i < N; i++) {  // 공포도 낮은 것 부터 확인
             cnt++; // 현재 그룹에 추가
             if (cnt >= arr[i]) {   // 현재 그룹원의 수가 공포도 이상이면 그룹 결성
                 result++;  // 그룹 추가
                 cnt = 0;   // 그룹원 초기화
             }
         }
         System.out.println(result);
     }
}

/*
이 문제는 그리디 알고리즘으로 풀 수 있다.
최대 그룹수를 구해야 하기 때문에 최소한의 인원으로 그룹을 구성해야한다.
공포도가 낮은 사람부터 최소한의 인원수로 그룹을 형성해 나갈경우 그룹수가 많아진다.
1. 공포도 순으로 오름차순 정렬
2. 그룹원의 수가 공포도보다 클경우 그룹 생성
 */