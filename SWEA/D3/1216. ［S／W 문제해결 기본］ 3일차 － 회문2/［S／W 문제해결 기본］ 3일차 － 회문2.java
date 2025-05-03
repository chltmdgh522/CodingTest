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
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                StringBuilder width = new StringBuilder();
                for (int k = j; k < 100; k++) {
                    width.append(alphabet[i][k]);
                    String original = width.toString();
                    String reverse = new StringBuilder(original).reverse().toString();
                    if (original.equals(reverse)) {
                        result = Math.max(result, width.length());
                    }
                }
            }
        }
        // 세로
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                StringBuilder high = new StringBuilder();
                for (int k = j; k < 100; k++) {
                    high.append(alphabet[k][i]);
                    String original = high.toString();
                    String reverse = new StringBuilder(original).reverse().toString();
                    if (original.equals(reverse)) {
                        result = Math.max(result, high.length());
                    }
                }

            }
        }
    }

}