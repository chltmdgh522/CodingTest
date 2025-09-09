import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] tree = new int[N];
        st = new StringTokenizer(br.readLine());
        
        int max = 0;
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            if (tree[i] > max) {
                max = tree[i]; // 가장 높은 나무의 높이를 저장
            }
        }

        int left = 0;
        int right = max;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            long total = 0; // 합계가 int 범위를 넘을 수 있으므로 long 사용

            for (int i = 0; i < N; i++) {
                if (tree[i] > mid) {
                    total += tree[i] - mid;
                }
            }

            if (total >= M) { // 원하는 양보다 많이 얻었다면
                answer = mid; // 정답 후보로 저장
                left = mid + 1; // 더 높이 잘라도 될 수 있음
            } else { // 원하는 양보다 적게 얻었다면
                right = mid - 1; // 더 낮게 잘라야 함
            }
        }

        System.out.println(answer);
    }
}
