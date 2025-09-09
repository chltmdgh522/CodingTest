package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Implement14890 {
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
            if (check(row)) {
                answer++;
            }
        }

        // 세로 줄 체크
        for (int i = 0; i < N; i++) {
            int[] col = new int[N];
            for (int j = 0; j < N; j++) {
                col[j] = map[j][i];
            }
            if (check(col)) {
                answer++;
            }
        }

        return answer;
    }

    private static boolean check(int[] line) {
        boolean[] visit = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int diff = line[i + 1] - line[i];
            // 같을때 넘기기
            if (diff == 0) {
                continue;
            }
            // 양수 오르막길
            else if (diff == 1) {
                for (int j = 0; j < L; j++) {
                    int idx = i - j;
                    if (idx < 0  || line[i] != line[idx] || visit[idx]) {
                        return false;
                    }
                    visit[idx] = true;
                }
            }
            // 음수 내리막길
            else if (diff == -1) {
                for (int j = 0; j < L; j++) {
                    int idx = i + j + 1;
                    if (idx >=N  || line[i+1] != line[idx] || visit[idx]) {
                        return false;
                    }
                    visit[idx] = true;
                }

            }
            // 차이가 2 이상이면 false
            else {
                return false;
            }
        }

        return true;
    }

}
