package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1861_전상혁 {
	static int N, arr[][], max_start;
	static int max;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			max = 0;
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					
					//[1,2],[3,4]
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					room(i,j,1);
				}
			}
			
			System.out.printf("#%d %d %d%n",tc, max_start, max);
		}
	}
	private static int room(int r, int c, int cnt) {
		//{{-1,0},{1,0},{0,-1},{0,1}};

		for (int d = 0; d < delta.length; d++) {
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];
			
			if (isln(nr,nc)) {
				if (arr[r][c]+1==arr[nr][nc]) {
					
					cnt = room(nr, nc, cnt+1);
				}else {
					continue;
				}
				
			}else {
				continue;
			}
			
			if (cnt>max) {
				max = cnt;
				max_start = arr[r][c];
				
			}else if (cnt==max && arr[r][c]<max_start) {
				max_start = arr[r][c];
			}
			break;
		}
		return cnt;
	}

	
	private static boolean isln(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}
