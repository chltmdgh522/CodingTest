import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static int[] candy;
    
    // Find (경로 압축)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // Union
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 아이 수
        int M = Integer.parseInt(st.nextToken()); // 친구 관계 수
        int K = Integer.parseInt(st.nextToken()); // 최대 괴롭힐 수 있는 아이 수 (제한)
        
        candy = new int[N + 1];
        parent = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candy[i] = Integer.parseInt(st.nextToken());
            parent[i] = i; // 부모 초기화
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        
        // 그룹별 {인원 수, 사탕 합}
        Map<Integer, int[]> group = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int root = find(i);
            group.putIfAbsent(root, new int[]{0, 0});
            group.get(root)[0]++;         // 인원 수
            group.get(root)[1] += candy[i]; // 사탕 합
        }
        
        // DP (0-1 배낭)
        int[] dp = new int[K]; 
        for (int[] g : group.values()) {
            int people = g[0];
            int candies = g[1];
            
            // 뒤에서부터 순회 (중복 선택 방지)
            for (int j = K - 1; j >= people; j--) {
                dp[j] = Math.max(dp[j], dp[j - people] + candies);
            }
        }
        
        int ans = 0;
        for (int j = 0; j < K; j++) {
            ans = Math.max(ans, dp[j]);
        }
        
        System.out.println(ans);
    }
}
