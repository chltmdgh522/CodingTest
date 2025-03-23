package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DP15666 {
    static int N;
    static int M;

    static List<String> num;
    static List<Integer> inputNum;
    static List<String> list = new ArrayList<>();
    static StringBuilder sb;

    static Set<String> answer = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inputNum = new ArrayList<>();
        num= new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            inputNum.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(inputNum);
        for (Integer i : inputNum) {
            num.add(String.valueOf(i));
        }
        dfs(0);

        for (String s : answer) {
            System.out.println(s);
        }
    }

    public static void dfs(int level) {
        if (level == M) {
            sb=new StringBuilder();
            for (String s : list) {
                sb.append(s).append(" ");
            }
            answer.add(sb.toString().trim());

            return;
        }

        for (int i = 0; i < N; i++) {
            if (list.size() == 0 || Integer.parseInt(list.get(list.size() - 1)) <= Integer.parseInt(num.get(i))) {
                list.add(num.get(i));
                dfs(level + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
