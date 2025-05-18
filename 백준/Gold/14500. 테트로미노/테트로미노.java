
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    static int[][] map;

    static boolean[][] visit;
    static int answer = Integer.MIN_VALUE;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visit[i][j] = true;
                solution(i, j, map[i][j], 1);
                visit[i][j] = false;
            }
        }

        System.out.println(answer);

    }

    private static void solution(int row, int col, int sum, int count) {
        if (count == 4) {
            answer = Math.max(sum, answer);
            return;
        }


        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];

            if (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {

                if (!visit[nextRow][nextCol]) {

                    if (count == 2) {
                        visit[nextRow][nextCol] = true;
                        solution(row, col, sum + map[nextRow][nextCol], count + 1);
                        visit[nextRow][nextCol] = false;
                    }

                    visit[nextRow][nextCol] = true;
                    solution(nextRow, nextCol, sum + map[nextRow][nextCol], count + 1);
                    visit[nextRow][nextCol] = false;
                }
            }
        }

    }

}
