import java.io.*;
import java.util.*;

public class Solution {
    static int N;

    static String[][] alphabet;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int test = 1; test < 11; test++) {
            N = Integer.parseInt(br.readLine());
            alphabet = new String[100][100];
            result = 0;
            for (int i = 0; i < 100; i++) {
                String input = br.readLine();
                for (int j = 0; j < 100; j++) {
                    alphabet[i][j] = String.valueOf(input.charAt(j));
                }
            }

            solution();
            System.out.println("#" + N + " " + result);
        }
    }

    public static void solution() {
        // 가로
        for (int row = 0; row < 100; row++) {
            boolean[][] dp = new boolean[100][100];

            for (int len = 1; len <= 100; len++) {
                for (int i = 0; i + len - 1 < 100; i++) {
                    int j = i + len - 1;

                    if (len == 1) {
                        dp[i][j] = true;
                    } else if (len == 2) {
                        dp[i][j] = alphabet[row][i].equals(alphabet[row][j]);
                    } else {
                        dp[i][j] = alphabet[row][i].equals(alphabet[row][j]) && dp[i + 1][j - 1];
                    }

                    if (dp[i][j]) {
                        result = Math.max(result, len);
                    }
                }
            }
        }

        // 세로
        for (int col = 0; col < 100; col++) {
            boolean[][] dp = new boolean[100][100];

            for (int len = 1; len <= 100; len++) {
                for (int i = 0; i + len - 1 < 100; i++) {
                    int j = i + len - 1;

                    if (len == 1) {
                        dp[i][j] = true;
                    } else if (len == 2) {
                        dp[i][j] = alphabet[i][col].equals(alphabet[j][col]);
                    } else {
                        dp[i][j] = alphabet[i][col].equals(alphabet[j][col]) && dp[i + 1][j - 1];
                    }

                    if (dp[i][j]) {
                        result = Math.max(result, len);
                    }
                }
            }
        }
    }

}