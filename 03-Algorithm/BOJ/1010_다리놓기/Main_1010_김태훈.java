package baekjoon;
import java.util.*;
import java.io.*;
public class Main_1010_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int T, N, M, count, sum, c[][];
	static boolean visit[];
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		N = Math.min(a, b);
		M = Math.max(a, b);
		count = 0;
		sum = 0;
		c = new int[M+1][N+1];
	}

	
	static void solve() {
		for (int i = 0; i <= M; i++) {
			for (int j = 0; j <= N; j++) {
				if(j > i) break;
				if(j ==0 || i == 1) c[i][j] = 1;
				else {
					c[i][j] = c[i-1][j-1] + c[i-1][j];
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			init();
			solve();
			sb.append(c[M][N]).append("\n");
		}
		System.out.println(sb);
	}

}
