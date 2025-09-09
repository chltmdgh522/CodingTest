import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
 public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = -1;

        // 5원 개수를 최대로 사용하면서 줄여가며 확인
        for (int five = N / 5; five >= 0; five--) {
            int remain = N - five * 5;

            if (remain % 2 == 0) { // 2원으로 나눠 떨어질 때
                result = five + (remain / 2);
                break;
            }
        }

        System.out.println(result);
    }
}
