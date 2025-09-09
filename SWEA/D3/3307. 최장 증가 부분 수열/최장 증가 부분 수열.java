
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;

    static int[] num;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            N = Integer.parseInt(br.readLine());
            num = new int[N];
            st =new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++){
                num[i] = Integer.parseInt(st.nextToken());
            }
            result=1;
            solution();
            System.out.println("#"+test+" "+result);


        }
    }

    private static void solution() {
        int [] dp =new int[N];
        dp[0]=1;


        for(int i=1; i<N; i++){
            dp[i]=1;

            for(int j=0; j<i; j++){
                if(num[j] < num[i] && dp[i] <=dp[j]){
                    dp[i] =dp[j] +1;
                }
            }

            result = Math.max(result, dp[i]);
        }
    }


}
