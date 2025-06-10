
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    static int[][] map;

    static int sum=0;

    static class Dom {
        int x, y, d1, d2;

        public Dom(int x, int y, int d1, int d2) {
            this.x = x;
            this.y = y;
            this.d1 = d1;
            this.d2 = d2;
        }
    }

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum+=map[i][j];
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 <= N; d1++) {
                    for (int d2 = 1; d2 <= N; d2++) {


                        if (x + d1 + d2 > N) break;
                        if (y - d1 < 1) break;
                        if (y + d2 > N) break;
                        solve(x,y,d1,d2);
                    }
                }

            }
        }



        System.out.println(answer);

    }

    private static void solve(int x, int y, int d1, int d2) {
        int[][] temp = new int[N][N];


        bounder(temp, new Dom(x, y, d1, d2));
        int[] count = new int[5];
        for (int k = 1; k <= 4; k++) {
            int endX = 0;
            int endY = 0;
            int startX = 0;
            int startY = 0;
            switch (k) {
                case 1:
                    startX = 1;
                    startY = 1;
                    endX = x + d1;
                    endY = y + 1;
                    break;

                case 2:
                    startX = 1;
                    startY = y + 1;
                    endX = x + d2 + 1;
                    endY = N + 1;
                    break;

                case 3:
                    startX = x + d1;
                    startY = 1;
                    endX = N + 1;
                    endY = y - d1 + d2;
                    break;

                case 4:
                    startX = x + d2 + 1;
                    startY = y - d1 + d2;
                    endX = N + 1;
                    endY = N + 1;
                    break;

            }

            if (k == 1 || k == 3) {
                for (int a = startX; a < endX; a++) {
                    for (int b = startY; b < endY; b++) {
                        if (temp[a - 1][b - 1] == 5) break;
                        temp[a - 1][b - 1] = k;
                        count[k-1] += map[a - 1][b - 1];

                    }
                }
            } else {
                for (int a = endX - 1; a >= startX; a--) {
                    for (int b = endY - 1; b >= startY; b--) {
                        if (temp[a - 1][b - 1] == 5) break;
                        temp[a - 1][b - 1] = k;
                        count[k-1] += map[a - 1][b - 1];

                    }
                }
            }

        }


        count[4] =  sum - count[0] - count[1] - count[2] - count[3];



         Arrays.sort(count);
        int abs = Math.abs(count[4] - count[0]);
        answer = Math.min(abs, answer);

    }

    private static void bounder(int[][] temp, Dom dom) {
        Queue<int[]> queue = new LinkedList<>();
        for (int k = 1; k <= 4; k++) {
            switch (k) {
                case 1:
                    queue.add(new int[]{dom.x, dom.y});
                    queue.add(new int[]{dom.x + dom.d1, dom.y - dom.d1});
                    break;
                case 2:
                    queue.add(new int[]{dom.x, dom.y});
                    queue.add(new int[]{dom.x + dom.d2, dom.y + dom.d2});
                    break;
                case 3:
                    queue.add(new int[]{dom.x + dom.d1, dom.y - dom.d1});
                    queue.add(new int[]{dom.x + dom.d1 + dom.d2, dom.y - dom.d1 + dom.d2});
                    break;
                case 4:
                    queue.add(new int[]{dom.x + dom.d2, dom.y + dom.d2});
                    queue.add(new int[]{dom.x + dom.d2 + dom.d1, dom.y + dom.d2 - dom.d1});
                    break;
            }


            int[] poll1 = queue.poll();
            int[] poll2 = queue.poll();

            int startX = poll1[0] - 1;
            int startY = poll1[1] - 1;

            int endX = poll2[0] - 1;
            int endY = poll2[1] - 1;

            temp[startX][startY] = 5;
            temp[endX][endY] = 5;

            while (!(startX == endX && startY == endY)) {

                if (endX - startX > 0) startX++;
                else if (endX - startX < 0) startX--;

                if (endY - startY > 0) startY++;
                else if (endY - startY < 0) startY--;

                temp[startX][startY] = 5;

            }

        }

    }
}

