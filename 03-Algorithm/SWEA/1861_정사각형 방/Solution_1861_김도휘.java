import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    static int max;
    static int roomNum;
    static List<Room> roomList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            max = Integer.MIN_VALUE;
            roomNum = Integer.MAX_VALUE;
            roomList = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    map[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    visited = new boolean[N][N];
                    bfs(j, k);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("#" + i + " ");
            Collections.sort(roomList);
            for (Room r : roomList) {
                if (max == r.area) {
                    sb.append(r.num + " ");
                    break;
                }
            }
            sb.append(max);
            System.out.println(sb);
        }


    }

    public static void bfs(int x, int y) {
        Queue<Pos> queue = new ArrayDeque<>();
        queue.add(new Pos(x, y));
        visited[x][y] = true;
        int area = 0;

        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            area++;

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && map[nx][ny] - map[now.x][now.y] == 1) {
                        queue.add(new Pos(nx, ny));
                        visited[nx][ny] = true;

                    }
                }
            }
        }
        max = Math.max(max, area);
        if (area == max) {
            roomNum = map[x][y];
            roomList.add(new Room(roomNum, area));
        }

    }
}
class Pos
{
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Room implements Comparable<Room>
{
    int num;
    int area;

    public Room(int num, int area) {
        this.num = num;
        this.area = area;
    }

    @Override
    public int compareTo(Room o) {
        return this.num - o.num;
    }
}
