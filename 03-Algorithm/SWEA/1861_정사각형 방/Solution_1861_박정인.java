package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1861_박정인 {
	static class Position {
        int x, y;
 
        public Position(int x, int y) {
            super();
            this.x = x;
            this.y = y;         
        }                  
    }
    static int N, map[][];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
         
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
             
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }                               
             
            // 방의 개수, 방의 숫자
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return Integer.compare(o2[0], o1[0]);
                } 
                return Integer.compare(o1[1], o2[1]);
            });
             
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int cnt = bfs(i, j);
                    pq.offer(new int[] {cnt, map[i][j]});
                }
            }
             
            int[] result = pq.poll();
            sb.append("#").append(tc).append(" ").append(result[1]).append(" ").append(result[0]).append("\n");
        }
         
        System.out.println(sb);
    }
     
    private static int bfs(int x, int y) {
        Queue<Position> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.offer(new Position(x, y));
        visited[x][y] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            Position now = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                 
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                 
                if (!visited[nx][ny] && map[nx][ny] == map[now.x][now.y] + 1) {
                    visited[nx][ny] = true;
                    q.offer(new Position(nx, ny));
                }
            }
        }
         
        return cnt;
    }

}
