
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        int normal = 0;
        int special = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    normal++;
                }
            }
        }
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs2(i, j);
                    special++;
                }
            }
        }
        System.out.print(normal + " " + special);

    }

    public static void bfs(int x, int y) {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    //방문하지 않았고, 같은 색상이면 고
                    if (!visited[nx][ny] && map[nx][ny] == map[x][y]) {
                        queue.offer(new Pos(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
    public static void bfs2(int x, int y) {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny]) {
                        if (map[x][y] == 'R') { //현재 색깔 R이라면
                            //다음 색깔 R이거나 G라면
                            if (map[nx][ny] == 'R' || map[nx][ny] == 'G') {
                                queue.offer(new Pos(nx, ny));
                                visited[nx][ny] = true;
                            }
                        } else if (map[x][y] == 'G') { //현재 색깔 G이라면
                            //다음 색깔 G거나, R이라면
                            if (map[nx][ny] == 'G' || map[nx][ny] == 'R') {
                                queue.offer(new Pos(nx, ny));
                                visited[nx][ny] = true;
                            }
                        }
                        else { //나머지 색상
                            if (map[nx][ny] == map[x][y]) {
                                queue.offer(new Pos(nx, ny));
                                visited[nx][ny] = true;
                            }
                        }

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
