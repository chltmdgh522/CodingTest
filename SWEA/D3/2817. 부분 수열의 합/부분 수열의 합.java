import java.io.*;
import java.util.*;

public class Solution {

    static int N;

    static int K;

    static int[] num;

    static int result;

    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test < T + 1; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            result = 0;
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            num = new int[N];
            for (int i = 0; i < N; i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }
            sum = 0;
            solution(0);
            System.out.println("#" + test + " " + result);
        }
    }

    static void solution(int level) {
        if (sum == K) {
            result++;
            return;
        }

        if (level == N) {
            return;
        }


        for (int i = level; i < N; i++) {
            sum += num[i];
            if (sum > K) {
                sum -= num[i];
                continue;
            }
            solution(i + 1);
            sum -= num[i];
        }
    }
}