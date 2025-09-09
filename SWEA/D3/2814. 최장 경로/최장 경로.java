import java.io.*;
import java.util.*;

public class Solution {


    static int N;

    static int M;

    static int[][] graph;

    static int cnt;
    static int result;

    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new int[N + 1][N + 1];
            visit = new boolean[N + 1];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int fNode = Integer.parseInt(st.nextToken());
                int bNode = Integer.parseInt(st.nextToken());
                graph[fNode][bNode] = bNode;
                graph[bNode][fNode] = fNode;
            }
            result = 1;
            cnt = 1;

            for (int i = 1; i < N + 1; i++) {

                dfs(i);
            }

            System.out.println("#" + test + " " + result);
        }
    }

    private static void dfs(int currentNode) {
        if (cnt == N) {
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            if (currentNode == i) {
                continue;
            }
            if (graph[currentNode][i] != 0 && !visit[i]) {
                visit[currentNode] = true;
                cnt++;
                result = Math.max(result, cnt);

                dfs(i);
                cnt--;
                visit[currentNode] = false;
            }
        }

    }
}
