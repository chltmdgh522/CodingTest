
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int K;

    static int[][] map;

    static class Shaker {
        int id, dir, x, y;
        ShakerDir shakerDir;
        boolean alive;

        public Shaker(int id, int dir, int x, int y, boolean alive) {
            this.id = id;
            this.dir = dir;
            this.x = x;
            this.y = y;
            this.alive = alive;
        }
    }

    static class ShakerDir {
        int id;
        List<List<Integer>> dirList;

        public ShakerDir(int id, List<List<Integer>> dirList) {
            this.id = id;
            this.dirList = dirList;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static List<Shaker> shakerList;
    static List<ShakerDir> shakerDirList;

    static int[][][] smell; // [x][y][0] = 상어ID, [x][y][1] = 냄새 남은 시간
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        smell = new int[N][N][2];
        int[][] tempPost = new int[M][2];

        // 맵 초기화 및 상어 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    tempPost[map[i][j] - 1][0] = i;
                    tempPost[map[i][j] - 1][1] = j;
                    smell[i][j][0] = map[i][j];
                    smell[i][j][1] = K;
                }
            }
        }

        // 상어 초기 방향 설정
        shakerList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int id = 1; id <= M; id++) {
            int dir = Integer.parseInt(st.nextToken()) - 1;
            shakerList.add(new Shaker(id, dir, tempPost[id - 1][0], tempPost[id - 1][1], true));
        }

        // 상어별 방향 우선순위 설정
        shakerDirList = new ArrayList<>();
        for (int id = 1; id <= M; id++) {
            List<List<Integer>> dirList = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                List<Integer> priority = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    priority.add(Integer.parseInt(st.nextToken()) - 1);
                }
                dirList.add(priority);
            }
            shakerDirList.add(new ShakerDir(id, dirList));
        }

        // 상어와 방향 연결
        for (int id = 1; id <= M; id++) {
            Shaker shaker = shakerList.get(id - 1);
            shaker.shakerDir = shakerDirList.get(id - 1);
        }

        solve();

        if (answer > 1000) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void solve() {
        while (answer <= 1000) {
            // 1번 상어만 남았는지 확인
            int aliveCount = 0;
            for (Shaker shaker : shakerList) {
                if (shaker.alive) {
                    aliveCount++;
                }
            }

            if (aliveCount == 1) {
                return; // 1번 상어만 남음
            }

            answer++;

            // 각 상어를 이동시키고 새로운 위치 저장
            List<Shaker> movingShakers = new ArrayList<>();
            for (Shaker shaker : shakerList) {
                if (!shaker.alive) continue;

                int sx = shaker.x;
                int sy = shaker.y;
                int currentDir = shaker.dir;
                ShakerDir shakerDir = shaker.shakerDir;

                List<Integer> nextDirs = shakerDir.dirList.get(currentDir);
                boolean moved = false;
                int fallbackDir = -1;
                int fallbackX = -1, fallbackY = -1;

                // 1단계: 냄새가 없는 칸으로 이동 시도
                for (int dir : nextDirs) {
                    int nx = sx + dx[dir];
                    int ny = sy + dy[dir];

                    // 격자 범위 체크
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    // 냄새가 있는지 체크
                    if (smell[nx][ny][1] > 0) {
                        // 자신의 냄새인 경우 fallback으로 저장
                        if (smell[nx][ny][0] == shaker.id && fallbackDir == -1) {
                            fallbackDir = dir;
                            fallbackX = nx;
                            fallbackY = ny;
                        }
                        continue;
                    }

                    // 냄새가 없는 칸 발견 - 이동
                    Shaker newShaker = new Shaker(shaker.id, dir, nx, ny, true);
                    newShaker.shakerDir = shaker.shakerDir;
                    movingShakers.add(newShaker);
                    moved = true;
                    break;
                }

                // 2단계: 냄새가 없는 칸이 없으면 자신의 냄새가 있는 칸으로 이동
                if (!moved && fallbackDir != -1) {
                    Shaker newShaker = new Shaker(shaker.id, fallbackDir, fallbackX, fallbackY, true);
                    newShaker.shakerDir = shaker.shakerDir;
                    movingShakers.add(newShaker);
                }
            }

            // 냄새 시간 감소
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (smell[i][j][1] > 0) {
                        smell[i][j][1]--;
                        if (smell[i][j][1] == 0) {
                            smell[i][j][0] = 0;
                        }
                    }
                }
            }

            // 맵 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = 0;
                }
            }

            // 상어들을 새로운 위치에 배치하고 충돌 처리
            for (Shaker shaker : shakerList) {
                shaker.alive = false; // 일단 모두 죽음으로 설정
            }

            for (Shaker movingShaker : movingShakers) {
                int nx = movingShaker.x;
                int ny = movingShaker.y;

                if (map[nx][ny] == 0) {
                    // 빈 칸이면 상어 배치
                    map[nx][ny] = movingShaker.id;

                    // 원래 상어 정보 업데이트
                    Shaker originalShaker = shakerList.get(movingShaker.id - 1);
                    originalShaker.x = nx;
                    originalShaker.y = ny;
                    originalShaker.dir = movingShaker.dir;
                    originalShaker.alive = true;

                    // 냄새 설정
                    smell[nx][ny][0] = movingShaker.id;
                    smell[nx][ny][1] = K;
                } else {
                    // 이미 다른 상어가 있으면 번호가 작은 상어가 살아남음
                    if (movingShaker.id < map[nx][ny]) {
                        // 기존 상어 제거
                        shakerList.get(map[nx][ny] - 1).alive = false;

                        map[nx][ny] = movingShaker.id;

                        // 원래 상어 정보 업데이트
                        Shaker originalShaker = shakerList.get(movingShaker.id - 1);
                        originalShaker.x = nx;
                        originalShaker.y = ny;
                        originalShaker.dir = movingShaker.dir;
                        originalShaker.alive = true;

                        // 냄새 설정
                        smell[nx][ny][0] = movingShaker.id;
                        smell[nx][ny][1] = K;
                    }
                    // 현재 상어가 번호가 크면 자동으로 alive = false 상태로 남음
                }
            }

            if (answer > 1000) {
                return; // 1000초 초과
            }
        }
    }
}