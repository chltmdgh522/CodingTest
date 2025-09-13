import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static String[] ANSWER = {"valid", "invalid"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine();

            if (input.equals("end")) {
                break;
            }

            String result = playGame(input);
            System.out.println(result);
        }

    }

    public static String playGame(String input) {
        List<Integer> horseCnt = new ArrayList<>();
        char[][] board = new char[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = input.charAt(i);
        }

        // 1차 거름망 x가 o보다 1개 많거나 같아야됨
        if (!cntXO(board, horseCnt)) {
            return ANSWER[1];
        }


        String verification = verification(board, horseCnt.get(0), horseCnt.get(1));


        return verification;

    }

    private static String verification(char[][] board, int xCnt, int oCnt) {
        Set<String> setChar = new HashSet<>();
        boolean simulationFlag = simulation(board, setChar);

        if (setChar.size() >= 2) {
            return ANSWER[1];
        }

        String checkChar = "";
        Iterator<String> iterator = setChar.iterator();

        if (iterator.hasNext()) {
            checkChar = iterator.next();
        }


        if (simulationFlag && checkChar.equals("O")) {
            if (xCnt == oCnt + 1) {
                return ANSWER[1];
            }
        }else if(simulationFlag && checkChar.equals("X")){
            if (xCnt == oCnt) {
                return ANSWER[1];
            }
        }



        if (checkChar.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '.') {
                        return ANSWER[1];
                    }
                }
            }
        }

        return ANSWER[0];

    }


    private static boolean simulation(char[][] board, Set<String> answer) {
        boolean resultFlag = false;
        boolean flag = false;
        //  가로
        for (int i = 0; i < 3; i++) {
            flag = board[i][0] != '.' && board[i][0] == board[i][1] && board[i][1] == board[i][2];
            if (flag) {
                answer.add(String.valueOf(board[i][0]));
                if (!resultFlag) resultFlag = true;
            }

        }

        // 세로
        for (int i = 0; i < 3; i++) {
            flag = board[0][i] != '.' && board[0][i] == board[1][i] && board[1][i] == board[2][i];
            if (flag) {
                answer.add(String.valueOf(board[0][i]));
                if (!resultFlag) resultFlag = true;
            }
        }

        // 대각선
        flag = board[0][0] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2];
        if (flag) {
            answer.add(String.valueOf(board[0][0]));
            if (!resultFlag) resultFlag = true;
        }

        flag = board[0][2] != '.' && board[0][2] == board[1][1] && board[1][1] == board[2][0];
        if (flag) {
            answer.add(String.valueOf(board[0][2]));
            if (!resultFlag) resultFlag = true;
        }

        return resultFlag;
    }


    public static boolean cntXO(char[][] board, List<Integer> horseCnt) {
        int x = 0;
        int o = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 'X') {
                    x++;
                } else if (board[i][j] == 'O') {
                    o++;
                }
            }
        }

        horseCnt.add(x);
        horseCnt.add(o);
        if (x + o <= 4) return false;


        return x == o + 1 || x == o;
    }
}
