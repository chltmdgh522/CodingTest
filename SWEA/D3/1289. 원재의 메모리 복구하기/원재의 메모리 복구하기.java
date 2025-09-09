import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test = 1; test <= T; test++) {
            String bit = br.readLine();
            int start = 0;
            int end = bit.length();
            int count =0;
            // 초기화
            StringBuilder init = new StringBuilder();
            String repeated = new String(new char[end - start]).replace('\0', '0');
            init.replace(start, end, repeated);



            for (int i = 0; i < bit.length(); i++) {
                start = i;
                if (bit.charAt(i) != init.charAt(i)) {
                    char temp = bit.charAt(i);
                    String repeatedT= new String(new char[end-start]).replace('\0', temp);
                    init.replace(start,end, repeatedT);
                    count++;
                }

                if(bit.contentEquals(init)){
                    System.out.println("#"+test+" "+count);
                    break;
                }
            }


        }
    }
}
