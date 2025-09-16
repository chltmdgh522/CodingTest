import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000_000; // 충분히 큰 값 (불가능한 경우 대체용)
    static int N;
    static int[][] cost; // cost[i][c] : i번째 집을 c색으로 칠하는 비용 (c: 0=R,1=G,2=B)
    static int[][] dp;   // dp[i][c] : i번째 집까지 칠했을 때, i번째 집을 c색으로 칠한 최소 비용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken()); // R
            cost[i][1] = Integer.parseInt(st.nextToken()); // G
            cost[i][2] = Integer.parseInt(st.nextToken()); // B
        }

        int ans = INF;

        // 첫 번째 집의 색을 0(R), 1(G), 2(B) 중 하나로 고정해서 세 번 실행
        for (int firstColor = 0; firstColor < 3; firstColor++) {
            dp = new int[N + 1][3];

            // 초기화: 첫 번째 집 색을 firstColor로 강제, 나머지는 불가능(INF)
            for (int c = 0; c < 3; c++) {
                if (c == firstColor) dp[1][c] = cost[1][c];
                else dp[1][c] = INF;
            }

            // DP 채우기
            for (int i = 2; i <= N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }

            // 마지막 집은 첫 번째 집과 다른 색이어야 함
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor == firstColor) continue; // 같은 색이면 스킵
                ans = Math.min(ans, dp[N][lastColor]);
            }
        }

        System.out.println(ans);
    }
}
