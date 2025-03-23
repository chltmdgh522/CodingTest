
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class From {
        int x;
        int y;

        public From(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class To {
        int x;
        int y;

        public To(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        From from;
        To to;

        public Node(From from, To to) {
            this.from = from;
            this.to = to;
        }
    }

    static int N;

    static int count = 0;
    static int[][] map;

    static int[] dx = {1, 0, 1};
    static int[] dy = {0, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (!(map[N - 1][N - 1] == 1)) {
            bfs();
        }

        if (count != 0) {
            System.out.println(count);
        } else {
            System.out.println(0);
        }
    }

    static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(new From(1, 1), new To(1, 2)));

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            From from = poll.from;
            To to = poll.to;

            // 가로, 세로, 대각선 인지 검토
            if (to.x - from.x == 0 && to.y - from.y == 1) {
                question1(queue, to, 1);
                question2(queue, to);

            } else if (to.x - from.x == 1 && to.y - from.y == 0) {
                question1(queue, to, 0);
                question2(queue, to);
            } else {
                question1(queue, to, 1);
                question1(queue, to, 0);
                question2(queue, to);
            }

        }


    }


    // 가로 및 세로
    static void question1(Queue queue, To to, int k) {
        int nextX = to.x + dx[k];
        int nextY = to.y + dy[k];
        if (nextX - 1 < N && nextY - 1 < N && nextX - 1 >= 0 && nextY - 1 >= 0) {
            if (map[nextX - 1][nextY - 1] == 0) {
                queue.add(new Node(new From(to.x, to.y), new To(nextX, nextY)));
                if (nextX == N && nextY == N) {

//                System.out.println("가로 및 세로");
//                System.out.println(to.x + " " + to.y);
                    count++;
                }
            }

        }
    }

    // 대각선
    static void question2(Queue queue, To to) {
        int nextX = to.x + dx[2];
        int nextY = to.y + dy[2];
        if (nextX - 1 < N && nextY - 1 < N && nextX - 1 >= 0 && nextY - 1 >= 0) {
            if (map[nextX - 1][nextY - 1] == 0 && map[nextX - 1][nextY - 2] == 0 && map[nextX - 2][nextY - 1] == 0) {
                queue.add(new Node(new From(to.x, to.y), new To(nextX, nextY)));
                if (nextX == N && nextY == N) {
                     count++;
                }
            }

        }
    }
}
