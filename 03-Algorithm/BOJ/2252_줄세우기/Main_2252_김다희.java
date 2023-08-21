import java.io.*;
import java.util.*;

public class Main{
	static int n,m;
	static int[] indegree=new int[100001];
	static List<Integer>[] v=new ArrayList[100001];
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		for(int i=1;i<=n;i++) {
			v[i]=new ArrayList<>();
		}
		int a,b;
		for (int i = 1; i <= m; i++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			v[a].add(b);
			indegree[b]++;
		}
		topology();
		System.out.println(sb.toString());
	}
	public static void topology() {
		Queue<Integer>queue=new ArrayDeque<>();
		for(int i=1;i<=n;i++) {
			if(indegree[i]==0) queue.add(i);
		}
		for(int i=1;i<=n;i++) {
			int node=queue.poll();
			sb.append(node).append(" ");
			for(int j=0;j<v[node].size();j++) {
				int nextNode=v[node].get(j);
				indegree[nextNode]--;
				if(indegree[nextNode]==0)queue.add(nextNode);
			}
		}
	}
}
