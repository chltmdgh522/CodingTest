
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> config;

    static int K;

    static int answer;

    static boolean[] visit;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        config = new ArrayList<>();

        // 0번 무시 해도됨 그냥 편하게 할려고
        config.add(new ArrayList<>());


        for (int i = 1; i < 5; i++) {
            config.add(new ArrayList<>());
            String input = br.readLine();
            List<Integer> setting = config.get(i);
            for (int j = 0; j < input.length(); j++) {
                setting.add(input.charAt(j) - '0');
            }
        }

        K = Integer.parseInt(br.readLine());
        answer = 0;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            visit = new boolean[5]; // 0 제외
            solution(line, dir);
        }

        int score = 1;
        for (int cnt = 1; cnt < 5; cnt++) {
            List<Integer> setting = config.get(cnt);
            if (setting.get(0) == 1) {
                answer += score;
            }
            score *= 2;
        }
        System.out.println(answer);

    }

    static void solution(int line, int dir) {
        List<Integer> lineSetting = config.get(line);

        // 다른 시계방향과 극이 맞는지 봐야됨
        // 1이 S극, 0이 N극
        if (line == 1) {
            back(line, dir, lineSetting);

        } else if (line == 4) {
            front(line, dir, lineSetting);

        } else {
            front(line, dir, lineSetting);
            back(line, dir, lineSetting);

        }


        // 시계(1) 방향이면 뒷 요소 제거해서 앞으로 오기
        // 반 시계(-1) 방향이면 앞요소 제거해서 뒤로 배치

        if (dir == 1) {
            int last = lineSetting.remove(7);
            lineSetting.add(0, last);
        } else if (dir == -1) {
            int first = lineSetting.remove(0);
            lineSetting.add(first);
        }

    }

    private static void front(int line, int dir, List<Integer> lineSetting) {
        int compareA = lineSetting.get(6);
        int compareB = config.get(line - 1).get(2);
        visit[line] = true;
        if (compareA != compareB && !visit[line - 1]) {
            int nextDir;
            if (dir == 1) {
                nextDir = -1;
            } else {
                nextDir = 1;
            }
            solution(line - 1, nextDir);
        }
    }

    private static void back(int line, int dir, List<Integer> lineSetting) {
        int compareA = lineSetting.get(2);
        int compareB = config.get(line + 1).get(6);
        visit[line] = true;
        if (compareA != compareB && !visit[line + 1]) {
            int nextDir;
            if (dir == 1) {
                nextDir = -1;
            } else {
                nextDir = 1;
            }
            solution(line + 1, nextDir);
        }
    }
}
