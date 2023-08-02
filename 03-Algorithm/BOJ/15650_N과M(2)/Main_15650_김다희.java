import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb=new StringBuilder();
	static boolean[] visited;
	static int[] arr;
	static int n,m;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		visited=new boolean[n+1];
		dfs(1,0);
		System.out.println(sb.toString());
	}
	static void dfs(int start,int level){
		if(level==m) {
			for(int i=1;i<=n;i++) {
				if(visited[i])sb.append(i).append(" ");
			}sb.append("\n");
			return;
		}
		for(int i=start;i<=n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(i+1,level+1);
				visited[i]=false;
			}
		}
	}
}
