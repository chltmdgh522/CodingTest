import java.io.*;
import java.util.*;

public class Main {
    static int[] map;
    static boolean[] visited, finished;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            map = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            cnt = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= N; i++) {
                if (!visited[i]) dfs(i);
            }

            sb.append(N - cnt).append("\n");
        }

        System.out.print(sb);
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        int next = map[cur];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) { 
            // 사이클 발견
            cnt++;
            for (int i = next; i != cur; i = map[i]) {
                cnt++;
            }
        }

        finished[cur] = true;
    }
}
