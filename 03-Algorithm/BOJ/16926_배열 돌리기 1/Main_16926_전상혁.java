package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16926_전상혁 {
	static int N, M, R, arr[][];
	static int delta[][] = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//회전하는 박스의 개수
		int cnt = Math.min(N,M)/2;
		
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < cnt; j++) {
				int r = j;
				int c = j;
				
				int v = arr[r][c];
				int d = 0;
				
				while(d<4) {
					int nr = r + delta[d][0];
					int nc = c + delta[d][1];
					
					if (isln(nr,nc,j)) {
						arr[r][c] = arr[nr][nc];
						r = nr;
						c = nc;
						
					}else d++;	

				}
				arr[j+1][j] = v;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	private static boolean isln(int r, int c, int n) {
		return r>=n && r<N-n && c>=n && c<M-n;
	}

}
