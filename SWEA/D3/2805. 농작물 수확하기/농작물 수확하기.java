import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;
    static int sum;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test < T + 1; test++) {
            N = Integer.parseInt(br.readLine());
            sum = 0;
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String num = br.readLine();
                for (int j = 0; j < num.length(); j++) {
                    map[i][j] = Integer.parseInt(String.valueOf(num.charAt(j)));
                }
            }

            int front = N / 2 + 1;
            int back = N / 2;
            farm(front, back);
            System.out.println("#" + test + " " + sum);
        }
    }

    public static void farm(int front, int back) {
        int fX = 0;
        int fY = back;

        int bX = 1;
        int bY = back;

        for (int i = 0; i < front; i++) {
            int fnX = fX;
            int fnY = fY;
            for (int j = 0; j < front; j++) {
                sum += map[fnX][fnY];
                fnX += 1;
                fnY -= 1;
            }
            fX += 1;
            fY += 1;

        }

        for (int i = 0; i < back; i++) {
            int bnX = bX;
            int bnY = bY;
            for (int j = 0; j < back; j++) {
                sum += map[bnX][bnY];
                bnX += 1;
                bnY -= 1;
            }
            bX += 1;
            bY += 1;

        }
    }

}
