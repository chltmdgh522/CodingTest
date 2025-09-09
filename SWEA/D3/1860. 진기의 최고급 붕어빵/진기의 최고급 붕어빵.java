import java.io.*;
import java.util.*;

public class Solution {


    static int N;

    static int M;

    static int K;

    static List<Integer> time;

    static int timeM;

    static int attack;
    static boolean result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            time = new ArrayList<>();
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                time.add(Integer.parseInt(st.nextToken()));
            }

            Collections.sort(time);


            timeM = M;
            attack = 0;
            result = true;


            solution();
            if (result) {
                System.out.println("#" + test + " " + "Possible");
            } else {
                System.out.println("#" + test + " " + "Impossible");
            }

        }
    }

    private static void solution() {
        int maxTime = time.get(time.size() - 1) + 1;
        int[] fish = new int[maxTime];
        int fishCnt = 0;

        if (time.get(0) < M) {
            result = false;
            return;
        }

        // 붕어빵 초기화
        for (int i = 1; i < maxTime; i++) {
            if (i == timeM) {
                fishCnt += K;
                timeM += M;
            }
            fish[i] = fishCnt;
        }

        for (Integer people : time) {
            if (fish[people] - attack != 0) {
       
                attack++;
            } else {
                result = false;
                return;
            }
        }
    }


}
