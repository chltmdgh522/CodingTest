import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test < T + 1; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int cal = Integer.parseInt(st.nextToken());
            int[] dp = new int[cal + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()); // 맛 점수
                int C = Integer.parseInt(st.nextToken()); // 칼로리

                for (int j = cal; j >= C; j--) {
                    dp[j] = Math.max(dp[j], dp[j - C] + S);
                }
            }

            System.out.println("#" + test + " " + dp[cal]);


        }
    }


}
