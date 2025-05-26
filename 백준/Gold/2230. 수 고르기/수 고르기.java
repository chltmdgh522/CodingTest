
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> num = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            num.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(num);

        int start = 0;
        int end = 1;

        int result = Integer.MAX_VALUE;
        while (end < N) {
            int sum = num.get(end) - num.get(start);

            if (M <= sum) {
                result = Math.min(result, sum);

            }
            
            if(result == M){
                break;
            }

            if (sum >= M) {
                start++;
            } else {
                end++;
            }
        }

        System.out.println(result);

    }
}
