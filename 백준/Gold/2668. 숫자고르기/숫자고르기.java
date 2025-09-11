import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static int N;
    static int[] map;
    static boolean[] visited;
    static boolean[] finished;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1]; // 1-based index
        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N + 1];
        finished = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1]; // 매번 방문 초기화
            dfs(i, i);
        }

        System.out.println(result.size());
        for (int num : result) {
            System.out.println(num);
        }
    }

    private static void dfs(int start, int current) {
        if (!visited[current]) {
            visited[current] = true;
            dfs(start, map[current]);
        } else {
            // 사이클 확인
            if (current == start) {
                result.add(start);
            }
        }
    }
}

