package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DP9465 {

    static int N;

    static int[][] arr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            arr = new int[2][N + 1];
            dp = new int[2][N + 1];
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k < N + 1; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            solution();
        }
    }

    private static void solution() {
        dp[0][1] = arr[0][0];
        dp[1][1] = arr[1][0];

        for (int j = 2; j < N + 1; j++) {
            dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + arr[0][j];
            dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + arr[1][j];
        }

        System.out.println(Math.max(dp[0][N], dp[1][N]));
    }
}
