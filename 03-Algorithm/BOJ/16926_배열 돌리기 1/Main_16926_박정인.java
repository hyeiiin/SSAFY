package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_박정인 {
	static int N, M, R, map[][], tmp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		tmp = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < R; i++) {			
			rotate();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(tmp[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void rotate() {		
		int n = N - 1;	// 가로 이동개수
		int m = M - 1;	// 세로 이동개수
 
		// 이동범위 모서리
		int ulx = 0, uly = 0;
		int urx = 0, ury = m;
		int dlx = n, dly = 0;
		int drx = n, dry = m;
		
		while (n > 0 && m > 0) {
			// 위
			for (int j = ury; j >= ury - m + 1; j--) {
				tmp[urx][j - 1] = map[urx][j];
			}
			ury--;
			urx++;
			
			// 왼쪽
			for (int i = ulx; i <= n + ulx - 1; i++) {
				tmp[i + 1][uly] = map[i][uly];
			}
			ulx++;	uly++;
			
			// 아래
			for (int j = dly; j <= m + dly - 1; j++) {
				tmp[dlx][j + 1] = map[dlx][j];
			}
			dlx--;	dly++;
			
			// 오른쪽
			for (int i = drx; i >= drx - n + 1; i--) {
				tmp[i - 1][dry] = map[i][dry];
			}
			drx--;	dry--;
			
			n-=2;
			m-=2;
		}
		
		copy();
	}
	
	private static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}
}
