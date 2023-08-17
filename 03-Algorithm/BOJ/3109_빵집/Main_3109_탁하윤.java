package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3109_탁하윤 {
    static boolean route[][];
    static int N, M, cnt;
    static int[] dx = {-1, 0, 1};   // 3방 탐색: 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
    static int[] dy = {1, 1, 1};
    static boolean done;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // R
        M = Integer.parseInt(st.nextToken());   // C
        route = new boolean[N][M];  // 빵집과 가스관
        cnt = 0;

        for (int i = 0; i < N; i++) { // 초기화
            String str = br.readLine();
            for (int j = 0; j < M; j++) {   // x: 건물인 경우 못가도록 true처리
                if(str.charAt(j) == 'x'){
                    route[i][j] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {   // j==0인 곳은 근처 빵집의 가스관
            done = false;   // 왔던
            dfs(i, 0);
        }

        System.out.println(cnt);
    }

    static void dfs(int i, int j) {
        route[i][j] = true; // 온 곳이기 때문에 true 표시

        if(j == M-1){   // j == M-1인 곳은 원웅이의 빵집
            done = true;    // 도착한 곳이기 때문에 true 표시
            cnt++;  // 파이프 라인 수 증가
            return;
        }

        for (int k = 0; k < 3; k++) {   // 3방 탐색
            int nx = i+dx[k];
            int ny = j+dy[k];

            if(nx >= 0 && nx < N && ny >= 0 && ny < M && !route[nx][ny]){
                dfs(nx, ny);    // 범위 안이고 아직 들리지 않은 곳이라면 호출
                if(done) { // 만약 도착한 곳이라면 다음에 다시 방문하지 않도록 현재 좌표 true 표시 후 리턴
                    route[nx][ny] = true;
                    return;
                }
            }
        }
    }
}
