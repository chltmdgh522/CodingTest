import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i < 11; i++) {
            int N = Integer.parseInt(br.readLine());

            int[] building = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                building[j] = Integer.parseInt(st.nextToken());
            }


            int result = solution(building, N);

            System.out.print("#" + i + " " + result);
            System.out.println();
        }
    }

    static int solution(int[] building, int N) {
        int left = 0;
        int right = 4;
        int result = 0;

        for (int i = 2; i < N - 2; i++) {
            boolean flag = true;
            if (building[i] == 0) {
                left += 1;
                right += 1;
                continue;
            }

            int current = building[i];
            // 앞
            int frontMax = 0;
            for (int j = left; j < i; j++) {
                if (current > building[j]) {
                    frontMax = Math.max(building[j], frontMax);
                } else {
                    flag = false;
                    break;
                }
            }

            int backMax = 0;
            // 뒤
            for (int j = i + 1; j <= right; j++) {
                if (current > building[j]) {
                    backMax = Math.max(building[j], backMax);
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                int max = Math.max(backMax, frontMax);
                int temp = current - max;
                result += temp;
            }

            left += 1;
            right += 1;
        }

        return result;
    }
}
