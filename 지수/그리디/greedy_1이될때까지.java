public class greedy_1이될때까지 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int target = 0;
        int cnt = 0;
        while (true) {
            target = (N / K) * K;        // N 보다 작은 수 중에서, K의 배수이면서 가장 큰 수
            cnt += (N - target);        // N을 target으로 만드는데 필요한 -1 연산 횟수 count 
            N = target;

            if (N < K) {
                break;
            }

            N /= K;
            cnt += 1;  // 나누기 연산 count
        }

        cnt += (N - 1);    // N을 1로 만드는데 필요한 -1 연산 횟수 count
        System.out.println(cnt);
    }
}
