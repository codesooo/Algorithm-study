package 지연.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이것이 코딩 테스트다 / 기출문제
 * p.395 탑승구 / 난이도 중
 */
public class 그래프이론_탑승구 {
    public static int g, p;
    public static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        g = Integer.parseInt(br.readLine());
        p = Integer.parseInt(br.readLine());

        parent = new int[g + 1];
        for (int i = 1; i <= g; i++) {
            parent[i] = i;
        }
        int result = 0;
        for (int i = 0; i < p; i++) {
            int x = Integer.parseInt(br.readLine());
            // 현재 비행기 탑승구의 루트 확인
            int root = findParent(x);
            // 현재 루트가 0이면 종료
            if (root == 0) {
                break;
            }
            // 그렇지 않으면 바로 왼쪽 집합과 합치기
            unionParent(root, root - 1);
            result++;
        }
        System.out.println(result);



    }

    private static void unionParent(int a, int b) {
        // 두 원소가 속한 집합 합치기
        a = findParent(a);
        b = findParent(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static int findParent(int x) {
        // 루트 노드가 아니면 루트 노드 찾을 때까지 재귀적으로 호출
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = findParent(parent[x]);
    }
}

/*
이 문제는 서로소 집합 알고리즘을 이용하면 효율적으로 해결할 수 있다.
각 탑승구를 서로 다른 집합으로 나타냈을 때 도킹하는 과정을 탑승구 간 합집합 연산으로 나타낼 수 있다.
새롭게 비행기가 도킹이 되면 해당 집합을 바로 왼쪽에 있는 집합과 합친다.
집합의 루트가 0이면 더이상 도킹이 불가능한 것으로 판단하여 중지된다.
 */