
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static int M;

    static int[][] num;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};

    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    static int result;

    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                num[i][j] = input.charAt(j) - '0';
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isSqrt(num[i][j])) {
                    result = Math.max(num[i][j], result);
                }
                slove(i, j);
            }
        }

        if (flag) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }


    }

    private static void slove(int x, int y) {


        for (int k = 1; k < N+1; k++) {
            for (int l = 1; l < M+1; l++) {
                // 방향
                for (int dir = 0; dir < 8; dir++) {
                    StringBuilder sb = new StringBuilder(String.valueOf(num[x][y]));
                    int nextX = x;
                    int nextY = y;
                    while (true) {
                        nextX += dx[dir] * k;
                        nextY += dy[dir] * l;

                        if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                            break;
                        }


                        sb.append(String.valueOf(num[nextX][nextY]));
                        if (isSqrt(Integer.parseInt(sb.toString()))) {
                            result = Math.max(Integer.parseInt(sb.toString()), result);
                        }

                    }
                }
            }

        }
    }

    private static boolean isSqrt(int num) {
        double sqrt = Math.sqrt(num);
        if (sqrt == Math.floor(sqrt)) {
            flag = true;
        }
        return sqrt == Math.floor(sqrt);
    }


}
