import java.io.*;
import java.util.*;

public class Solution {

    static int[] board;

    static int result;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test < T + 1; test++) {
            N = Integer.parseInt(br.readLine());
            result = 0;
            board = new int[N];
            dfs(0);
            System.out.println("#"+test+" "+result);

        }
    }

    private static void dfs(int queen) {
        if (queen == N) {
            result++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[queen] = i;
            if(solution(queen)){
                dfs(queen+1);
            }
        }
    }

    private static boolean solution(int col) {
        for(int i=0; i<col; i++){
            // 직선
            if(board[col] == board[i]){
               return false;
            }
            // 대각선
            else if(Math.abs(col-i) == Math.abs(board[col]- board[i])){
               return false;
            }
        }
        return true;
    }


}
