package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Impl14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer> block = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            block.add(Integer.parseInt(st.nextToken()));
        }

        int rainWater = getRainWater(M, block);

        System.out.println(rainWater);
    }

    private static int getRainWater(int M, List<Integer> block) {
        int current = 0;
        int leftMax = 0;
        int rightMax = 0;
        int rainWater =0;
        // 처음과 끝은 빗물이 고일수가 없음
        for (int i = 1; i < M - 1; i++) {
            current = block.get(i);
            leftMax = block.get(i);
            rightMax = block.get(i);

            for (int j = i - 1; j >= 0; j--) {
                if (block.get(j) > current) {
                    leftMax = Math.max(leftMax, block.get(j));
                }
            }

            for (int k = i + 1; k < M; k++) {
                if (block.get(k) > current) {
                    rightMax = Math.max(rightMax, block.get(k));
                }
            }

            if(Math.min(rightMax, leftMax) > current){
                rainWater +=(Math.min(rightMax,leftMax)-current);
            }
        }
        return rainWater;
    }
}
