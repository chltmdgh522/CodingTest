package test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class TestMain {

    static class Node implements Comparable<Node>{

        int cost, city;

        public Node (int city, int cost){
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M;

    static List<List<Node>> graph;

    static int [] dist;

    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i<N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);

    }

    static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N+1];
        Arrays.fill(dist, INF);

        pq.add(new Node(start , 0));
        dist[start] =0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int curCity = now.city;
            int curCost = now.cost;

            if(curCost > dist[curCity]) continue;


            for (Node node : graph.get(curCity)) {
                int nextCity = node.city;
                int nextCost = node.cost + curCost;

                if(nextCost < dist[nextCity]){
                    dist[nextCity] = nextCost;
                    pq.add(new Node(nextCity, nextCost));
                }
            }

        }
        return dist[end];
    }
}
