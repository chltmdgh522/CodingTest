
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Jewel {
        int weight;

        long value;

        public Jewel(int weight, long value) {
            this.weight = weight;
            this.value = value;
        }
    }

    static int N, K;

    static Jewel[] jewels;

    static long[] bag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewels = new Jewel[N];
        bag = new long[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }


        for (int i = 0; i < K; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        // 오름차순
        Arrays.sort(bag);
        Arrays.sort(jewels, (a, b) -> a.weight - b.weight);


        long result = simulation();
        System.out.println(result);
    }

    private static long simulation() {
        long result = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        int idx = 0;
        for (int i = 0; i < K; i++) {
            long capacity = bag[i];


            while (idx < N && jewels[idx].weight <= capacity) {
                pq.add(jewels[idx].value);
                idx++;
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }
        return result;
    }
}
