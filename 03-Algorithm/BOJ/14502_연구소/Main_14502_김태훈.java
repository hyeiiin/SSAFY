package baekjoon;
import java.util.*;
import java.io.*;

public class Main_14502_김태훈 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    static int N, M; // 지도의 세로 크기 N과 가로 크기 M (3 ≤ N, M ≤ 8)
    static int[][] map; // 지도
    static int maxSave;
    static Stack<int[]> virusLocations;

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        virusLocations = new Stack<>();
        maxSave = 0;
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virusLocations.push(new int[] {i, j});
                }
            }
        }
    }

    static void solve() {
        dfs(0, 0);
    }

    static void dfs(int wallCount, int index) {
        if (wallCount == 3) {
            maxSave = Math.max(maxSave, bfs());
            return;
        }
        for (int i = index; i < N * M; i++) {
            int x = i / M;
            int y = i % M;
            if (map[x][y] == 0) {
                map[x][y] = 1; // 벽 세우기
                dfs(wallCount + 1, i + 1);
                map[x][y] = 0; // 벽 제거
            }
        }
    }

    static int bfs() {
        int[][] copyMap = new int[N][M];
        copyMap(copyMap);
        int safeAreaCount = 0;
        Queue<int[]> queue = new LinkedList<>(virusLocations);

        while (!queue.isEmpty()) {
            int[] virus = queue.poll();
            int x = virus[0];
            int y = virus[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (isValid(nx, ny) && copyMap[nx][ny] == 0) {
                    copyMap[nx][ny] = 2;
                    queue.offer(new int[] {nx, ny});
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    safeAreaCount++;
                }
            }
        }
        return safeAreaCount;
    }

    private static boolean isValid(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private static void copyMap(int[][] copyMap) {
        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, copyMap[i], 0, M);
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        solve();
        System.out.println(maxSave);
    }
}
