package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Greedy14916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int five = N / 5;
        int result = 0;
        for (int i = five; i >= 0; i--) {
            int remain = N - i * 5;
            result = i;
            if (remain % 2 == 0) {
                result += remain / 2;
                break;
            }
        }
        System.out.println(result);

    }
}
