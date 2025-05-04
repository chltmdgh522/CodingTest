import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[] num = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            int[][] dp = new int[N + 1][K + 1];
            dp[0][0] = 1;  // 아무것도 선택하지 않았을 때 합 0 하나 존재

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j <= K; j++) {
                    dp[i][j] = dp[i - 1][j];  // i번째 숫자 안 쓴 경우
                    if (j >= num[i - 1]) {
                        dp[i][j] += dp[i - 1][j - num[i - 1]];  // i번째 숫자 쓴 경우
                    }
                }
            }

            System.out.println("#" + test + " " + dp[N][K]);
        }
    }
}
