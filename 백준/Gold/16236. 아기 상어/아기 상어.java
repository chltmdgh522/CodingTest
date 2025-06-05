import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 0, 1}; // 상, 좌, 우, 하
    static int[] dy = {0, -1, 1, 0};
    static int sharkX, sharkY, sharkSize = 2, eatCount = 0;
    static int time = 0;

    static class Fish implements Comparable<Fish> {
        int x, y, dist;
        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) {
                    return this.y - o.y; // 열이 작은 것 우선
                }
                return this.x - o.x; // 행이 작은 것 우선
            }
            return this.dist - o.dist; // 거리가 작은 것 우선
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    sharkX = i;
                    sharkY = j;
                    map[i][j] = 0; // 상어 자리 비워줌
                }
            }
        }

        while (true) {
            Fish fish = bfs();
            if (fish == null) break;
            time += fish.dist;
            sharkX = fish.x;
            sharkY = fish.y;
            map[sharkX][sharkY] = 0;
            eatCount++;

            if (eatCount == sharkSize) {
                sharkSize++;
                eatCount = 0;
            }
        }

        System.out.println(time);
    }

    static Fish bfs() {
        visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sharkX, sharkY, 0});
        visited[sharkX][sharkY] = true;

        PriorityQueue<Fish> pq = new PriorityQueue<>();

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];
            int dist = now[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] > sharkSize) continue; // 큰 물고기 지나갈 수 없음

                visited[nx][ny] = true;
                if (map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
                    pq.add(new Fish(nx, ny, dist + 1));
                }
                queue.add(new int[]{nx, ny, dist + 1});
            }
        }

        if (pq.isEmpty()) return null;
        return pq.poll(); // 조건에 맞는 물고기 중 우선순위가 가장 높은 애
    }
}
