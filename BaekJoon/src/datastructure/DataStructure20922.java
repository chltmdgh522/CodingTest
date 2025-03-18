package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DataStructure20922 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] visit = new int[100001];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int answer = 0;

        while (left < N && right < N) {
            if (visit[arr[right]] < K) {
                visit[arr[right]]++;
                right++;
                answer=Math.max(answer, right-left);
            } else {
                visit[arr[left]]--;
                left++;
            }
        }
        System.out.println(answer);

    }
}
