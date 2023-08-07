package baekjoon;
import java.util.*;
import java.io.*;
public class Main_1158_김태훈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int count = 0;
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for(int i=1; i<=n; i++) {
			que.offerLast(i);
		}
		sb.append("<");
		while(!que.isEmpty()) {
			for(int j=1; j<k; j++) {
				int tmep = que.pollFirst();
				que.offerLast(tmep);
			}
			sb.append(que.pollFirst()).append(", ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append(">");
		System.out.println(sb);
	}

}
