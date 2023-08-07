package ssafyPractice;

import java.io.*;
import java.util.*;

public class Main_1158_이세은 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<Integer>();

		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=n; i++)
			q.add(i);
		
		sb.append("<");
		int cnt = 1;
		while (!q.isEmpty()) {
			if(q.size() == 1) {
				sb.append(q.poll());
				break;
			}
			if (cnt == k) {
				sb.append(q.poll()+", ");
				cnt = 0;
			}
			else{
				q.add(q.poll());
			}
			
			cnt++;
		}
		sb.append(">");

		System.out.println(sb.toString());
	}

}
