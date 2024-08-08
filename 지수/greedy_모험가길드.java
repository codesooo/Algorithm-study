public class greedy_모험가길드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int res = 0;    // 총 그룹의 수
        int cnt = 0;    // 현재 그룹에 포함된 모험가의 수

        for (int i : arr) { // i : 공포도
            cnt += 1;   // 현재 그룹에 해당 모험가를 포함시킴
            if (cnt >= i) {     // (현재 그룹에 포함된 모험가의 수) >=  (현재의 공포도)
                res += 1;       // 그룹 결성
                cnt = 0;        // 현재 그룹의 모험가 수 초기화
            }
        }
        System.out.println(res);
    }
}
