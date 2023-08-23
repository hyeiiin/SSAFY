package baekjoon;
import java.util.*;
import java.io.*;
public class Main_13023_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int n, m;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static boolean visit[];
	
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visit = new boolean[n];
		for (int i = 0; i < n; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
	}
	
	static boolean dfs(int current, int depth) {
		if(depth == 4) {
			return true;
		}
		visit[current] = true;
		boolean result = false;
		for (int nextNode : list.get(current)) {
			if(!visit[nextNode]) {
				if(dfs(nextNode, depth+1)) {
					result =true;
					break;
				}
			}
		}
		visit[current] = false;
		return result;
	}
	
	static void print() {
		boolean found = false;
		for (int i = 0; i < n; i++) {
			if(dfs(i, 0)) {
				found = true;
				break;
			}
		}
		System.out.println(found ? 1 : 0);
	}
	
	public static void main(String[] args) throws IOException{
		init();
		print();
	}

}
