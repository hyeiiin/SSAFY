package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2178_탁하윤 {
    static int N, M, cnt, map[][];
    static boolean visited[][];
    static int[] dx = {-1, 1, 0, 0};    // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 가로
        M = Integer.parseInt(st.nextToken());   // 세로
        map = new int[N][M];    // 미로 배열
        visited = new boolean[N][M];    // 방문 처리 배열

        for (int i = 0; i < N; i++) {   // 미로 초기화
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                if(str.charAt(j) - '0' == 1){
                    map[i][j] = 1;
                }
            }
        }
        bfs(0, 0);  // 너비 우선 탐색 호출
        System.out.println(map[N-1][M-1]);  // 누적한 이동 수 출력
    }

    static void bfs(int x, int y){  // x: 현재 x좌표, y: 현재 y좌표
        Queue<int[]> q = new ArrayDeque<>();    // 방문 관리 큐
        visited[x][y] = true;   // 현재 좌표 방문 처리
        q.offer(new int[] {x, y});    // 현재 좌표 q에 넣기

        while (!q.isEmpty()){   // 큐가 공백이 될 때 까지
            int pre[] = q.poll();   // 현재 좌표 q에 빼기
            int px = pre[0];    // x좌표
            int py = pre[1];    // y좌표

            for(int i=0; i<4; i++){ // 4방 탐색
                int nx = px + dx[i];    // 다음 좌표
                int ny = py + dy[i];

                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;    // 범위를 벗어나면 for문 다시
                if(visited[nx][ny] || map[nx][ny] == 0) continue;   // 방문했거나, 지나갈 수 없다면 for문 다시

                q.offer(new int[] {nx, ny});  // 큐에 다음 좌표값 넣기
                map[nx][ny] = map[px][py]+1;    // 이동거리 넣기
                visited[nx][ny] = true;         // 방문처리
            }
        }
    }
}
