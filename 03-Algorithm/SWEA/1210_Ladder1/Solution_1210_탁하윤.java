package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // 3방 탐색 (좌, 우, 상)
        int[] dx = {0, 0, -1};
        int[] dy = {-1, 1, 0};

        for(int tc=1; tc<=10; tc++) {
        	int T = Integer.parseInt(br.readLine());    // 테스트 케이스 번호 (10까지 진행)
            int[][] ladder = new int[100][100];			// ladder를 담을 배열
            boolean[][] isVistied = new boolean[100][100];	// 방문 체크 배열
            
            int x = 0;	// 도착점 x좌표	[시작점]
            int y = 0;	// 도착점 y좌표

            // ladder 만들기
            for(int i=0; i<100; i++){
            	st = new StringTokenizer(br.readLine());
                for(int j=0; j<100; j++){
                    ladder[i][j]=Integer.parseInt(st.nextToken());
                    if(ladder[i][j]==2) {
                    	// 도착점이라면 도착점의 좌표 대입
                    	x=i;
                    	y=j;
                    }
                }
            }
            
            // 시작점까지 while문
            while (x>0) {            	
            	for(int k=0; k<3; k++){
            		int kx = x + dx[k];	// 다음 x좌표
            		int ky = y + dy[k];	// 다음 y좌표
            		
            		if(kx>=0 && kx<100 && ky>=0 && ky<100 && ladder[kx][ky]==1 && isVistied[kx][ky]==false){
            			// 좌표가 영역을 벗어나지 않고, 사다리이고, 아직 방문하지 않은곳이라면
            			// 다음 좌표를 대입하고 방문 처리
            			x = kx;
            			y = ky;
            			isVistied[kx][ky] = true;
            		}
            	}
            }

            System.out.printf("#%d %d\n", tc, y);
        }
    }

}
