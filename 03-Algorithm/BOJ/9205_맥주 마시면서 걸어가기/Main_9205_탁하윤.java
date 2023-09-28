package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9205_탁하윤 {
    static int n, dx, dy;
    static List<int[]> adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());   // tc 개수
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            adj = new ArrayList<>();

            // 상근이 집
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {   // 편의점
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                adj.add(new int[] {x, y});
            }

            // 목적지
            st = new StringTokenizer(br.readLine());
            dx = Integer.parseInt(st.nextToken());
            dy = Integer.parseInt(st.nextToken());

            bfs(sx, sy);
        }
    }

    private static void bfs(int sx, int sy) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n]; // 편의점 방문 처리
        q.offer(new int[] {sx, sy});    // 상근이 집 부터 방문

        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];

            if(Math.abs(x-dx) + Math.abs(y-dy) <= 1000) {   // 현재 좌표부터 목적지까지 거리가 1000 이하라면 행복하게 페스티벌에 갈 수 있음
                System.out.println("happy");
                return;
            }

            for (int i = 0; i < n; i++) {   // 편의점 개수만큼 돌면서 현재 좌표와 편의점 거리가 1000이하라면 큐에 넣고 방문처리
                if(visited[i]) continue;
                int nx = adj.get(i)[0];
                int ny = adj.get(i)[1];

                if(Math.abs(x-nx) + Math.abs(y-ny) <= 1000) {
                    visited[i] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }
        System.out.println("sad");  // 여기까지 왔다면 더 이동 못하는 경우로 sad리턴
    }
}
