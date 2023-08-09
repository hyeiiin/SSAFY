import java.util.*;
import java.io.*;

class Solution{
	private static int[][] arr;
	private static int n;
	public static void main(String args[]) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int T=Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++){
			sb.append("#").append(test_case).append(" ");
			n=Integer.parseInt(br.readLine());
			int answer=0,tmp;
			Pair answerPos=null;
			arr=new int[n][n];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					tmp=bfs(i,j);
					if(tmp>answer) {
						answer=tmp;
						answerPos=new Pair(i,j);
					}else if(tmp==answer) {
						if(arr[answerPos.first][answerPos.second]>arr[i][j]) {
							answerPos=new Pair(i,j);
						}
					}
				}
			}
			sb.append(arr[answerPos.first][answerPos.second]).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	public static class Pair{
		int first;
		int second;
		public Pair(int first,int second) {
			this.first=first;
			this.second=second;
		}
		
	}
	public static int bfs(int startR,int startC) {
		Queue<Pair>q=new ArrayDeque<>();
		int count=0,nextR,nextC;
		int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
		Pair curr;
		boolean[][] visited=new boolean[n][n];
		q.add(new Pair(startR,startC));
		while(!q.isEmpty()) {
			curr=q.poll();
			count++;
			for(int i=0;i<4;i++) {
				nextR=curr.first+dir[i][0];
				nextC=curr.second+dir[i][1];
				if(isArray(nextR,nextC)&&!visited[nextR][nextC]&&arr[nextR][nextC]==arr[curr.first][curr.second]+1) {
					q.add(new Pair(nextR,nextC));
				}
			}
		}
		return count;
	}
	private static boolean isArray(int r,int c) {
		return r>=0&&r<n&&c>=0&&c<n;
	}
}
