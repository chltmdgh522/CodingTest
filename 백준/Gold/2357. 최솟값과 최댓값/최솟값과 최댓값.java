
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long[] arr;
    static long[] minTree;
    static long[] maxTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        int treeSize = 1;
        while (treeSize < N) treeSize *= 2;
        treeSize *= 2;

        minTree = new long[treeSize];
        maxTree = new long[treeSize];

        buildMinTree(1, 0, N - 1);
        buildMaxTree(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            long minVal = queryMin(1, 0, N - 1, left, right);
            long maxVal = queryMax(1, 0, N - 1, left, right);

            sb.append(minVal).append(" ").append(maxVal).append("\n");
        }

        System.out.print(sb);
    }

    static void buildMinTree(int node, int start, int end) {
        if (start == end) {
            minTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            buildMinTree(node * 2, start, mid);
            buildMinTree(node * 2 + 1, mid + 1, end);
            minTree[node] = Math.min(minTree[node * 2], minTree[node * 2 + 1]);
        }
    }

    static void buildMaxTree(int node, int start, int end) {
        if (start == end) {
            maxTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            buildMaxTree(node * 2, start, mid);
            buildMaxTree(node * 2 + 1, mid + 1, end);
            maxTree[node] = Math.max(maxTree[node * 2], maxTree[node * 2 + 1]);
        }
    }

    static long queryMin(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Long.MAX_VALUE;
        if (left <= start && end <= right) return minTree[node];
        int mid = (start + end) / 2;
        return Math.min(queryMin(node * 2, start, mid, left, right),
                        queryMin(node * 2 + 1, mid + 1, end, left, right));
    }

    static long queryMax(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Long.MIN_VALUE;
        if (left <= start && end <= right) return maxTree[node];
        int mid = (start + end) / 2;
        return Math.max(queryMax(node * 2, start, mid, left, right),
                        queryMax(node * 2 + 1, mid + 1, end, left, right));
    }
}
