import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static boolean[] finished;

    static int[] map;

    static boolean[] visit;

    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            visit = new boolean[N + 1];
            finished = new boolean[N + 1];
            map = new int[N + 1];
            result = 0;

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[j] = Integer.parseInt(st.nextToken());
            }


            for (int k = 1; k <= N; k++) {
                if (!visit[k]) {
                    teamMatching(k);
                }
            }


            System.out.println(N - result);

        }
    }

    private static void teamMatching(int cur) {
        visit[cur] = true;
        int next = map[cur];

        if (!visit[next]) {
            teamMatching(next);
        } else if (!finished[next]) {
            int i = next;

            while (i != cur) {
                result++;
                i = map[i];
            }
            result++; // 자기 자신
        }

        finished[cur] = true;

    }
}
