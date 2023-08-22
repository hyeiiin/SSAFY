import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] office;
    static int[] dx = {0, 1, 0, -1}; //동, 남, 서, 북
    static int[] dy = {1, 0, -1, 0};
    static int[][][] mode = {{{0}}, {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 3}, {0, 1}, {1, 2}, {2, 3}},
            {{0, 1, 2}, {1, 2, 3}, {0, 1, 3}, {0, 2, 3}},
            {{0, 1, 2, 3}}};

    static ArrayList<Pos> cctvList;
    static ArrayList<Pos> wallList;
    static ArrayList<Integer> maxRoom;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        office = new int[N][M];
        cctvList = new ArrayList<>();
        wallList = new ArrayList<>();
        maxRoom = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                office[i][j] = Integer.parseInt(st.nextToken());
                if (office[i][j] != 0 && office[i][j] != 6) {
                    cctvList.add(new Pos(i, j, office[i][j]));
                }
            }
        }
        dfs(0, office);
        System.out.println(ans);
    }
    public static int check(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
    public static void dfs(int depth, int[][] map) {
        if (depth == cctvList.size()) {
            int cnt = check(map);
            ans = Math.min(ans, cnt);
            return;
        }
        int type = cctvList.get(depth).type;
        int x = cctvList.get(depth).x;
        int y = cctvList.get(depth).y;

        for (int i = 0; i < mode[type].length; i++) {
            int[][] copy = new int[N][M];

            for (int j = 0; j < N; j++) {
                copy[j] = map[j].clone();
            }
            for (int j = 0; j < mode[type][i].length; j++) {
                int dir = mode[type][i][j];
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                while (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (map[nx][ny] == 6) { //벽 만나면 끝
                        break;
                    }
                    copy[nx][ny] = -1;
                    nx += dx[dir];
                    ny += dy[dir];
                }
            }
            dfs(depth + 1, copy);
        }
    }
}
class Pos {
    int x, y, type;
    public Pos(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
