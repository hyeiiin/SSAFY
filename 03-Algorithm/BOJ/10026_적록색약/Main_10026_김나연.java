package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10026_김나연 {
	
	static int n, cnt, res1, res2;
	static boolean visited[][];
	static char a[][];
	static int dy[]= {-1,0,1,0}, dx[]= {0,1,0,-1};
	
	public static void go(int y, int x, char color) {
		visited[y][x]=true;
		
		for(int i=0;i<4;i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			
			if(ny<0||nx<0||ny>=n||nx>=n)continue;
			if(visited[ny][nx]==true) continue;
			
			if(a[ny][nx]==color) {
				go(ny, nx, color);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		
		a=new char[n][n];
		visited = new boolean[n][n];
		
		for (int i = 0; i < n; i++) {
			String str=br.readLine();
			for (int j = 0; j < n; j++) {
				a[i][j]=str.charAt(j);
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i<0||j<0||i>=n||j>=n)continue;
				if(visited[i][j]==true) continue;
				
				go(i, j, a[i][j]);
				cnt++;
			}
		}
		
		sb.append(cnt).append(" ");
		
		// 적록색약
		cnt=0;
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(a[i][j]=='G')a[i][j]='R';
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(i<0||j<0||i>=n||j>=n)continue;
				if(visited[i][j]==true) continue;
				
				go(i, j, a[i][j]);
				cnt++;
			}
		}
		
		sb.append(cnt);
		
		System.out.println(sb);
	}

}
