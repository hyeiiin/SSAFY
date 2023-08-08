import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayDeque<Integer>[] queues = new ArrayDeque[Math.min(N, M) / 2];

        for (int i = 0; i < queues.length; i++) {
            queues[i] = new ArrayDeque<>();
        }

        int x1 = 0;
        int x2 = M - 1;
        int y1 = 0;
        int y2 = N - 1;
        int idx = 0;
        while (x1 <= x2 && y1 <= y2) {
            for (int i = y1; i <= y2; i++) {
                if (arr[i][x1] != 0) {
                    queues[idx].add(arr[i][x1]);
                    arr[i][x1] = 0;
                }
            }

            for (int i = x1 + 1; i <= x2; i++) {
                if (arr[y2][i] != 0) {
                    queues[idx].add(arr[y2][i]);
                    arr[y2][i] = 0;
                }
            }

            for (int i = y2 - 1; i >= y1; i--) {
                if (arr[i][x2] != 0) {
                    queues[idx].add(arr[i][x2]);
                    arr[i][x2] = 0;
                }
            }
            for (int i = x2 - 1; i > x1; i--) {
                if (arr[y1][i] != 0) {
                    queues[idx].add(arr[y1][i]);
                    arr[y1][i] = 0;
                }
            }


            x1 += 1;
            x2 -= 1;
            y1 += 1;
            y2 -= 1;
            idx++;
        }

        for (ArrayDeque<Integer> queue : queues) {
            int cnt = R;
            while (cnt != 0) {
                queue.addFirst(queue.pollLast());
                cnt--;
            }
        }

        x1 = 0;
        x2 = M - 1;
        y1 = 0;
        y2 = N - 1;
        idx = 0;
        while (x1 <= x2 && y1 <= y2) {
            for (int i = y1; i <= y2; i++) {
                if (arr[i][x1] == 0) {
                    arr[i][x1] = queues[idx].pollFirst();
                }
            }


            for (int i = x1 + 1; i <= x2; i++) {
                if (arr[y2][i] == 0) {
                    arr[y2][i] = queues[idx].pollFirst();
                }
            }

            for (int i = y2 - 1; i >= y1; i--) {
                if (arr[i][x2] == 0) {
                    arr[i][x2] = queues[idx].pollFirst();
                }
            }
            for (int i = x2 - 1; i > x1; i--) {
                if (arr[y1][i] == 0) {
                    arr[y1][i] = queues[idx].pollFirst();
                }
            }

            x1 += 1;
            x2 -= 1;
            y1 += 1;
            y2 -= 1;
            idx++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
