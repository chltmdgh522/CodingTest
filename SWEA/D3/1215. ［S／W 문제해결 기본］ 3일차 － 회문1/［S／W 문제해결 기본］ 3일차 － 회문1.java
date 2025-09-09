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
            alphabet = new String[8][8];
            result = 0;
            for (int i = 0; i < 8; i++) {
                String input = br.readLine();
                for (int j = 0; j < 8; j++) {
                    alphabet[i][j] = String.valueOf(input.charAt(j));
                }
            }

            solution();
            System.out.println("#" + test + " " + result);
        }
    }

    public static void solution() {


        // 가로
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 - N + 1; j++) {
                StringBuilder width = new StringBuilder();
                for (int k = j; k < j + N; k++) {
                    width.append(alphabet[i][k]);
                }
                if (width.toString().equals(width.reverse().toString())) {
                    result++;
                }
            }
        }
        // 세로
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8 - N + 1; j++) {
                StringBuilder high = new StringBuilder();
                for (int k = j; k < j + N; k++) {
                    high.append(alphabet[k][i]);
                }
                if (high.toString().equals(high.reverse().toString())) {
                    result++;
                }
            }
        }
    }

}