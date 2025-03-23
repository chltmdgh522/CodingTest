import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] podo = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            podo[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = podo[1];
       if (N > 1) {
            dp[2] = podo[1] + podo[2];
        }

        for (int i = 3; i < N + 1; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 3] + podo[i - 1] + podo[i], dp[i - 2] + podo[i]));
        }

        System.out.println(dp[N]);


    }
}
