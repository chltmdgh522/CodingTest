package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dp15486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 2];

        StringTokenizer st;
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        for (int i = 1; i < N + 1; i++) {
            max = Math.max(dp[i], max); // 자기보다 앞에 큰값이 있을 경우
            // 이거는 예를 들어서 1에서 4로 갈때 4가 10이고 2에서 3으로 갈떄 3이 20이라고 가정했을떄
            // 1에서 4로 가는것보다 2에서 3으로가고 그다음 4로가는게 이득이다. 즉 건너띄어서 갈 수 있다.
            dp[i] = max;

            if (T[i] + i > N + 1) continue;

            // 1일치면 그 1일치 이전 까지 즉 다시 말해서 4일치면 4일이전 즉 4일의 P[] 를 포함하는게 아님!
            if (P[i] + dp[i] > dp[i + T[i]]) { // 자기보다 뒤에 큰값이 없을 경우
                dp[i + T[i]] = P[i] + dp[i];
            }
        }

        int result =0;
        for(int i=1; i<=N+1; i++){
            result=Math.max(dp[i], result);
        }
        System.out.println(result);
    }
}
