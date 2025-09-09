package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Impl16235 {
    static int N;

    static int M;

    static int K;

    // 겨울에 줘야되므로 바뀌면 안되

    static int[][] energy;
    static int[][] map;


    static List<Integer>[][] tree;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        energy = new int[N][N];

        tree = new ArrayList[N][N];
        int result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                energy[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
                tree[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            tree[x - 1][y - 1].add(z);
        }

        for (int i = 0; i < K; i++) {
            slove();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += tree[i][j].size();
            }
        }

        System.out.println(result);
    }

    private static void slove() {
        //봄 && 여름
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tree[i][j].isEmpty()) {
                    continue;
                }
                List<Integer> alive = new ArrayList<>();
                Collections.sort(tree[i][j]);
                int death=0;
                for (int k = 0; k < tree[i][j].size(); k++) {
                    int age = tree[i][j].get(k);
                    if (map[i][j] - age >= 0) {
                        alive.add(age + 1);
                        map[i][j] -= age;
                    } else {
                        death += age/2;
                    }
                }

                // 직접 리스트 할당
                tree[i][j] = alive;

                map[i][j] += death;


            }
        }


        // 가을: 번식할 위치들을 먼저 수집
        List<int[]> newTreePositions = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : tree[i][j]) {
                    if (age % 5 == 0) {  // age == 0 조건 제거 (어차피 % 5 == 0이면 걸러짐)
                        for (int dir = 0; dir < 8; dir++) {
                            int nextX = i + dx[dir];
                            int nextY = j + dy[dir];
                            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                                newTreePositions.add(new int[]{nextX, nextY});
                            }
                        }
                    }
                }
            }
        }

        // 새로운 나무들을 한 번에 추가
        for (int[] pos : newTreePositions) {
            tree[pos[0]][pos[1]].add(1);
        }


        // 겨울
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += energy[i][j];
            }
        }

    }

}
