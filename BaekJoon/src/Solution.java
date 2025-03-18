
import java.io.*;
import java.util.*;

public class Solution {

    static class Node implements Comparable<Node> {
        int city;

        int cost;

        public Node(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N;

    static int M;

    static int[] dist;
    static List<List<Node>> graph = new ArrayList<>();
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, cost));

        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dijkstra(start, end);

    }

    static void dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int nowCity = node.city;
            int nowCost = node.cost;

            if (nowCost > dist[nowCity]) continue;

            for (Node nextNode : graph.get(nowCity)) {
                int nextCity = nextNode.city;
                int nextCost = nextNode.cost + nowCost;

                if (nextCost < dist[nextCity]) {
                    dist[nextCity] = nextCost;
                    pq.add(new Node(nextCity, nextCost));
                }

            }


        }
    }
}
