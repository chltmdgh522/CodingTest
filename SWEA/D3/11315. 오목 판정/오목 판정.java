import java.io.*;
import java.util.*;

public class Solution {

    static int N;

    static String[][] ohmock;

    static boolean flag;

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            N = Integer.parseInt(br.readLine());

            ohmock = new String[N][N];

            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                for (int j = 0; j < N; j++) {
                    ohmock[i][j] = String.valueOf(input.charAt(j));
                }
            }


            flag = false;
            outer:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (ohmock[i][j].equals(".")) {
                        continue;
                    }
                    for (int k = 0; k < 8; k++) {
                        solution(i, j, k);
                        if (flag) break outer;
                    }
                }
            }

            if (flag) {
                System.out.println("#" + test + " YES");
            } else {
                System.out.println("#" + test + " NO");
            }
        }
    }

    private static void solution(int x, int y, int dist) {
        if (flag) {
            return;
        }
        int cnt = 1;
        int nextX = x;
        int nextY = y;
        while (true) {
            nextX += dx[dist];
            nextY += dy[dist];
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                flag = false;
                break;
            }

            if (ohmock[nextX][nextY].equals(".")) {
                flag = false;
                break;
            }


            cnt++;

            if(cnt ==5){
                flag = true;
                break;
            }

        }
    }

}


