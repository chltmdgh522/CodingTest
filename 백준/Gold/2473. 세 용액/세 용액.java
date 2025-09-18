
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] liquid = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquid);

        long[] result = new long[3];
        long min = Long.MAX_VALUE;

        for (int i = 0; i < N - 2; i++) {
            int core = i;
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = liquid[core] + liquid[left] + liquid[right];
                long abs = Math.abs(sum);
                if (min > abs) {
                    min = abs;
                    result[0] = liquid[core];
                    result[1] = liquid[left];
                    result[2] = liquid[right];
                }


                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        System.out.println(result[0] + " " + result[1] + " " + result[2]);


    }
}
