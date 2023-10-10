
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; //동남서북 (시계방향)
    static int[] dy = {1, 0, -1, 0};
    static List<Pos> airCleaner;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); //행
        C = Integer.parseInt(st.nextToken()); //열
        T = Integer.parseInt(st.nextToken()); //초
        map = new int[R][C];
        airCleaner = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) { //공기청정기 좌표
                    airCleaner.add(new Pos(i, j));
                }
            }
        }
        while (T-- > 0) {
            boolean[][] visited = new boolean[R][C];
            Queue<Pos> queue = new ArrayDeque<>();
            cntDust(visited, queue); //미세먼지 방문처리
            dust(visited, queue); //미세먼지 확산
            airClean(); //공기청정기

        }
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    ans += map[i][j];
                }
            }
        }
        System.out.println(ans);


    }

    public static void dust(boolean[][] visited, Queue<Pos> queue) {
        while (!queue.isEmpty()) {
            int size = queue.size();
            int[][] spread = new int[R][C];

            while (size-- > 0) {
                Pos now = queue.poll();
                int nowDust = map[now.x][now.y];
                int aroundRoomCnt = 0;

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (map[nx][ny] != -1)
                        {
                            if (map[nx][ny] == 0) { //비어있는 칸이라면
                                map[nx][ny] = nowDust / 5; //미세먼지 확산
                            }
                            else if (map[nx][ny] > 0) { //이미 미세먼지가 있다면
                                if (!visited[nx][ny]) { //기존 미세먼지 아님
                                    map[nx][ny] += nowDust / 5;
                                }
                                else { //기존 미세먼지 위치
                                    spread[nx][ny] += nowDust / 5;
                                }
                            }
                            aroundRoomCnt++; //인접한 칸
                        }
                    }
                }
                map[now.x][now.y] = (nowDust - (nowDust / 5) * aroundRoomCnt) + spread[now.x][now.y];
                visited[now.x][now.y] = false;
            }
        }
    }

    public static void airClean() {
        Pos up = airCleaner.get(0); //위 -> 반시계

        //위(반시계)
        int standardRow = up.x;

        //아래로 당기기
        for (int r = standardRow - 1; r > 0; r--) {
            map[r][0] = map[r - 1][0];
        }
        //왼쪽으로 당기기
        for (int c = 0; c < C - 1; c++) {
            map[0][c] = map[0][c + 1];
        }
        //위로 당기기
        for (int r = 0; r < standardRow; r++) {
            map[r][C - 1] = map[r + 1][C - 1];
        }
        //오른쪽으로 당기기
        for (int c = C - 1; c > 1; c--) {
            map[standardRow][c] = map[standardRow][c - 1];
        }
        map[standardRow][1] = 0;

        //아래 (시계)
        int down = standardRow + 1;
        // 위로 당기기
        for (int i = down + 1; i < R - 1; i++)
            map[i][0] = map[i + 1][0];
        // 왼쪽으로 당기기
        for (int i = 0; i < C - 1; i++)
            map[R - 1][i] = map[R - 1][i + 1];
        // 아래로 당기기
        for (int i = R - 1; i > down; i--)
            map[i][C - 1] = map[i - 1][C - 1];
        // 오른쪽으로 당기기
        for (int i = C - 1; i > 1; i--)
            map[down][i] = map[down][i - 1];
        map[down][1] = 0;

    }

    //미세먼지 방문 처리 메서드
    public static void cntDust(boolean[][] visited, Queue<Pos> queue) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    visited[i][j] = true;
                    queue.add(new Pos(i, j));
                }
            }
        }
    }


}

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
