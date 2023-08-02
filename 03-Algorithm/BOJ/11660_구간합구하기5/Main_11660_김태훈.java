package baekjoon;
import java.util.*;
import java.io.*;
public class Main_11660_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int sum=0;
		int[][] map = new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				sum += Integer.parseInt(st.nextToken());
				map[i][j] = sum;
				if(j==n && i<n) {
					map[i+1][0] = sum;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
			sum = 0;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());	//1
			int y1 = Integer.parseInt(st.nextToken());	//1
			int x2 = Integer.parseInt(st.nextToken());	//4
			int y2 = Integer.parseInt(st.nextToken());	//4
			
			for(int j = x1; j <= x2; j++) {
				sum += map[j][y2] - map[j][y1-1];
			}
			sb.append(sum);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
