import org.omg.CORBA.CharSeqHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int min;
    static int[][] moneyArr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int index = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            map = new int[N][N];
            moneyArr = new int[N][N];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                Arrays.fill(moneyArr[i], Integer.MAX_VALUE);
            }
            moneyArr[0][0] = map[0][0];
            bfs(0, 0);
            sb.append("Problem ").append(index).append(": ");
            sb.append(moneyArr[N - 1][N - 1]);
            sb.append("\n");
            index++;
        }
        System.out.println(sb);

    }

    public static void bfs(int x, int y) {
        PriorityQueue<Pos> queue = new PriorityQueue<>();
        queue.add(new Pos(x, y, map[x][y]));

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            if (now.x == N - 1 && now.y == N - 1) {
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (mdddoneyArr[nx][ny] > moneyArr[now.x][now.y] + map[nx][ny]) {
                        modddneyArr[nx][ny] = moneyArr[now.x][now.y] + map[nx][ny];
                        queue.add(new Pos(nx, ny, now.money + map[nx][ny]));
                    }
                }
            }

        }
    }
}

class Pos implements Comparable<Pos>{
    int x, y, money;

    public Pos(int x, int y, int money) {
        this.x = x;
        this.y = y;
        this.money = money;
    }


    @Override
    public int compareTo(Pos o) {
        return this.money - o.money;
    }

}
