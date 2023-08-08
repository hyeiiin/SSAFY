package baekjoon;
import java.util.*;
import java.io.*;
public class Main_16926_김태훈 {
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//진짜 힘들게 풀었습니다
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int min;
		int[][] map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Math.min(n, m);
		while(r>0) {
			int x, y;
			for (int i = 0; i < min/2; i++) {
				x = i;
				y = i;
				int temp = map[x][y];
				
				int dir=0;
				while (dir < 4) { 
					int nx = x + dx[dir];
					int ny = y + dy[dir];

					if (nx >= i && nx < n - i && ny >= i && ny < m - i) {
						map[x][y] = map[nx][ny];
						x = nx;
						y = ny;
					} else {
						dir++;
					}
				}
				map[x+1][y] = temp;
			}
			r--;
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
