package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Back9663 {
    static int[] board;

    static int result;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        result = 0;
        board = new int[N];
        dfs(0);
        System.out.println(result);

    }

    private static void dfs(int queen) {
        if (queen == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[queen] = i;
            if (solution(queen)) {
                dfs(queen + 1);
            }
        }
    }

    private static boolean solution(int col) {
        for (int i = 0; i < col; i++) {
            // 직선
            if (board[col] == board[i]) {
                return false;
            }
            // 대각선
            else if (Math.abs(col - i) == Math.abs(board[col] - board[i])) {
                return false;
            }
        }
        return true;
    }
}
