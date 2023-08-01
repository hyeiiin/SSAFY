import java.io.*;
import java.util.*;

public class Main {
	static boolean[] visited;
	static int[] comp;
	static int n,m;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		sb=new StringBuilder();
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		visited=new boolean[n+1];
		comp=new int[m];
		findCompute(0);
		System.out.println(sb.toString());
	}
	static void findCompute(int count) {
		if(count==m) {
			for(int i:comp)
				sb.append(i).append(" ");
			sb.append("\n");
			return;
		}
		for(int i=1;i<=n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				comp[count]=i;
				findCompute(count+1);
				visited[i]=false;
			}
		}
	}
}
