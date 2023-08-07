package todo._0807;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158_최지웅 {

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int n = 1; n <= N; n++) {
			queue.offer(n);
		}
		
		ArrayList<Integer> josephus = new ArrayList<>();
		
		int k = 0;
		while (!queue.isEmpty()) {
			k++;
			if (k == K) {
				josephus.add(queue.poll());
				k = 0;
			} else {
				queue.offer(queue.poll());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		sb.append(josephus.get(0));
		for (int i = 1; i < josephus.size(); i++) {
			sb.append(", ").append(josephus.get(i));
		}
		sb.append(">");
		
		System.out.println(sb);
	}

}
