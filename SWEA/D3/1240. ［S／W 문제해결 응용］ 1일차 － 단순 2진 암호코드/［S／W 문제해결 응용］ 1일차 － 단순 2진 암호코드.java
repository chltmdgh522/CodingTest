import java.io.*;
import java.util.*;

public class Solution {
    static boolean flag;
    static int result;
    static String[] digit = new String[]
            {"0001101", "0011001", "0010011", "0111101", "0100011",
                    "0110001", "0101111", "0111011", "0110111", "0001011"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] digit;
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int M = Integer.parseInt(st.nextToken());
flag =false;
            for (int i = 0; i < N; i++) {
                String input = br.readLine();
                if (input.contains("1")) {
                    solution(input);
                    flag = true;
                }
            }
            System.out.println("#" + test + " " + result);
        }

    }

    private static void solution(String input) {
        if (flag) {
            return;
        }
        result = 0;
        int endPlace = 0;
        int startPlace = 0;
        StringBuilder decode = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == '1') {
                endPlace = i;
                startPlace = endPlace - 55;
                break;
            }
        }

        String key = input.substring(startPlace, endPlace + 1);
        List<String> keyList = new ArrayList<>();
        for (int i = 0; i < 56; i += 7) {
            keyList.add(key.substring(i, i + 7));
        }

        for (String s : keyList) {
            for (int i = 0; i <= 9; i++) {
                if (s.equals(digit[i])) {
                    decode.append(i);
                    break;
                }
            }
        }

        // 짝수
        int even = 0;
        // 홀수 *3
        int odd = 0;
        for (int i = 1; i < decode.length() + 1; i++) {

            if (i % 2 == 0) {
                even += decode.charAt(i - 1) - '0';
            } else {
                odd += decode.charAt(i - 1) - '0';
            }
        }

        int sum = odd * 3 + even;
        if (sum % 10 == 0) {
            result = odd + even;
        } else {
            result = 0;
        }


    }
}


