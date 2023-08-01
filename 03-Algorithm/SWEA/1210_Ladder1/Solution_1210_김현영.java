import java.io.*;
import java.util.*;

public class Solution_1210_김현영 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());

			// 사다리 이차원배열
			int[][] ladder = new int[100][100];
			
			// 도착점
			int r = 0;
			int c = 0;

			// 사다리 입력
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j <100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
					//도착점의 좌표 찾기
					if (ladder[i][j] == 2) {
						r = i;
						c = j;
					}
				}
			}
			 
			int[] dy = {0, 0, -1}; // y축 방향 이동
			int[] dx = {-1, 1, 0}; // x축 방향 이동
			
			//y축 가장 위로 올라올 때까지 반복
			while(r > 0) {
				// 좌,우,상 을 확인하며 1이라면 진행
				for (int i = 0; i < 3; i++) {
					int x = r + dy[i];
					int y = c + dx[i];
					if( 0<= x && x < 100 && 0<= y && y<100 && ladder[x][y]==1){
						r = x;
						c = y;						
						ladder[r][c] = 0; //지나온 사다리 0으로 바꾸기
//						System.out.println(r+"/"+ c);
						break;
					} 
				} 
			}
			
			System.out.printf("#%d %d\n", t, c);

		}

	}

}
