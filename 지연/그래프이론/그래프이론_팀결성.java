package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 실전문제
 * p.298 팀 결성 / 난이도 중
 */
public class 그래프이론_팀결성 {
    public static int[] parent = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 합집합일 경우
            if (oper == 0) {
                unionParent(a, b);
            }
            // 찾기 연산일 경우
            else if (oper == 1) {
                if (findParent(a) == findParent(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    /**
     * 특정 원소가 속한 집합 찾기
     * @param x 루트를 찾기 위한 원소
     * @return 루트 노드
     */
    private static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을때까지 재귀적으로 호출
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }

    /**
     * 두 원소가 속한 집합을 합치기
     * @param a
     * @param b
     */
    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }
}

/*
이 문제는 서로소 집합 알고리즘 문제로 N과 M의 범위가 100,000이 넘기 때문에
경로 압축 방식의 서로소 집합 자료구조를 이용하여 시간 복잡도를 개선해야 한다.
 */