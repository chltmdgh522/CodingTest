import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int cost;

        Edge(int f, int t, int c) {
            this.from = f;
            this.to = t;
            this.cost = c;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]); // 경로 압축
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) return false; // 이미 같은 집합 → 사이클
            parent[rootB] = rootA; // 합치기
            return true;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Edge> edgeList = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(from, to, cost));
        }

        Collections.sort(edgeList);

        int result = simulation(edgeList);
        System.out.println(result);
    }

    private static int simulation(List<Edge> edgeList) {
        UnionFind unionFind = new UnionFind(N);
        int result = 0;
        int maxResult = 0;
        for (Edge edge : edgeList) {

            if (unionFind.union(edge.from, edge.to)) {
                result += edge.cost;
                maxResult = edge.cost;
            }
        }


        result -= maxResult;


        return result;
    }
}
