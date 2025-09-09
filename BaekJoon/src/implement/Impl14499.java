package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Impl14499 {
    static int N;

    static int M;

    static int K;
    static int x;

    static int y;

    static int[][] map;

    static int[] command;

    static List<Integer> result;

    /// 동 서 북 남
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static int[] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        command = new int[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        solution();
        for (Integer i : result) {
            System.out.println(i);
        }
    }

    private static void solution() {
        result = new ArrayList<>();
        dice = new int[7];

        int nextX = x;
        int nextY = y;
        for (int i = 0; i < K; i++) {
            int dir = command[i];
            nextX += dx[dir];
            nextY += dy[dir];

            if (nextX >= 0 && nextX < N && nextY >= 0 & nextY < M) {
                int value = map[nextX][nextY];
                diceLogic(dir);
                if (value == 0) {
                    map[nextX][nextY] = dice[6];
                } else {
                    dice[6] = value;
                    map[nextX][nextY] = 0;
                }
                result.add(dice[1]);
            } else {
                nextX -= dx[dir];
                nextY -= dy[dir];
            }

        }
    }

    static void diceLogic(int dir) {
        // 1: top, 2: back, 3: right, 4: left, 5: front, 6: bottom

        // 동
        if (dir == 1) {
            int temp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        }
        // 서
        else if (dir == 2) {
            int temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        }
        // 북
        else if (dir == 3) {
            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
        // 남
        else {
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }
    }

}
