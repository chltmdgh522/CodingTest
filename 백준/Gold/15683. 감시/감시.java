
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    static int[][] map;

    static int min;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static List<int[]> cctvs;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new int[]{i, j});
                }
            }
        }

        min = N * M;
        cctvSolve(0);
        System.out.println(min);

    }

    // 1은 북 2는 동 3은 남 4는 서 dir
    public static void cctvSolve(int depth) {
        if (depth == cctvs.size()) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        sum++;
                    }
                }
            }
            min = Math.min(min, sum);
            return;
        }

        int[] cctvPlace = cctvs.get(depth);
        int sx = cctvPlace[0];
        int sy = cctvPlace[1];

        for (int dir = 1; dir < 5; dir++) {
            search(sx, sy, dir, true);
            cctvSolve(depth + 1);
            search(sx, sy, dir, false);
        }
    }


    static void search(int x, int y, int dir, boolean flag) {
        if (map[x][y] == 1) fill(dir, x, y, flag);
        else if (map[x][y] == 2) {
            int fDir = dir + 2 >= 5 ? dir - 2 : dir + 2;
            fill(dir, x, y, flag);
            fill(fDir, x, y, flag);
        } else if (map[x][y] == 3) {
            int fDir = dir + 1 >= 5 ? dir - 3 : dir + 1;
            fill(dir, x, y, flag);
            fill(fDir, x, y, flag);
        } else if (map[x][y] == 4) {
            int fDir = dir + 2 >= 5 ? dir - 2 : dir + 2;
            for (int i = 1; i < 5; i++) {
                if (i == fDir) {
                    continue;
                }
                fill(i, x, y, flag);

            }
        } else {
            for (int i = 1; i < 5; i++) {
                fill(i, x, y, flag);
            }
        }


    }

    static void fill(int dir, int x, int y, boolean flag) {

        if (flag) {
            int nX = x + dx[dir - 1];
            int nY = y + dy[dir - 1];

            if (nX >= 0 && nX < N && nY >= 0 && nY < M) {
                if (map[nX][nY] != 6) {
                    if (map[nX][nY] == 0) {
                        map[nX][nY] = 7;
                    } else if (map[nX][nY] >= 7) {
                        map[nX][nY] += 1;
                    }
                    fill(dir, nX, nY, true);
                }
            }
        } else {
            int nX = x + dx[dir - 1];
            int nY = y + dy[dir - 1];

            if (nX >= 0 && nX < N && nY >= 0 && nY < M) {
                if (map[nX][nY] != 6) {
                    if (map[nX][nY] > 7) {
                        map[nX][nY] -= 1;
                    } else if (map[nX][nY] == 7) {
                        map[nX][nY] = 0;
                    }
                    fill(dir, nX, nY, false);
                }
            }
        }
    }
}
