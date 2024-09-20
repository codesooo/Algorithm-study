import java.util.*;

public class binary_공유기설치 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int c = sc.nextInt();

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }

        Collections.sort(arr);

        int start = 1; 
        int end = arr.get(n - 1) - arr.get(0); 
        int result = 0;

        while (start <= end) {
            int mid = (start + end) / 2;
            int value = arr.get(0);
            int cnt = 1;
            for (int i = 1; i < n; i++) { 
                if (arr.get(i) >= value + mid) {
                    value = arr.get(i);
                    cnt += 1;
                }
            }
            if (cnt >= c) {
                start = mid + 1;
                result = mid; 
            }
            else {
                end = mid - 1;
            }
        }
        System.out.println(result);
    }
}
