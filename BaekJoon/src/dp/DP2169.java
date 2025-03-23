package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP2169 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] temp = new int[2][M];
        int[][] dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dp[i][j] = map[i][j];
                    } else {
                        dp[i][j] = dp[i][j - 1] + map[i][j];
                    }
                } else {
                    if (j == 0) {
                        temp[0][j] = dp[i - 1][j] + map[i][j];
                        temp[1][M - 1 - j] = dp[i - 1][M - 1] + map[i][M - 1];
                    } else {
                        temp[0][j] = Math.max(dp[i - 1][j], temp[0][j - 1]) + map[i][j];
                        temp[1][M - 1 - j] = Math.max(dp[i - 1][M - 1 - j], temp[1][M - j]) + map[i][M - 1 - j];
                    }
                }
            }
            if (i > 0) {
                for (int k = 0; k < M; k++) {
                    dp[i][k] = Math.max(temp[0][k], temp[1][k]);
                }
            }

        }


        System.out.println(dp[N - 1][M - 1]);
    }
}
