
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N; // 전체 층

    static int K; // 총 자릿수 LED

    static int P; // 반전시킬 개수

    static int X; // 멈춰있는 층

    static boolean[][] LED;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        init();
        int[] original_digit = digitArray(X);
        for (int compare_num = 1; compare_num <= N; compare_num++) {
            if(compare_num == X) continue;
            if (simulation(compare_num, original_digit)) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static boolean simulation(int compare_num, int[] original_digit) {

        int[] compare_digit = digitArray(compare_num);
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 7; j++) {
                if (LED[original_digit[i]][j] != LED[compare_digit[i]][j]) {
                    cnt++;
                }
                if (cnt > P) return false;
            }
        }
        return true;

    }


    private static int[] digitArray(int mock) {
        int[] result = new int[K];
        for (int i = K - 1; i >= 0; i--) {
            result[i] = mock % 10;
            mock = mock / 10;
        }
        return result;
    }

    private static void init() {
        LED = new boolean[10][7];
        // 0번
        LED[0][0] = true;
        LED[0][2] = true;
        LED[0][3] = true;
        LED[0][4] = true;
        LED[0][5] = true;
        LED[0][6] = true;

        // 1번
        LED[1][4] = true;
        LED[1][6] = true;

        // 2번
        LED[2][0] = true;
        LED[2][1] = true;
        LED[2][2] = true;
        LED[2][4] = true;
        LED[2][5] = true;

        // 3번
        LED[3][0] = true;
        LED[3][1] = true;
        LED[3][2] = true;
        LED[3][4] = true;
        LED[3][6] = true;

        // 4번
        LED[4][1] = true;
        LED[4][3] = true;
        LED[4][4] = true;
        LED[4][6] = true;

        // 5번
        LED[5][0] = true;
        LED[5][1] = true;
        LED[5][2] = true;
        LED[5][3] = true;
        LED[5][6] = true;

        // 6번
        LED[6][0] = true;
        LED[6][1] = true;
        LED[6][2] = true;
        LED[6][3] = true;
        LED[6][5] = true;
        LED[6][6] = true;

        // 7번
        LED[7][0] = true;
        LED[7][4] = true;
        LED[7][6] = true;

        // 8번
        LED[8][0] = true;
        LED[8][1] = true;
        LED[8][2] = true;
        LED[8][3] = true;
        LED[8][4] = true;
        LED[8][5] = true;
        LED[8][6] = true;

        // 9번
        LED[9][0] = true;
        LED[9][1] = true;
        LED[9][2] = true;
        LED[9][3] = true;
        LED[9][4] = true;
        LED[9][6] = true;


    }
}
