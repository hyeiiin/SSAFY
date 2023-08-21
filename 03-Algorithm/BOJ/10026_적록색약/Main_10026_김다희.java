import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int[] indegree=new int[100001];
	static char[][] map;
	static boolean[][] visited;
	static int[][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		int answer01=0,answer02=0;
		String str;
		map=new char[n][n];
		for(int i=0;i<n;i++) {
			str=br.readLine();
			for(int j=0;j<n;j++) {
				map[i][j]=str.charAt(j);
			}
		}
		visited=new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]) {
					dfs(i,j);
					answer01++;
				}
			}
		}
		visited=new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]) {
					dfs2(i,j);
					answer02++;
				}
			}
		}
		System.out.println(answer01+" "+answer02);
	}
	public static void dfs(int r,int c) {
		Deque<int[]>stack=new ArrayDeque<>();
		stack.add(new int[] {r,c});
		visited[r][c]=true;
		int nr,nc;
		int[] curr;
		while(!stack.isEmpty()) {
			curr=stack.pollLast();
			for(int i=0;i<4;i++) {
				nr=curr[0]+dir[i][0];
				nc=curr[1]+dir[i][1];
				if(nr>=0&&nr<n&&nc>=0&&nc<n) {
					if(!visited[nr][nc]&&map[nr][nc]==map[r][c]) {
						visited[nr][nc]=true;
						stack.addLast(new int[] {nr,nc});
					}
				}
			}
		}
	}
	public static void dfs2(int r,int c) {
		Deque<int[]>stack=new ArrayDeque<>();
		stack.add(new int[] {r,c});
		visited[r][c]=true;
		int nr,nc;
		int[] curr;
		while(!stack.isEmpty()) {
			curr=stack.pollLast();
			for(int i=0;i<4;i++) {
				nr=curr[0]+dir[i][0];
				nc=curr[1]+dir[i][1];
				if(nr>=0&&nr<n&&nc>=0&&nc<n) {
					if(!visited[nr][nc]) {
						if((map[r][c]=='B'&&map[nr][nc]=='B')||((map[r][c]=='R'||map[r][c]=='G')&&(map[nr][nc]=='R'||map[nr][nc]=='G'))) {
							visited[nr][nc]=true;
							stack.addLast(new int[] {nr,nc});
						}
					}
				}
			}
		}
	}
}
