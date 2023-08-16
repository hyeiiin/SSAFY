import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};
    static int ans;
    static boolean isPossible;
    static int max = 0;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        visited = new boolean[R][C];

        //근처 빵집 가스관
        for (int i = 0; i < R; i++) {
            isPossible = false;
            map[i][0] = 'x';
            makePipe(i, 0);
            //한 행이 마지막 열까지 탐색했으니 파이프 하나 생성
            if (isPossible) {
                ans++;
            }
        }
        System.out.println(ans);

    }

    public static void makePipe(int x, int y) {

        //마지막 열에 도착했다면 
        if (y == C - 1) {
            isPossible = true;
            return;
        }
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                if (map[nx][ny] != 'x') {
                    map[nx][ny] = 'x';
                    makePipe(nx, ny);
                    //마지막 열에 도착하면 다른 곳 탐색안하도록 빠져나오기 -> 다음 행 시작
                    if (isPossible) {
                        return;
                    }
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
