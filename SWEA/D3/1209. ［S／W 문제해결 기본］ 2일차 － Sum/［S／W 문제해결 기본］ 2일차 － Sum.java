import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int test = 1; test <= 10; test++) {
            StringTokenizer st;
            map = new int[100][100];
            int T = Integer.parseInt(br.readLine());
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 100; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result = 0;
            solution();
            System.out.println("#" + test + " " + result);
        }


    }

    private static void solution() {
        int fsum = 0;
        int bsum = 0;

        for (int i = 0; i < 100; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < 100; j++) {
                rowSum += map[i][j];
                colSum += map[j][i];
            }

            result = Math.max(rowSum, result);
            result = Math.max(colSum, result);

            fsum += map[i][i];
            bsum += map[i][99 - i];
        }

        result = Math.max(fsum, result);
        result = Math.max(bsum, result);
    }
}
