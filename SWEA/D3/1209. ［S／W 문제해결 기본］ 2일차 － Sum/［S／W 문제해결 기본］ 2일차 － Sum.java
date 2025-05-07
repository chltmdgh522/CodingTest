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
        //가로
        for (int i = 0; i < 100; i++) {
            int sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += map[i][j];
            }

            result = Math.max(sum, result);
        }

        //세로
        for (int i = 0; i < 100; i++) {
            int sum = 0;
            for (int j = 0; j < 100; j++) {
                sum += map[j][i];
            }

            result = Math.max(sum, result);
        }

        //앞대각선
        int fsum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = i; j < i + 1; j++) {
                fsum += map[j][i];
            }
        }
        result = Math.max(fsum, result);

        //뒤대각선
        int bsum = 0;
        for (int i = 99; i >= 0; i--) {
            for (int j = i; j > i - 1; j--) {
                bsum += map[j][i];
            }
        }
        result = Math.max(bsum, result);

    }
}
