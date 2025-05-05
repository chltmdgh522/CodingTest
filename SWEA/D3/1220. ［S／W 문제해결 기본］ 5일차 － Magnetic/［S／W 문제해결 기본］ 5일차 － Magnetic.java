import java.io.*;
import java.util.*;

public class Solution {

    static int [][] map;
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        for(int test=1; test<11; test++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st;
            map = new int[100][100];
            // 입력
            for(int i=0; i<100; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<100; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            result = 0;
            solution();

            System.out.println("#"+test+" "+result);
        }

    }

    private static void solution(){

        // N극 기준
        for(int col =0; col<100; col++){
            boolean flag = false;
            int prepare =0;
            for(int row=99; row>=0; row--){

                if(map[row][col] == 2){
                    flag = true;
                    prepare = 2;
                }
                if(prepare == 1){
                    continue;
                }

                if(map[row][col] == 1 && flag){
                    result++;
                    prepare =1;
                }


            }
        }
    }
}