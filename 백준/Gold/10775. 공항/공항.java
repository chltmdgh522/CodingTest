import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
    static int G, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        int result = 0;
        TreeSet<Integer> parks = new TreeSet<>();

        for (int i = 1; i <= G; i++) {
            parks.add(i);
        }

        for (int i = 0; i < P; i++) {
            int park = Integer.parseInt(br.readLine());
            Integer air = parks.floor(park);
            if (air == null) {
                break;
            }

            parks.remove(air);
            result++;
        }

        System.out.println(result);
    }
}
