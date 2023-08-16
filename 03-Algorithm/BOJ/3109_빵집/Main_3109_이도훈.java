import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dirs = {{1, -1}, {1, 0}, {1, 1}};

    static boolean[][] map;
    static int R;
    static int C;
    static int ans;
    static boolean isStop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new boolean[R][C];
        ans = 0;
        isStop = false;
        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j) != 'x';
            }
        }

        for (int i = 0; i < R; i++) {
            dfs(0, i);
            isStop = false;
        }
        System.out.println(ans);

    }


    public static void dfs(int x, int y) {
        if (x == C - 1 && map[y][x]) {
            map[y][x] = false;
            ans++;
            isStop = true;
            return;
        }

        map[y][x] = false;

        for (int[] dir : dirs) {
            int moveX = x + dir[0];
            int moveY = y + dir[1];

            if(moveX < 0 || moveY < 0 || moveX >= C || moveY >= R) continue;
            if(!map[moveY][moveX]) continue;

            dfs(moveX, moveY);
            if(isStop) return;

        }

    }


}

