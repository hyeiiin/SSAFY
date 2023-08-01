
import java.util.Scanner;
import java.util.StringTokenizer;

class Solution_1210_이도훈 {

    public static int[] dirs = {1, -1};
    public static int endCol;
    public static int[][] ladder;

    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= 10; test_case++) {

            int N = Integer.parseInt(sc.nextLine());

            ladder = new int[100][100];
            StringTokenizer st;

            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < 100; j++) {
                    ladder[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 첫째줄 돌면서 dfs 시작
            for (int i = 0; i < 100; i++) {
                if (ladder[0][i] == 1) {
                    if (dfs(i, 0,0)) {
                        sb.append("#").append(test_case)
                                .append(" ")
                                .append(i).append("\n");
                        break;
                    }
                }
            }
        }
        System.out.println(sb);
    }

    public static boolean dfs(int x, int y, int dir) {
        if (y == 99) {
            if (ladder[y][x] == 2) return true;
            return false;
        }

        int moveX = x;
        int moveY = y;

        if (dir == 0) {
            // 좌우 체크
            for (int i : dirs) {
                moveX = x + i;
                dir = i;
                if (moveX < 0 || moveX > 99 || ladder[y][moveX] == 0) {
                    moveX = x;
                    continue;
                }
                break;
            }
        } else {
            moveX += dir;
            if (moveX < 0 || moveX > 99 || ladder[y][moveX] == 0) {
                moveX = x;
            }
        }

        if (moveX == x) {
            moveY += 1;
            dir = 0;
        }

        return dfs(moveX,moveY,dir);
    }

}
