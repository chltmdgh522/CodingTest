import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;

    static int L;

    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = solution();
        System.out.println(result);

    }

    private static int solution() {
        int answer = 0;

        // 가로 줄 체크
        for (int i = 0; i < N; i++) {
            int[] row = new int[N];
            for (int j = 0; j < N; j++) {
                row[j] = map[i][j];
            }
            if (check(row)) answer++;
        }

        // 세로 줄 체크
        for (int j = 0; j < N; j++) {
            int[] col = new int[N];
            for (int i = 0; i < N; i++) {
                col[i] = map[i][j];
            }
            if (check(col)) answer++;
        }

        return answer;
    }

    private static boolean check(int[] line) {
        boolean[] visited = new boolean[N];  // 경사로 설치 여부

        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];

            if (diff == 0) continue;

            else if (diff == 1) { // 오르막
                for (int j = 0; j < L; j++) {
                    int idx = i - j;
                    if (idx < 0 || line[idx] != line[i] || visited[idx]) return false;
                    visited[idx] = true;
                }
            } else if (diff == -1) { // 내리막
                for (int j = 1; j <= L; j++) {
                    int idx = i + j;
                    if (idx >= N || line[idx] != line[i + 1] || visited[idx]) return false;
                    visited[idx] = true;
                }
            } else {
                return false; // 높이 차가 2 이상
            }
        }

        return true;
    }

}
