import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int m;
	static int r;
	static int[][] l = new int[301][301];
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < m; j++) {
				l[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int round = Math.min(n, m);
		
		for(int u = 0; u < r; u++) {
			int s = 0;
			for(int i = 0; i < round/2; i++) {
				int temp = l[s][s];
				for(int j = s; j < m-s-1; j++) {
					l[i][j] = l[i][j+1];
				}
				for(int j = s; j < n-1-s; j++) {
					l[j][m-i-1] = l[j+1][m-i-1];
				}
				for(int j = s; j < m-s-1; j++) {
					l[n-i-1][m-1-j] = l[n-i-1][m-j-2];
				}
				for(int j = s; j < n-1-s; j++) {
					l[n-j-1][i] = l[n-j-2][i];
				}
				l[s+1][s]= temp;
				s++;
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(l[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
}