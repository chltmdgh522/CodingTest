
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static int[] book;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            int N = Integer.parseInt(br.readLine());
            book = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                book[i] = Integer.parseInt(st.nextToken());
            }
            result = 0;
            solution();
            System.out.println(result);
        }
    }

    private static void solution() {
        int n = book.length;
        int[][] dp = new int[n][n];
        int[] sum = new int[n + 1]; // sum[i] = book[0] ~ book[i-1]의 합

        // 누적합 계산
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + book[i];
        }

        // 구간 길이: 1부터 n-1까지
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + (sum[j + 1] - sum[i]);
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        result = dp[0][n - 1];
    }

}
