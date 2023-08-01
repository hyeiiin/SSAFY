import java.io.*;
import java.util.*;

public class Solution {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0};
    static int[] dy = {1, -1};
    static int answerX;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            int T = Integer.parseInt(br.readLine());
            map = new int[100][100];

            for (int j = 0; j < 100; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 100; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            answerX = 0;
            for (int j = 0; j < 100; j++) {
                if (map[0][j] == 1) {
                    bfs(0, j); //사다리 출발
                }
            }
            System.out.println("#" + (i + 1) + " " + answerX);

        }
    }

    public static void bfs(int x, int y) {
        //출발 좌표 x, y
        Queue<Pos> queue = new LinkedList<>();
        visited = new boolean[100][100];
        visited[x][y] = true;
        //출발좌표에서 한칸 직진
        if (map[x + 1][y] == 1) {
            visited[x + 1][y] = true;
            queue.add(new Pos(x + 1, y));
        }

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            if (map[now.x][now.y] == 2) {
                answerX = y;
                return;
            }
            //주변에 1이 있는지 확인
            boolean isTrue = false;

            //좌우
            for (int i = 0; i < 2; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100) {
                    if (map[nx][ny] != 0 && !visited[nx][ny]) {
                        isTrue = true;
                        queue.add(new Pos(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
            //좌우로 1 없으면 직진
            if (!isTrue) {
                int nx = now.x + 1;
                int ny = now.y;
                if (nx >= 0 && nx < 100 && ny >= 0 && ny < 100) {
                    if (map[nx][ny] != 0 && !visited[nx][ny]) {
                        queue.add(new Pos(nx, ny));
                        visited[nx][ny] = true;

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