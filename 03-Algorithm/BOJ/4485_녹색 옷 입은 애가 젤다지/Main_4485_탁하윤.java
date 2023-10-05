package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485_탁하윤 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int cnt = 0;
        while(true) {
            cnt++;  // 현재 tc 번호
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            int[][] map = new int[N][N];    // 그래프
            int[][] dp = new int[N][N];     // 누적 도둑루피 값
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dp[i][j] = 987654321;    // 큰 수로 초기화
                }
            }

            // 우선순위 큐를 사용하여 누적 도둑루피가 가장 적은 값이 우선순위가 높도록 지정
           PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
                return o1[2]-o2[2]; // 오름차순 정렬
            });
            // 시작점 [0][0], 현재 도둑 루피값은 map[0][0]
            q.offer(new int[] {0, 0, map[0][0]});
            while(!q.isEmpty()) {
                int[] now = q.poll();   // 현재까지 누적 도둑루피 값이 작은 값 추출
                int x = now[0]; // 현재 좌표 x
                int y = now[1]; // y
                int weight = now[2];    // 누적 도둑루피

                for (int i = 0; i < 4; i++) {   // 4방 탐색
                    int nx = x+dx[i];
                    int ny = y+dy[i];

                    if(nx<0 || nx>=N || ny<0 || ny>=N) continue;    // 범위 벗어난곳 continue
                    if(dp[nx][ny] > weight + map[nx][ny]) { // 다음 누적 도둑루피값이 현재 도둑루피값과 다음 도둑루피값을 더한것보다 많다면 갱신하고 q에 넣기
                        dp[nx][ny] = weight + map[nx][ny];
                        q.offer(new int[] {nx, ny, dp[nx][ny]});
                    }
                }
            }

            sb.append("Problem ").append(cnt).append(": ").append(dp[N-1][N-1]).append("\n");
        }

        System.out.println(sb.toString());
    }

}
