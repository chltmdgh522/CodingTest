import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i >= 1; i--) {
            int nextDay = i + T[i];

            if (nextDay > N + 1) {
                dp[i] = dp[i + 1]; // 일을 안했으므로 앞에 있는 값 갖고오기
            } else {
                dp[i] = Math.max(dp[i + 1], P[i] + dp[nextDay]); // 1. 일 안했으므로 앞에 있는 값 갖고오기, 2. 오늘 일한값, 그리고 담날에 한값들 불러오기
            }
        }

        System.out.println(dp[1]);
    }


}