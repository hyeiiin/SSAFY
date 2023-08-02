import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int[][] map=new int[n+1][n+1];
		int[][] dp=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				dp[i][j]=map[i][j]+dp[i][j-1];
			}
		}
		int r1=0,r2=0,y1=0,y2=0,sum=0;
		for(int i=0;i<m;i++) {
			sum=0;
			st=new StringTokenizer(br.readLine());
			r1=Integer.parseInt(st.nextToken());
			y1=Integer.parseInt(st.nextToken());
			r2=Integer.parseInt(st.nextToken());
			y2=Integer.parseInt(st.nextToken());
			for(int j=r1;j<=r2;j++) {
				sum+=dp[j][y2]-dp[j][y1-1];
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
}
