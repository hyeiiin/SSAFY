package todo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1225_최지웅 {
	
	static boolean terminated;
	
	static Queue<Integer> q;
	
	static void cycle() {
		int cnt = 1;
		for (int i = 0; i < 5; i++) {
			int x = q.peek();
			q.poll();
			
			x -= cnt;
			cnt++;
			
			if (x <= 0) {
				q.offer(0);
				terminated = true;
				return;
			} else {
				q.offer(x);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		String line;
		int t;
		
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while ((line = br.readLine()) != null) {
			
			q = new ArrayDeque<>();
			
			t = Integer.parseInt(line);
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			terminated = false;
			while (true) {
				cycle();
				if (terminated) break;
			}
			
			sb.append("#").append(t).append(" ");
			while (!q.isEmpty()) {
				int x = q.peek();
				q.poll();
				
				sb.append(x).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
