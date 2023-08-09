import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution_1861_이도훈 {

    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {

            int N = Integer.parseInt(br.readLine());

            int[][] arr = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            Queue<Node> queue;

            int num = Integer.MAX_VALUE;
            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    queue = new ArrayDeque<>();

                    queue.add(new Node(j, i, 1));

                    while (!queue.isEmpty()) {
                        Node cur = queue.poll();

                        int moveCnt = 0;
                        for (int[] dir : dirs) {
                            int moveX = cur.x + dir[0];
                            int moveY = cur.y + dir[1];

                            if (moveX < 0 || moveY < 0 || moveX >= N || moveY >= N) continue;
                            if (arr[moveY][moveX] != arr[cur.y][cur.x] + 1) continue;

                            queue.add(new Node(moveX, moveY, cur.cnt + 1));
                            moveCnt++;
                        }

                        if (moveCnt == 0) {
                            if (max < cur.cnt) {
                                max = cur.cnt;
                                num = arr[i][j];
                            } else if (max == cur.cnt) {
                                num = Math.min(num, arr[i][j]);
                            }
                        }


                    }
                }
            }
            sb.append("#").append(test_case).append(" ").append(num).append(" ").append(max).append("\n");

        }
        System.out.println(sb);

    }

    static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

}
