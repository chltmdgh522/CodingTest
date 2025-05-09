import java.io.*;
import java.util.*;

public class Solution {

    static int[][] map;

    static int N, M;



    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N + 1][N + 1];

            // 백돌
            map[N/2][N/2] = 2;
            map[N/2+1][N/2+1] = 2;

            // 흑돌
            map[N/2+1][N/2] = 1;
            map[N/2][N/2+1] = 1;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                // 뒤집어야됨
                map[y][x] = value;

                for (int k = 0; k < 8; k++) {
                    int backdok = value;
                    solution(y, x, dx[k], dy[k], backdok);
                }
            }

            int black = 0;
            int white = 0;
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (map[i][j] == 1) {
                        black++;
                    } else if (map[i][j] == 2) {
                        white++;
                    }
                }

            }

            System.out.println("#" + test + " " + black + " " + white);

        }
    }

    // 공격 개시
    private static void solution(int x, int y, int nX, int nY, int backdok) {

        boolean flag = false;
        int cnt = 0;
        int nextX = x;
        int nextY = y;
        while (true) {

            nextX += nX;
            nextY += nY;

            if (nextX <= 0 || nextX >= N + 1 || nextY <= 0 || nextY >= N + 1) {
                break;
            }

            if (map[nextX][nextY] == 0) {
                break;
            }

            if (map[nextX][nextY] == backdok) {
                if (flag) {
                    int staticX = x;
                    int staticY = y;
                    for (int i = 0; i < cnt; i++) {
                        staticX += nX;
                        staticY += nY;
                        map[staticX][staticY] = backdok;
                    }

                }
                break;

            } else {
                flag = true;
                cnt++;
            }
        }
    }

}


