package swea;
import java.util.*;
import java.io.*;
public class Solution_3289_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int T, n ,m, commend, parents[];
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	}
	
	static void solve() throws IOException{
	
		makeSet(n);
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			commend = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(commend == 0) {
				union(a, b);
			}
			else if(commend == 1) {
				int nA = find(a);
				int nB = find(b);
				if(nA == nB) sb.append(1);
				else sb.append(0);
			}
		}
	}
	
	static void makeSet(int size) {
		parents = new int[n+1];
		for (int i = 0; i < n+1; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int x) {
		if(parents[x] == x)
			return x;
		return parents[x] = find(parents[x]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a > b) parents[a] = b;
		else parents[b] = a;
	}
	
	public static void main(String[] args) throws IOException{
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			sb.append("#").append(t+1).append(" ");
			init();
			solve();
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
