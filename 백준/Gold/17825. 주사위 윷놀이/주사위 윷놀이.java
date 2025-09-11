
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] map = {
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0, // 5 10 15 // 20 ~21
            10, 13, 16, 19, 25, 30, 35, 40, 0, //22 //26 ~ 30
            20, 22, 24, 25, 30, 35, 40, 0, // 31 // 34 ~ 38
            30, 28, 27, 26, 25, 30, 35, 40, 0 // 39 // 43 ~ 47
    };

    static boolean[] visit;

    static int[] dice;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dice = new int[10];
        int[] player = new int[10];
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        selectPlayer(0, player);
        System.out.println(result);

    }

    private static void selectPlayer(int cnt, int[] player) {
        if (cnt == 10) {
            int gameResult = gamePlay(player);
            result = Math.max(gameResult, result);
            return;
        }

        for (int horse = 1; horse <= 4; horse++) {
            player[cnt] = horse;
            selectPlayer(cnt + 1, player);
        }
    }

    private static int gamePlay(int[] player) {
        int[] storeHorse = new int[5]; // 0은 제외
        visit = new boolean[48];
        int result = 0;
        for (int cnt = 0; cnt < player.length; cnt++) {


            int startPos = storeHorse[player[cnt]];

            if (isFinish(startPos)) { // 이미 도착한 말들은 건너뛰어야함
                continue;
            }

            int nextPos = startPos + dice[cnt]; // 다음 이동

            if (isNextFinish(startPos, nextPos, storeHorse, player[cnt])) { // 도착했는지 검증
                clearVisit(startPos);
                continue;
            }



            // 다음 이동 칸에 다른 말이 없으면 이동 및 점수 얻기
            if (!visit[nextPos]) {
                // 이전 자리 true 해제
                clearVisit(startPos);

                // 점수 더하고 자리 차지하기
                result += map[nextPos];
                visit[nextPos] = true;
                storeHorse[player[cnt]] = nextPos;

                // 정지한 칸이 파란색일 경우
                bluePosition(storeHorse, nextPos, player[cnt]);

                // 정지한 칸이 혼잡도 구간일 경우
                congestionPosition(nextPos);
            }else{
                return 0;
            }

        }


        return result;
    }

    private static void congestionPosition(int nextPos){
        if(nextPos == 26 || nextPos == 34 || nextPos == 43){
            visit[26] = true;
            visit[34] = true;
            visit[43] = true;
        }else if(nextPos == 27 || nextPos == 35 || nextPos == 44){
            visit[27] = true;
            visit[35] = true;
            visit[44] = true;
        }else if(nextPos == 28 || nextPos == 36 || nextPos == 45){
            visit[28] = true;
            visit[36] = true;
            visit[45] = true;
        }else if(nextPos == 20 ||nextPos == 29 || nextPos == 37 || nextPos == 46){
            visit[20] = true;
            visit[29] = true;
            visit[37] = true;
            visit[46] = true;
        }

    }

    private static boolean isFinish(int start) {
        return start == 21 || start == 30 || start == 38 || start == 47;
    }


    private static boolean isNextFinish(int start, int nextPos, int[] storeHorse, int horse) {
        if (start >= 0 && start <= 21) {
            if (nextPos >= 21) {
                storeHorse[horse] = 21;
                clearVisit(start);
                return true;
            }
        } else if (start >= 22 && start <= 30) {
            if (nextPos >= 30) {
                clearVisit(start);
                storeHorse[horse] = 30;
                return true;
            }
        } else if (start >=31 && start <= 38) {
            if (nextPos >= 38) {
                clearVisit(start);
                storeHorse[horse] = 38;
                return true;
            }
        } else if (start >= 39 && start <= 47) {
            if (nextPos >= 47) {
                clearVisit(start);
                storeHorse[horse] = 47;
                return true;
            }
        }

        return false;
    }

    private static void bluePosition(int[] storeHorse, int nextPos, int horse) {
        if (nextPos == 5) {
            storeHorse[horse] = 22;
            visit[22] = true;
        } else if (nextPos == 10) {
            storeHorse[horse] = 31;
            visit[31] = true;
        } else if (nextPos == 15) {
            storeHorse[horse] = 39;
            visit[39] = true;
        }
    }

    private static void clearVisit(int start) {
        if (start == 0) {
            return;
        }
        // 혼잡도 구간
        if(start == 26 || start == 34 || start == 43){
            visit[26] = false;
            visit[34] = false;
            visit[43] = false;
        }else if(start == 27 || start == 35 || start == 44){
            visit[27] = false;
            visit[35] = false;
            visit[44] = false;
        }else if(start == 28 || start == 36 || start == 45){
            visit[28] = false;
            visit[36] = false;
            visit[45] = false;
        }else if(start== 20 || start == 29 || start == 37 || start == 46){
            visit[20] = false;
            visit[29] = false;
            visit[37] = false;
            visit[46] = false;
        }



        // blue 중복
        if (start == 22) {
            visit[5] = false;
            visit[22] = false;
        } else if (start == 31) {
            visit[10] = false;
            visit[31] = false;
        } else if (start == 39) {
            visit[15] = false;
            visit[39] = false;
        } else {
            visit[start] = false;
        }
    }


}
