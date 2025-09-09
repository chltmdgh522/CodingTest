package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DataStructure3190 {
    static int N;

    static int[][] map;
    static Queue<Object[]> state;


    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 사과 배정
            map[x - 1][y - 1] = 2;
        }

        int L = Integer.parseInt(br.readLine());
        state = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            String alarm = st.nextToken();
            state.add(new Object[]{cnt, alarm});
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0});
        int time = 0;
        int dir = 1;

        while (!deque.isEmpty()) {
            int[] poll = deque.peekLast();
            int x = poll[0];
            int y = poll[1];

            // 방향 전환
            if (!state.isEmpty()) {
                Object[] peek = state.peek();
                if (time == (int) peek[0]) {

                    if (peek[1].equals("D")) {
                        dir = dir + 1 > 3 ? 0 : dir + 1;
                        //System.out.println(x + " " + y);
                    } else if (peek[1].equals("L")) {
                        //System.out.println(x + " " + y);
                        dir = dir - 1 < 0 ? 3 : dir - 1;
                    }
                    state.poll();
                }
            }



            //이동
            int nX = x + dx[dir];
            int nY = y + dy[dir];

            // 벽
            if (nX >= N || nY >= N || nX < 0 || nY < 0) {
                break;
            }
            // 내 자신
            if (map[nX][nY] == 1) {
                break;
            }
            // 사과
            if (map[nX][nY] == 2) {
                map[nX][nY] = 1;
                deque.add(new int[]{nX, nY});
            } else {

                int[] first = deque.poll();
                map[first[0]][first[1]] = 0;
                map[nX][nY] = 1;
                deque.add(new int[]{nX, nY});
            }


            time++;
        }

        return time + 1;
    }


}
