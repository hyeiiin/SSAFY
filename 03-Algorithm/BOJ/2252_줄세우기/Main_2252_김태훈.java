package baekjoon;
import java.util.*;
import java.io.*;
public class Main_2252_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	static int n, m;
	static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	static Queue<Integer> que = new ArrayDeque<>();
	static int[] edge;
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		edge = new int[n+1];
		for (int i = 0; i <= n; i++) {
			map.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map.get(a).add(b);
			edge[b]++;
		}
	}
	
	static void solve() {
		for (int i = 1; i <= n; i++) {
			if(edge[i]==0) {
				que.offer(i);
			}
		}
		while(!que.isEmpty()) {
			int nodeNo = que.poll();
			sb.append(nodeNo).append(" ");
			List<Integer> list = map.get(nodeNo);
			for (int i = 0; i < list.size(); i++) {
				edge[list.get(i)]--;
				if(edge[list.get(i)] == 0)
					que.offer(list.get(i));
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException{
		init();
		solve();
	}

}
