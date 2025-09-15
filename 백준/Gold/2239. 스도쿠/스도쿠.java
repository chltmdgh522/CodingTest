
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0';
            }
        }

        solve(0, 0);
    }

    // (row, col) 위치부터 채우기 시작
    private static void solve(int row, int col) {
        if (col == 9) { // 열 다 채웠으면 다음 행으로
            solve(row + 1, 0);
            return;
        }

        if (row == 9) { // 보드 끝까지 채웠으면 정답 출력
            printBoard();
            System.exit(0);
        }

        if (board[row][col] == 0) { // 빈칸이면
            for (int num = 1; num <= 9; num++) {
                if (isValid(row, col, num)) { // 넣을 수 있는 숫자면
                    board[row][col] = num;
                    solve(row, col + 1); // 다음 칸으로
                    board[row][col] = 0; // 다시 비워주기 (백트래킹)
                }
            }
        } else {
            solve(row, col + 1); // 빈칸 아니면 다음 칸으로
        }
    }

    // 숫자 넣어도 되는지 검사
    private static boolean isValid(int row, int col, int num) {
        // 행, 열 확인
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) return false; // 행
            if (board[i][col] == num) return false; // 열
        }

        // 3x3 박스 확인
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }

    // 보드 출력
    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
