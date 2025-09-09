package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Impl12100 {
    static int answer = 0;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(0, board);
        System.out.println(answer);
    }

    private static void solution(int depth, int[][] board) {
        if (depth == 5) {
            answer = Math.max(answer, getMax(board));
            return;
        }


        for (int dir = 0; dir < 4; dir++) {
            int[][] move = move(dir, board);
            solution(depth+1, move);
        }
    }

    private static int getMax(int[][] board) {
        int max = 0;
        for (int[] row : board) {
            for (int val : row) {
                max = Math.max(max, val);
            }
        }
        return max;
    }

    private static int[][] move(int dir, int[][] board) {
        int [][] newBoard = new int[N][N];

        for(int i=0; i<N; i++){
            int []temp =new int[N];
            int idx = 0;
            boolean merge=false;
            for(int j=0; j<N; j++){
                int x,y;
                if(dir ==0){
                    x=j;
                    y=i;
                }else if(dir ==1){
                    x=i;
                    y=j;
                }else if(dir ==2){
                    x= N-1-j;
                    y= i;
                }else{
                    x=i;
                    y= N-1-j;
                }
                int value = board[x][y];
                if(value ==0){
                    continue;
                }

                if(idx !=0 && temp[idx-1] == value &&!merge){
                    temp[idx-1] *=2;
                    merge=true;
                }else{
                    temp[idx] = value;
                    idx++;
                    merge=false;
                }
            }

            for(int j=0; j<N; j++){
                if(dir==0){
                    newBoard[j][i] = temp[j];
                }else if(dir ==1){
                    newBoard[i][j] = temp[j];
                }else if(dir ==2){
                    newBoard[N-1-j][i] =temp[j];
                }else{
                    newBoard[i][N-1-j] =temp[j];
                }
            }

        }

        return newBoard;

    }
}
