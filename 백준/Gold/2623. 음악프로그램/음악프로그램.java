import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j = 1; j < cnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
                indegree[to] += 1;

                from = to;
            }
        }

        simulation(indegree, graph);
    }

    private static void simulation(int[] indegree, List<List<Integer>> graph) {
        StringBuilder result = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int poll = queue.poll();
            result.append(poll).append("\n");
            count++;
            List<Integer> nodeto = graph.get(poll);
            for (Integer to : nodeto) {
                indegree[to] -= 1;
                if (indegree[to] == 0) {
                    queue.add(to);
                }
            }
        }
        if (count != N) {
            System.out.println(0);
        } else {
            System.out.println(result.toString().trim());
        }
    }

}
