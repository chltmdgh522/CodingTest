import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int K;

    static int[][] map;

    static final int red = 1;
    static final int blue = 2;

    static final int white = 0;

    static class Horse {
        int id;

        public Horse(int id) {
            this.id = id;
        }
    }

    static class HorsePos {
        int id, dir, x, y;

        public HorsePos(int id, int dir, int x, int y) {
            this.id = id;
            this.dir = dir;
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    static List<Horse>[][] horseMap;
    static List<HorsePos> horseList;
    static int answer = 0;

    static boolean answerFlag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        horseMap = new ArrayList[N][N];
        horseList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                horseMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int id = 1; id <= K; id++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            horseMap[x][y].add(new Horse(id));
            horseList.add(new HorsePos(id, dir, x , y ));
        }

        if (solve()) System.out.println(answer);
        else System.out.println(-1);

    }

    private static boolean solve() {

        while (answer <= 1000) {
            answer++;
            for (int id = 0; id < K; id++) {
                HorsePos horsePos = horseList.get(id);
                int dir = horsePos.dir;
                int sx = horsePos.x;
                int sy = horsePos.y;
                int nx = sx + dx[dir];
                int ny = sy + dy[dir];


                // 블루 또는 경계 넘아갈떄
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == blue) {

                    boolean isMove = true;
                    // 반대 방향
                    int newDir = (dir % 2 == 0) ? dir + 1 : dir - 1;
                    nx = sx + dx[newDir];
                    ny = sy + dy[newDir];


                    // 제자리 그대로
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == blue) {
                        isMove = false;
                        horsePos.dir = newDir;
                    }

                    if(isMove) move(sx, sy, id, horsePos, nx, ny, newDir, true);
                }

                // 흰색
                else if (map[nx][ny] == white || map[nx][ny] == red) move(sx, sy, id, horsePos, nx, ny, dir, false);


                if (isAnswerFlag()) break;

            }


            if (isAnswerFlag()) break;

//            System.out.println(answer);
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(horseMap[i][j].size() + " ");
//                }
//                System.out.println();
//            }
        }
        return answerFlag;
    }

    private static void move(int sx, int sy, int id, HorsePos horsePos, int nx, int ny, int dir, boolean blueFlag) {
        int size = horseMap[sx][sy].size();
        List<Horse> temp = new ArrayList<>();
        for (int j = size; j > 0; j--) {

            // 아이디 기준 위에 말이 있을때 다같이 이동
            Horse horseRemove = horseMap[sx][sy].remove(horseMap[sx][sy].size()-1);
            // 하나씩 temp 값에 넣음 순서대로
            temp.add(horseRemove);
            if (horseRemove.id - 1 == id) {
                // 말 리스트 좌표 업데이트
                horsePos.x = nx;
                horsePos.y = ny;
                horsePos.dir = dir;
                break;
            } else {
                // 다른 말 리스트도 업데이트
                HorsePos horseTempPos = horseList.get(horseRemove.id - 1);
                horseTempPos.x = nx;
                horseTempPos.y = ny;
//                if (blueFlag) {
//                    int tempDir = horseTempPos.dir;
//                    tempDir = (tempDir % 2 == 0) ? tempDir + 1 : tempDir - 1;
//                    horseTempPos.dir = tempDir;
//                }

            }
        }

        if (map[nx][ny] == white) {
            // 위에 있는 말이 아래로 갔으니깐 다시 역순해줘야됨
            Collections.reverse(temp);
            for (Horse horse : temp) {
                horseMap[nx][ny].add(horse);
            }
        } else {
            // 레드는 안해도 됨
            for (Horse horse : temp) {
                horseMap[nx][ny].add(horse);
            }
        }
    }


    private static boolean isAnswerFlag() {
        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (horseMap[i][j].size() >= 4) {
                    answerFlag = true;
                    break outer;
                }
            }
        }
        return answerFlag;
    }
}
