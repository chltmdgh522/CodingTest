import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int size = input.length();
        boolean[][] dp = new boolean[size][size];

        for (int one = 0; one < size; one++) {
            dp[one][one] = true;
        }

        for (int two = 0; two < size - 1; two++) {
            if (input.charAt(two) == input.charAt(two + 1)) {
                dp[two][two + 1] = true;
            }
        }

        for (int len = 3; len < size + 1; len++) {
            for (int s_idx = 0; s_idx < size - len + 1; s_idx++) {
                int e_idx = s_idx + len - 1;
                dp[s_idx][e_idx] = input.charAt(s_idx) == input.charAt(e_idx) && dp[s_idx + 1][e_idx - 1];
            }
        }

        simulation(input, dp, size);

    }

    private static void simulation(String input, boolean[][] dp, int size) {
        int[] minCut = new int[size];
        for (int i = 0; i < size; i++) {
            minCut[i] = Integer.MAX_VALUE;

            for (int j = 0; j <= i; j++) {
                if (dp[j][i]) { // j~i가 팰린드롬이면
                    if (j == 0) {
                        minCut[i] = 1; // 전체가 팰린드롬이면 그대로 1
                    } else {
                        minCut[i] = Math.min(minCut[i], minCut[j - 1] + 1);
                    }
                }
            }
        }

        System.out.println(minCut[size - 1]);
    }
}
