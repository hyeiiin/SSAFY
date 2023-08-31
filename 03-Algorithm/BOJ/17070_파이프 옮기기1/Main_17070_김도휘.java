import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 1}; //오른쪽, 아래, 대각선
    static int[] dy = {1, 0, 1};
    static boolean[][] visited;
    static int ans;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        movePipe(0, 1, 0); //(0,1)부터 시작해서 가로 파이프 모양부터
        System.out.println(ans);

    }

    public static void movePipe(int x, int y, int shape) {
        if (x == N - 1 && y == N - 1) {
            ans++;
            return;
        }
        int[] types = direction(shape);
        for (int i = 0; i < types.length; i++) {
            int type = types[i]; //0:가로 1:세로 2:대각선
            if (type == 2) { //대각선이라면
                //오른쪽, 아래 빈칸 확인
                int rightX = x + dx[0];
                int rightY = y + dy[0];
                int downX = x + dx[1];
                int downY = y + dy[1];
                //N 영역 안에 있고, 빈칸이라면
                if (isIn(rightX, rightY) && isIn(downX, downY) && isIn(x + dx[type], y + dy[type])) {
                    movePipe(x + dx[type], y + dy[type], type);
                }
            }
            else { //오른쪽, 아래
                int nx = x + dx[type];
                int ny = y + dy[type];
                if (isIn(nx, ny)) {
                    movePipe(nx, ny, type);
                }

            }
        }
    }

    public static boolean isIn(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N) {
            if (map[x][y] == 0) { //빈칸이라면
                return true;
            }
        }
        return false;
    }

    public static int[] direction(int nowPipe) {
        int[] nextPipe;

        switch (nowPipe) {
            case 0:  //가로
                nextPipe = new int[]{0, 2};
                return nextPipe;
            case 1: //세로
                nextPipe = new int[]{1, 2};
                return nextPipe;
            case 2: //대각선
                nextPipe = new int[]{0, 1, 2};
                return nextPipe;
        }
        return new int[3];
    }
}
