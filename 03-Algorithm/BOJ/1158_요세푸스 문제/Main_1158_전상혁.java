package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

import java.util.Queue;

public class Main_1158_전상혁 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> que = new LinkedList<Integer>();
		
		for (int i = 1; i <= N; i++) {
			que.add(i);
		}
		
		sb.append("<");
		while(!que.isEmpty()) {
			int num = K-1;
			while(num!=0) {
				que.add(que.poll());
				num--;
			}
			
			sb.append(que.poll());
			if (!que.isEmpty()) {
				sb.append(", ");
			}
			
		}
		sb.append(">");
		
		System.out.println(sb);
		
	}

}
