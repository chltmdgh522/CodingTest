
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    static int M;

    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Virus {
        int x, y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Virus> virusList;

    static List<int[]> selectVirus = new ArrayList<>();

    static int answer = Integer.MAX_VALUE;

    static boolean[][] visit;

    static boolean answerFlag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        virusList = new ArrayList<>();
        map = new int[N][N];
        boolean zeroFlag = false;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) virusList.add(new Virus(i, j));
                if (map[i][j] == 0) zeroFlag = true;
            }
        }

        if (zeroFlag) {
            dfs(0,0);
            if (answerFlag) System.out.println(answer);
            else System.out.println(-1);
        }else System.out.println(0);
    }

    private static void dfs(int depth, int start) {
        if (depth == M) {
            Queue<int[]> queue = new LinkedList<>();
            visit = new boolean[N][N];
            int[][] clone = new int[map.length][];
            for (int i = 0; i < map.length; i++) {
                clone[i] = map[i].clone();
            }
            for (int[] virus : selectVirus) {
                queue.add(virus);
                visit[virus[0]][virus[1]] = true;
                clone[virus[0]][virus[1]] = 3;
            }

            int time = virusSpread(queue, clone);

            if (time >= 0) {
                answer = Math.min(answer, time);
                answerFlag = true;
            }

            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            Virus virus = virusList.get(i);
            selectVirus.add(new int[]{virus.x, virus.y});
            dfs(depth + 1, i+1);
            selectVirus.remove(selectVirus.size() - 1);
        }
    }

    private static int virusSpread(Queue<int[]> queue, int[][] clone) {
        int time = 1;
        boolean nonVirus;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int sx = poll[0];
                int sy = poll[1];

                for (int dir = 0; dir < 4; dir++) {
                    int nx = sx + dx[dir];
                    int ny = sy + dy[dir];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (clone[nx][ny] == 1 || visit[nx][ny]) continue;

                    queue.add(new int[]{nx, ny});
                    visit[nx][ny] = true;
                    clone[nx][ny] = 3;

                }
            }

            nonVirus = false;
            outer:
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (clone[i][j] == 0) {
                        nonVirus = true;
                        break outer;
                    }
                }
            }

            if (nonVirus) {
                time++;
            } else break;

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (clone[i][j] == 0) {
                    time = -1;
                    break;
                }
            }
        }
        return time;
    }
}
