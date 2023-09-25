import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static List<Pos> virus = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) { //바이러스
                    virus.add(new Pos(i, j));
                }
            }

        }
        makeWall(0);
        System.out.println(max);
    }

    public static void makeWall(int wallCnt) {
        //벽이 3개 세워졌다면
        if (wallCnt == 3) {
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) { //빈칸이라면
                    map[i][j] = 1; //벽 세우기
                    makeWall(wallCnt + 1);
                    map[i][j] = 0; //벽 허물기

                }
            }
        }
    }

    public static void bfs() {
        Queue<Pos> queue = new ArrayDeque<>();
        visited = new boolean[N][M];
        int room = 0;

        for (Pos wall : virus) {
            queue.add(new Pos(wall.x, wall.y));

            while (!queue.isEmpty()) {
                Pos now = queue.poll();
                room++;

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (map[nx][ny] == 0 && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.add(new Pos(nx, ny));
                        }
                    }
                }
            }
        }
        //안전지역
        int safe = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    safe++;
                }
            }
        }
        max = Math.max(max, safe);


    }


}

class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
