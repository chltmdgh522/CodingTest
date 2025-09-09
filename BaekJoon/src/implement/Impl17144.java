package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Impl17144 {

    static int R;

    static int C;

    static int T;

    static int[][] room;


    static int uaX;
    static int uaY;
    static int baX;
    static int baY;


    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        boolean mFlag = false;
        room = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
                if (room[i][j] == -1 && !mFlag) {
                    uaX = i;
                    uaY = j;
                    baX = i + 1;
                    baY = j;
                    mFlag = true;
                }
            }
        }
        for (int i = 0; i < T; i++) {
            slove();
        }

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] == -1) continue;
                if (room[i][j] == 0) continue;
                result += room[i][j];
            }

        }
        System.out.println(result);

    }

    private static void slove() {
        // 1단계
        Queue<int[]> mungiData = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] <= 0) continue;
                dataMungi(i, j, mungiData);
            }
        }
        if (!mungiData.isEmpty()) {
            dirMungi(mungiData);
        }

        // 2단계
        switchOn();

    }

    private static void dataMungi(int x, int y, Queue<int[]> mungiData) {
        for (int dir = 0; dir < 4; dir++) {
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C) continue;
            if (room[nextX][nextY] == -1) continue;
            mungiData.add(new int[]{nextX, nextY, x, y});
        }
    }

    private static void dirMungi(Queue<int[]> mungiData) {
        int[][] tempRoom = new int[R][C];
        int[] temp = mungiData.peek();
        int coreX = temp[2];
        int coreY = temp[3];
        int size = 0;
        int core = room[temp[2]][temp[3]] / 5;

        while (!mungiData.isEmpty()) {
            int[] poll = mungiData.poll();
            if (coreX == poll[2] && coreY == poll[3]) {
                size++;
            } else {
                room[coreX][coreY] = room[coreX][coreY] - core * size;

                size = 1;
                coreX = poll[2];
                coreY = poll[3];
                core = room[coreX][coreY] / 5;
            }
            if (core == 0) continue;
            tempRoom[poll[0]][poll[1]] += core;
        }

        // 마지막 원소 바꿔주기
        room[coreX][coreY] = room[coreX][coreY] - core * size;

        // 종자는 원본 배열에 그대로 넣어도 무방
        // 단 종자가 퍼뜨린 먼지양은 temp에 저장해서 다음 순서에도 지장없게 해야됨
        // 따라서 마지막에 합치자

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (room[i][j] == -1) continue;
                room[i][j] += tempRoom[i][j];

            }
        }


    }

    private static void switchOn() {

        int uDir = 0;
        int bDir = 2;
        int nuX = uaX + dx[uDir];
        int nuY = uaY + dy[uDir];
        int nbX = baX + dx[bDir];
        int nbY = baY + dy[bDir];

        while (true) {
            // 위 반시계 북 동 남 서
            nuX += dx[uDir];
            nuY += dy[uDir];

            if (nuX < 0 || nuX >= R || nuY < 0 || nuY >= C) {
                nuX -= dx[uDir];
                nuY -= dy[uDir];
                uDir = uDir + 1 >= 4 ? 0 : uDir + 1;
                nuX += dx[uDir];
                nuY += dy[uDir];
            }

            if (room[nuX][nuY] == -1) {
                room[nuX - dx[uDir]][nuY - dy[uDir]] = 0;
                break;
            }
            room[nuX - dx[uDir]][nuY - dy[uDir]] = room[nuX][nuY];
            if (nuX == uaX) {
                uDir = 3;
            }

        }

        while (true) {
            // 아래 시계 남 동 북 서
            nbX += dx[bDir];
            nbY += dy[bDir];

            if (nbX < 0 || nbX >= R || nbY < 0 || nbY >= C) {

                nbX -= dx[bDir];
                nbY -= dy[bDir];
                bDir = bDir - 1 < 0 ? 3 : bDir - 1;
                nbX += dx[bDir];
                nbY += dy[bDir];

            }
            if (room[nbX][nbY] == -1) {
                room[nbX - dx[bDir]][nbY - dy[bDir]] = 0;
                break;
            }

            room[nbX - dx[bDir]][nbY - dy[bDir]] = room[nbX][nbY];
            if (nbX == baX) {
                bDir = 3;
            }
        }
    }
}
