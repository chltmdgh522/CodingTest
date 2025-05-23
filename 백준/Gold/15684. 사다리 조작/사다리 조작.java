
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static int M;

    static int H;

    static int[][] map;

    static int result;

    static boolean finish = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 고정 사다리 1,2로 고정
            map[a][b] = 1; // 우측
            map[a][b + 1] = 2; // 좌측
        }

        for (int i = 0; i <= 3; i++) {
            solution(1, 1, 0,i);
            if (finish) {
                result = i;
                break;
            }
        }
        if(!finish){
            result = -1;
        }

        System.out.println(result);

    }

    private static void solution(int x, int y, int ladder,int maxLadder) {

        if (ladder == maxLadder) {
            if (mapCheck()) {
                finish = true;
            }
            return;
        }
        for (int i = x; i <= H; i++) {
            for (int j = y; j < N; j++) {
                if (map[i][j] == 0 && map[i][j + 1] == 0 ) {
                    map[i][j] = 1;
                    map[i][j + 1] = 2;
                    solution(1,1, ladder + 1, maxLadder);
                    map[i][j] = 0;
                    map[i][j + 1] = 0;
                }
            }
        }
    }

    private static boolean mapCheck() {
        boolean finish = false;
        for (int i = 1; i <= N; i++) {
            int nx = 0;
            int ny = i;

            for(int j=1; j<=H; j++){
                nx+=1;
                if (map[nx][ny] == 1) {
                    ny +=1;
                }else if(map[nx][ny] ==2){
                    ny -=1;
                }
            }


            if(i == ny){
                finish = true;

            }else{
                finish = false;
                break;
            }
        }

        return finish;
    }
}
