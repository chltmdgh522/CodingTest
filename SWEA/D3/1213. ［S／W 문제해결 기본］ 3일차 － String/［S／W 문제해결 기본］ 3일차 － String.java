
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int test = 1; test <= 10; test++) {
            int T = Integer.parseInt(br.readLine());
            int result = 0;
            String key = br.readLine();
            String input = br.readLine();
            int cnt = 1;
            boolean flag = false;
            for (int i = 0; i < input.length() - key.length() + 1; i++) {
                if (flag) {
                    cnt++;
                    if (cnt == key.length()) {
                        cnt = 1;
                        flag = false;
                    }
                    continue;
                }
                if (input.charAt(i) == key.charAt(0)) {
                    if (input.substring(i, i+key.length()).equals(key)) {
                        result++;
                        flag = true;
                    }
                }
            }

            System.out.println("#" + T + " " + result);
        }
    }


}
