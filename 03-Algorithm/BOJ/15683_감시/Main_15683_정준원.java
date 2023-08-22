package sdf;

import java.io.*;
import java.util.*;

class CCTV {
    int type, x, y;

    public CCTV(int type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
}

public class Main_15683_정준원 {
    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();
    static int[] dx = { -1, 0, 1, 0 }; // 상, 우, 하, 좌
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvs.add(new CCTV(map[i][j], i, j));
                }
            }
        }

        solve(0, map);
        System.out.println(answer);
    }

    static void solve(int idx, int[][] prevMap) {
        if (idx == cctvs.size()) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (prevMap[i][j] == 0) {
                        count++;
                    }
                }
            }
            answer = Math.min(answer, count);
            return;
        }

        CCTV cctv = cctvs.get(idx);
        int x = cctv.x;
        int y = cctv.y;

        int[][] curMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            curMap[i] = Arrays.copyOf(prevMap[i], M);
        }

        if (cctv.type == 1) {
            for (int d = 0; d < 4; d++) {
                mark(curMap, x, y, d);
                solve(idx + 1, curMap);
                unmark(curMap, x, y, d);
            }
        } else if (cctv.type == 2) {
            for (int d = 0; d < 2; d++) {
                mark(curMap, x, y, d);
                mark(curMap, x, y, (d + 2) % 4);
                solve(idx + 1, curMap);
                unmark(curMap, x, y, d);
                unmark(curMap, x, y, (d + 2) % 4);
            }
        } else if (cctv.type == 3) {
            for (int d = 0; d < 4; d++) {
                mark(curMap, x, y, d);
                mark(curMap, x, y, (d + 1) % 4);
                solve(idx + 1, curMap);
                unmark(curMap, x, y, d);
                unmark(curMap, x, y, (d + 1) % 4);
            }
        } else if (cctv.type == 4) {
            for (int d = 0; d < 4; d++) {
                mark(curMap, x, y, d);
                mark(curMap, x, y, (d + 1) % 4);
                mark(curMap, x, y, (d + 2) % 4);
                solve(idx + 1, curMap);
                unmark(curMap, x, y, d);
                unmark(curMap, x, y, (d + 1) % 4);
                unmark(curMap, x, y, (d + 2) % 4);
            }
        } else if (cctv.type == 5) {
            for (int d = 0; d < 4; d++) {
                mark(curMap, x, y, d);
            }
            solve(idx + 1, curMap);
        }
    }

    static void mark(int[][] map, int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != 6) {
            if (map[nx][ny] == 0) {
                map[nx][ny] = -1;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    static void unmark(int[][] map, int x, int y, int dir) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        while (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != 6) {
            if (map[nx][ny] == -1) {
                map[nx][ny] = 0;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }
}
