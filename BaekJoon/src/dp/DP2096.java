package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DP2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] dpMin = new int[N][3];
        int[][] dpMax = new int[N][3];

        // 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (i == 0) {
                dpMin[i][0] = a;
                dpMin[i][1] = b;
                dpMin[i][2] = c;

                dpMax[i][0] = a;
                dpMax[i][1] = b;
                dpMax[i][2] = c;

            } else {
                dpMin[i][0] = Math.min(dpMin[i - 1][0] + a, dpMin[i - 1][1] + a);
                dpMin[i][1] = Math.min(Math.min(dpMin[i - 1][0] + b, dpMin[i - 1][1] + b), dpMin[i - 1][2] + b);
                dpMin[i][2] = Math.min(dpMin[i - 1][1] + c, dpMin[i - 1][2] + c);

                dpMax[i][0] = Math.max(dpMax[i - 1][0] + a, dpMax[i - 1][1] + a);
                dpMax[i][1] = Math.max(Math.max(dpMax[i - 1][0] + b, dpMax[i - 1][1] + b), dpMax[i - 1][2] + b);
                dpMax[i][2] = Math.max(dpMax[i - 1][1] + c, dpMax[i - 1][2] + c);
            }

        }

        // 출력
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dpMax[N - 1][i]);
            min = Math.min(min, dpMin[N - 1][i]);
        }
        System.out.println(max + " " + min);


    }
}
