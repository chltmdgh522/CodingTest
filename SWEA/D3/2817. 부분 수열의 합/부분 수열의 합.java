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

            // dp[i] = 합이 i가 되는 부분집합의 개수
            int[] dp = new int[K + 1];
            dp[0] = 1; // 아무것도 선택하지 않았을 때 합 0인 경우 하나 존재

            for (int i = 0; i < N; i++) {
                for (int j = K; j >= num[i]; j--) {
                    dp[j] += dp[j - num[i]];
                }
            }

            System.out.println("#" + test + " " + dp[K]);
        }
    }
}
