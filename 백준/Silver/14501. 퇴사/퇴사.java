import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    public static int solution() {
        return getMaxProfit(0);
    }

    public static int getMaxProfit(int day) {
        if (day >= n)
            return 0;

        // 현재 상담을 선택하지 않는 경우
        int profit1 = getMaxProfit(day + 1);

        // 현재 상담을 선택하는 경우
        int profit2 = 0;
        if (day + arr[day][0] <= n) {
            profit2 = arr[day][1] + getMaxProfit(day + arr[day][0]);
        }

        return Math.max(profit1, profit2);
    }
}