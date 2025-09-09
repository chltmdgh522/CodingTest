package bra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Bra17281 {

    static int N;
    static int[][] people;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        people = new int[N][9];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번 타자를 4번 타순(인덱스 3)에 고정 → 나머지 8명 순열 생성
        boolean[] visited = new boolean[9];
        int[] battingOrder = new int[9];

        battingOrder[3] = 0; // 1번 타자 고정
        visited[0] = true;

        makeOrder(0, battingOrder, visited);
        System.out.println(result);
    }

    // 백트래킹으로 타순 생성
    private static void makeOrder(int depth, int[] battingOrder, boolean[] visited) {
        if (depth == 9) {

            simulation(battingOrder);
            return;
        }

        if (depth == 3) { // 4번 타자는 이미 고정
            makeOrder(depth + 1, battingOrder, visited);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                battingOrder[depth] = i;
                makeOrder(depth + 1, battingOrder, visited);
                visited[i] = false;
            }
        }
    }

    //
    private static void simulation(int[] battingOrder) {
        int point = 0;
        int batter = 0;

        for (int inning = 0; inning < N; inning++) {
            int out = 0;
            int b1 = 0, b2 = 0, b3 = 0;

            while (out < 3) {
                int player = battingOrder[batter];
                int play = people[inning][player];

                switch (play) {
                    case 0: // 아웃
                        out++;
                        break;
                    case 1: // 안타
                        point += b3;
                        b3 = b2;
                        b2 = b1;
                        b1 = 1;
                        break;
                    case 2: // 2루타
                        point += b3 + b2;
                        b3 = b1;
                        b2 = 1;
                        b1 = 0;
                        break;
                    case 3: // 3루타
                        point += b3 + b2 + b1;
                        b3 = 1;
                        b2 = 0;
                        b1 = 0;
                        break;
                    case 4: // 홈런
                        point += b3 + b2 + b1 + 1;
                        b1 = b2 = b3 = 0;
                        break;
                }

                batter++;
                if (batter == 9) batter = 0;
            }
        }

        result = Math.max(result, point);
    }

}
