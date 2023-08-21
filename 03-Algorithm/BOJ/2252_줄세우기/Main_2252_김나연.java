package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2252_김나연 {

	static int n,m,a,b;
	static int degree[];
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException {
		Deque<Integer> q = new ArrayDeque<Integer>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
	        list[i] = new ArrayList<>();
	    } 
		degree=new int[n+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
//			list[a][b]=1;
			list[a].add(b);
			degree[b]++;
		}
		
		for(int i=1;i<=n;i++) {
			if(degree[i]==0) {
				sb.append(i).append(" ");
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			 for (int next : list[current]) {
		            degree[next]--;
		            
		            if (degree[next] == 0) {
		                q.offer(next);
		                sb.append(next).append(" ");
		            }
		        }
		}
		
		System.out.println(sb);
	}

}
