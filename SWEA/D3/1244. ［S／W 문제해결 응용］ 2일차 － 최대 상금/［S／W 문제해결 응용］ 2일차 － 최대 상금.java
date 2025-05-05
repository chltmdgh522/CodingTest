import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static char[] num;
    static String result;
    static int length;

    static Set<String>[] visited; // 깊이에 따른 방문 상태 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            N = Integer.parseInt(st.nextToken());

            num = input.toCharArray();
            length = num.length;
            result = "0";

            visited = new HashSet[N + 1]; // 깊이마다 Set 초기화
            for (int i = 0; i <= N; i++) {
                visited[i] = new HashSet<>();
            }

            solution(0);

            System.out.println("#" + test + " " + result);
        }
    }

    private static void solution(int count) {
        String current = new String(num);
        if (visited[count].contains(current)) return;
        visited[count].add(current);

        if (count == N) {
            if (current.compareTo(result) > 0) {
                result = current;
            }
            return;
        }

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                swap(i, j);
                solution(count + 1);
                swap(i, j); // backtracking
            }
        }
    }

    private static void swap(int i, int j) {
        char tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
}
