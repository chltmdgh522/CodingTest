import java.io.*;
import java.util.*;

public class Solution {

    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int test=1; test<=10; test++){
            int T = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            list = new ArrayList<>();
            for(int i=0; i<8; i++){
                list.add(Integer.parseInt(st.nextToken()));
            }

            solution();
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(T).append(" ");
            for (Integer i : list) {
                sb.append(i).append(" ");
            }

            System.out.println(sb.toString().trim());
        }
    }


    private static void solution(){
        int back=1;

        while(back >0){
            int cnt = 1;
            for(int i=0; i<5; i++){
                back = list.remove(0);

                back -=cnt;
                if(back<= 0){
                    list.add(0);
                    break;
                }
                list.add(back);
                cnt ++;
            }
        }
    }
}
