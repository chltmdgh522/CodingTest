package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Impl17143 {
    static int R;
    static int C;

    static int M;

    static Shaker[][] map;

    static class Shaker {
        int s, d, z;

        public Shaker(int s, int d, int z) {
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }

    static int result;

    // 북 남 동 서
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new Shaker[R + 1][C + 1];


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            Shaker shaker = new Shaker(s, d, z);
            map[r][c] = shaker;

        }

        for (int i = 1; i <= C; i++) {
            // 1. 사람 열 한칸씩 이동
            slove(i);
        }
        System.out.println(result);
    }

    private static void slove(int people) {
        // 2. people 열에서 상어를 잡자
        for (int row = 1; row <= R; row++) {
            if (map[row][people] != null) {
                Shaker shaker = map[row][people];
                result += shaker.z;
                map[row][people] = null;
                break;
            }

        }

        // 상어 대이동하기 위해 임시 변수 할당
        List<Shaker>[][] temp = new ArrayList[R + 1][C + 1];
        for (int i = 0; i <= R; i++) {
            for (int j = 0; j <= C; j++) {
                temp[i][j] = new ArrayList<>();
            }
        }


        // 3. 상어 대이동
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (map[i][j] == null) continue;

                Shaker shaker = map[i][j];
                int nx = i;
                int ny = j;
                int dir = shaker.d;

                if (shaker.s == 0) {
                    temp[nx][ny].add(shaker);
                    map[i][j] = null;
                    continue;
                }

                // 방향 전환 및 속도 이동
                for (int speed = 0; speed < shaker.s; speed++) {
                    nx += dx[dir];
                    ny += dy[dir];
                    if (nx <= 0 || nx >= R + 1 || ny <= 0 || ny >= C + 1) {
                        nx -= dx[dir];
                        ny -= dy[dir];
                        int newDir = switchDir(dir);
                        shaker.d = newDir;
                        nx += dx[newDir];
                        ny += dy[newDir];
                        dir = newDir;
                    }
                }
                temp[nx][ny].add(shaker);
                map[i][j] = null;

            }
        }

        // 중복 자리 잡아먹기 및 저장
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (temp[i][j].isEmpty()) continue;
                if (temp[i][j].size() == 1) map[i][j] = temp[i][j].get(0);
                else {
                    Shaker winShaker = sizeCompare(temp[i][j]);
                    map[i][j] = winShaker;
                }
            }
        }
    }

    private static int switchDir(int dir) {
        switch (dir) {
            case 1:
                dir = 2;
                break;
            case 2:
                dir = 1;
                break;
            case 3:
                dir = 4;
                break;
            case 4:
                dir = 3;
                break;
        }

        return dir;
    }

    private static Shaker sizeCompare(List<Shaker> shakers) {
        Shaker maxShaker = null;
        int max = 0;
        for (Shaker shaker : shakers) {
            if (shaker.z > max) {
                maxShaker = shaker;
                max = shaker.z;
            }
        }


        return maxShaker;
    }
}
