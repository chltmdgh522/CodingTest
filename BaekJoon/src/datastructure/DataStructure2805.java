package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class DataStructure2805 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        int[] tree = new int[N];
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            if (tree[i] > max) {
                max = tree[i];
            }
        }
        int answer = 0;
        int left = 0;
        int right = max;


        while (left <= right) {
            int mid = (left + right) / 2;
            long total = 0;
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] > mid) {
                    total += tree[i] - mid;
                }
            }

            if (total >= M) {
                left = mid + 1;
                answer = mid;
            } else if (total < M) {
                right = mid - 1;
            }

        }

        System.out.println(answer);
    }
}
