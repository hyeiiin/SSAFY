package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_탁하윤 {
    static int N, M, map[][], max;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());	// 지도 세로 크기
        M = Integer.parseInt(st.nextToken());	// 지도 가로 크기
        map = new int[N][M];	// 지도 map
        max = 0;	// 안전 영역 최대 크기

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());	// map 초기화
            }
        }

        dfs(0);	// dfs로 벽 3개 세우기

        System.out.println(max);	// 안전 영역 최대 크기 출력
    }

    static void dfs(int wall){  // 벽세우기 = 완탐 = 백트레킹
        if(wall == 3){  // 벽 3개 설치된 경우 bfs 탐색 시작
            bfs();
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0){	// 0이라면 1로 벽 세우기
                    map[i][j] = 1;
                    dfs(wall+1);
                    map[i][j] = 0;	// 돌아오면 다시 0으로 바꾸기
                }
            }
        }
    }

    static void bfs() { // 바이러스 뿌리기
        Queue<int[]> q = new ArrayDeque<>();
        int[][] virus = new int[N][M];

        for (int i = 0; i < N; i++) {   // map 복사
            for (int j = 0; j < M; j++) {
                virus[i][j] = map[i][j];
                if(virus[i][j] == 2){   // 바이러스 부분 q에 담기
                    q.offer(new int[] {i, j});
                }
            }
        }

        while(!q.isEmpty()){
            int[] now = q.poll();	// 현재 바이러스
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny <M){	// 범위 안이라면
                    if(virus[nx][ny] == 0) {    // 빈칸 바이러스 뿌리기
                        q.offer(new int[] {nx, ny});
                        virus[nx][ny] = 2;
                    }
                }
            }
        }

        int safe = 0;   // 안전 영역 크기 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(virus[i][j] == 0){
                    safe++;
                }
            }
        }

        max = Math.max(max, safe);	// 안전 영역 최대 크기 구하기
    }
}