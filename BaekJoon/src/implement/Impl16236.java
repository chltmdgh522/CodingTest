package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Impl16236 {
    static int N;

    static int[][] map;

    static boolean[][] visit;

    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    // 아기 상어 크기
    static int shaker=2;

    static int shakerEat=0;

    static int shakerX;

    static int shakerY;



    static int shakerCount;

    static class Fish implements Comparable<Fish> {

        int x, y, dist;

        public Fish(int x, int y, int dist){
            this.x=x;
            this.y= y;
            this.dist =dist;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] ==9){
                    map[i][j] = 0;
                    shakerX = i;
                    shakerY = j;

                }
            }
        }

        shakerCount=0;
        slove();
        System.out.println(shakerCount);
    }

    private static void slove() {

        while(true){
            Fish fish = bfs();
            if(fish ==null) break;

            shakerX = fish.x;
            shakerY = fish.y;
            map[shakerX][shakerY]=0;
            shakerCount+=fish.dist;
            shakerEat+=1;

            if(shakerEat == shaker){
                shakerEat=0;
                shaker+=1;
            }

        }
    }

    private static Fish bfs(){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[]{shakerX, shakerY, 0});
        visit = new boolean[N][N];
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        visit[shakerX][shakerY]=true;
        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int sX = poll[0];
            int sY = poll[1];
            int dist = poll[2];

            for(int dir = 0; dir<4; dir++){
                int nX = sX + dx[dir];
                int nY = sY + dy[dir];

                if(nX <0 || nX >=N || nY <0 || nY >=N) continue;
                if(visit[nX][nY]) continue;
                if(map[nX][nY] > shaker) continue;

                if(map[nX][nY] <shaker && map[nX][nY] >0){
                    pq.add(new Fish(nX, nY, dist+1));
                }
                visit[nX][nY] =true;
                queue.add(new int[]{nX, nY, dist+1});
            }
        }
        if (pq.isEmpty()) return null;
        return pq.poll();
    }
}
