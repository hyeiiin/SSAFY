package baekjoon;
import java.util.*;
import java.io.*;
public class Main_1697_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int n, k, len, count;
	static boolean visit[];
	static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		visit = new boolean[k*2];
	}
	
	static void solve() {
		if(k>n) {
			count = bfs();
		}
		else {
			count = n-k;
		}
			
		System.out.println(count);
	}
	
	static int bfs() {
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		list.add(n);
		while(true) {
			ArrayList<Integer> newlist = new ArrayList<>();
			for (int x : list) {
				if(x == k) return cnt;
				if(visit[x]) continue;
				
				visit[x] = true;
				if(x < k && x*2 < k*2) {
					newlist.add(x * 2);
					newlist.add(x + 1);
				}
				if(x > 0) {
					newlist.add(x - 1);
				}
			}
			cnt++;
			list = newlist;
		}
	}

	public static void main(String[] args) throws IOException{
		init();
		solve();
	}

}
