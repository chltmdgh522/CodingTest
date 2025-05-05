import java.io.*;
import java.util.*;

public class Solution {

    static int N;

    static char[] num;

    static String result;

    static int length;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test < T + 1; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            N = Integer.parseInt(st.nextToken());
            length = input.length();

            num = input.toCharArray();
            result = "";
            solution(0,0);
            System.out.println("#" + test + " " + result);
        }
    }

    private static void solution(int depth, int count) {
        if (count == N) {
            String current = new String(num);
            if(current.compareTo(result) > 0){
                result = current;
            }
            return;
        }

        for (int i = depth; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                swap(i, j);
                solution(i,count+1);
                swap(i, j);
            }
        }
    }

    private static void swap(int front, int back) {
        char temp = 0;
        temp = num[front];
        num[front] = num[back];
        num[back] = temp;
    }
}