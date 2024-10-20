package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.393 여행 계획 / 난이도 중
 */
public class 그래프이론_여행계획 {
    public static int[] parent;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 부모를 자기 자신으로 초기화
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Union 연산 수행
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) unionParent(i + 1, j + 1);
            }
        }

        ArrayList<Integer> plan = new ArrayList<>();
        // 여행 계획 입력받기
        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(br.readLine());
            plan.add(x);
        }

        boolean result = true;
        // 여행 계획에 속하는 모든 노드의 루트가 동일한지 확인
        for (int i = 0; i < m - 1; i++) {
            if(findParent(plan.get(i)) != findParent(plan.get(i + 1))) result = false;
        }

        if (result) System.out.println("YES");
        else System.out.println("NO");

    }

    private static void unionParent(int a, int b) {
        // 두 원소가 속한 집합 합치기
        a = findParent(a);
        b = findParent(b);
        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    private static int findParent(int x) {
        // 루트 노드가 아니라면, 루트 노드를 찾을 때까지 재귀적으로 호출
        if(x == parent[x]) return x;
        return parent[x] = findParent(parent[x]);
    }
}

/*
이 문제는 양방향 간선을 가진 그래프로 표현된다.
서로소 집합 알고리즘을 이용하여 그래프에서 노드 간의 연결성을 파악해 해결할 수 있다.
중요한 포인트는 여행계획에 해당하는 모든 노드가 같은 집합에 속하기만 하면 가능한 여행 경로라는 것이다.
따라서 두 노드 사이에 도로가 존재하는 경우에는 union 연산을 이용하여 서로 연결된 두 노드를 같은 집합에 속하도록 만든다.
결과적으로 입력으로 들어온 여행 계획에 포함되는 모든 노드가 모두 같은 집합에 속하는지를 체크하여 출력하면된다.

 */