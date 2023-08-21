import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int arrow[];
	static List<Integer> li[];
	static Queue<Integer> q = new ArrayDeque<>();
	
	static void ss() {
		for(int i = 1; i <= n; i++) {
			if(arrow[i] == 0) q.add(i);
		}
		while(q.size() != 0) {
			sb.append(q.peek()).append(" ");
			int now = q.poll();
			for(int i = 0; i < li[now].size(); i++) {
				int next = li[now].get(i);
				arrow[next]--;
				if(arrow[next] == 0) {
					q.add(next);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		li = new ArrayList[n+1];
		arrow = new int[n+1];
		for(int i = 1; i <= n; i++) {
			li[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int la = Integer.parseInt(st.nextToken());
			int le = Integer.parseInt(st.nextToken());
			li[la].add(le);
			arrow[le]++;
		}
		
		ss();
		
		System.out.println(sb.toString());
	}

}