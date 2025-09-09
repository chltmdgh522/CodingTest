package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Impl15685 {
    static int N;

    static int[][] map = new int[101][101];

    // 동 북 서 남 (0,1,2,3)
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            map[y][x] += 1;
            slove(y, x, dir, g);
        }

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (isSquare(j, i)) {
                    result++;
                }
            }
        }


        System.out.println(result);

    }

    private static boolean isSquare(int x, int y) {
        if(map[x][y]==0){
            return false;
        }

        if(map[x][y+1] ==0){
            return false;
        }

        if(map[x+1][y] ==0){
            return false;
        }

        if(map[x+1][y+1] ==0){
            return false;
        }

        return true;
    }

    private static void slove(int x, int y, int dir, int g) {
        int gCnt = (int) Math.pow(2, g);
        List<Integer> dirs = new ArrayList<>();
        int nextX = x;
        int nextY = y;
        dirs.add(dir);

        int compare = 1;
        for (int k = 1; k < gCnt + 1; k++) {
            nextX += dx[dirs.get(k-1)];
            nextY += dy[dirs.get(k -1)];
            if (nextX < 0 || nextX >= 101 || nextY < 0 || nextY >= 101) {
                break;
            }
            map[nextX][nextY] += 1;

            // 세대 바꿀떄 dir 바꿔서 추가
            if (k == 1 || compare * 2 == k) {
                List<Integer> tempDirs = new ArrayList<>();
                for (int i= dirs.size()-1; i>=0; i-- ) {
                    int eDir = dirs.get(i);
                    int newDir = eDir + 1 >= 4 ? 0 : eDir + 1;
                    tempDirs.add(newDir);
                }
                dirs.addAll(tempDirs);
                compare = k;
            }

        }
    }
}
