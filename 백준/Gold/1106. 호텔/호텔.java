
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // dp[i] = i명을 확보하는 데 필요한 최소 비용
        int MAX = 1000000000; // 충분히 큰 수
        int[] dp = new int[C + 101];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        int[] cost = new int[N];
        int[] customer = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            customer[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = customer[i]; j < dp.length; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer[i]] + cost[i]);
            }
        }

        // C명 이상 확보할 수 있는 최소 비용 찾기
        int answer = MAX;
        for (int i = C; i < dp.length; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
