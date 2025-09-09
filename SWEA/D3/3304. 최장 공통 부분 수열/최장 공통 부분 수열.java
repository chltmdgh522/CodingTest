
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            int lengthA= a.length();
            int lengthB= b.length();
            int[][] dp = new int[lengthA + 1][lengthB + 1];

            for (int i = 0; i < lengthA; i++) {
                for (int j = 0; j < lengthB; j++) {
                    if (a.charAt(i) == b.charAt(j) ) {
                        dp[i + 1][j + 1] = dp[i][j]+ 1;
                    } else {
                        dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                    }
                }
            }



            System.out.println("#" + test + " " + dp[lengthA][lengthB]);
        }
    }


}
