package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Impl2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] liquid = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        int[] result = new int[2];
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = N - 1;
        while (left < right) {
            int sum = Math.abs(liquid[left] + liquid[right]);
            if (min >= sum) {
                min = sum;
                result[0] = liquid[left];
                result[1] = liquid[right];
            }


            if (liquid[left] + liquid[right] >= 0) {
                right--;
            } else {
                left++;
            }
        }


        System.out.print(result[0] + " " + result[1]);
    }
}
