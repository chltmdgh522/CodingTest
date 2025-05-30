
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    static int L;
    static int R;

    static int[][] map;

    static int result = 0;

    // visit가 true일때 탐색 불가 왜냐하면 동시에 돌아서 데이터 정합성 깨짐 따라서 wall이랑 같이함 wall은 걍 1회성


    // false면 벽이 있어 true면 벽이 없어 벽이없으면 탐색가능

    static boolean resultFlag;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        countDay();
        System.out.println(result);
    }

    private static void countDay() {

        while (true) {
           resultFlag = false;
            boolean[][] visit = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visit[i][j]) {
                        slove(i, j, visit);
                    }
                }
            }

            if (!resultFlag) {
                break;
            }

            result++;
        }

    }

    private static void slove(int x, int y, boolean[][] visit) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> wall = new ArrayList<>();
        visit[x][y] = true;
        wall.add(new int[]{x,y});
        queue.add(new int[]{x, y});
        int cnt = 1;
        int sum = map[x][y];
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int sX = temp[0];
            int sY = temp[1];

            for (int i = 0; i < 4; i++) {
                int nextX = sX + dx[i];
                int nextY = sY + dy[i];
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                    continue;
                }
                if (visit[nextX][nextY]) {
                    continue;
                }
                int substance = Math.abs(map[sX][sY] - map[nextX][nextY]);
                if (substance >= L && substance <= R) {
                    resultFlag = true;
                    visit[nextX][nextY] = true;
                    cnt++;
                    sum += map[nextX][nextY];
                    queue.add(new int[]{nextX, nextY});
                    wall.add(new int[]{nextX,nextY});
                }
            }
        }

        int value = sum / cnt;

        for (int[] pos : wall) {
            map[pos[0]][pos[1]] = value;
        }

    }

}
