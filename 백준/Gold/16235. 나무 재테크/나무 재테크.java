
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int M;
    static int K;
    
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

        for (int year = 0; year < K; year++) {
            solve();
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += tree[i][j].size();
            }
        }

        System.out.println(result);
    }

    private static void solve() {
        // 봄과 여름을 함께 처리
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tree[i][j].isEmpty()) {
                    continue;
                }
                
                Collections.sort(tree[i][j]);
                List<Integer> aliveTrees = new ArrayList<>();
                int deadNutrient = 0;

                for (int age : tree[i][j]) {
                    if (map[i][j] >= age) {
                        map[i][j] -= age;
                        aliveTrees.add(age + 1);
                    } else {
                        deadNutrient += age / 2;  // 죽은 나무는 양분으로 변환
                    }
                }
                
                tree[i][j] = aliveTrees;
                map[i][j] += deadNutrient;  // 여름: 죽은 나무 양분 추가
            }
        }

        // 가을: 번식
        List<int[]> newTrees = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int age : tree[i][j]) {
                    if (age % 5 == 0) {
                        for (int dir = 0; dir < 8; dir++) {
                            int nextX = i + dx[dir];
                            int nextY = j + dy[dir];
                            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N) {
                                newTrees.add(new int[]{nextX, nextY});
                            }
                        }
                    }
                }
            }
        }
        
        // 새로운 나무들 추가
        for (int[] pos : newTrees) {
            tree[pos[0]][pos[1]].add(1);
        }

        // 겨울: 양분 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += energy[i][j];
            }
        }
    }
}