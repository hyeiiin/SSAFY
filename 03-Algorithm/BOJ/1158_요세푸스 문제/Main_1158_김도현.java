package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main_1158_김도현 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		ArrayDeque<Integer> list = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			list.addLast(i);
		}
		StringBuilder sb = new StringBuilder();
		while(list.size()!=1) {
			for (int i = 0; i < K-1; i++) {
				list.addLast(list.pollFirst());
			}
			sb.append(list.pollFirst()+", ");
		}
		sb.append(list.poll());
		
		System.out.print("<");
		System.out.print(sb.toString());
		System.out.println(">");
		
	}

}
