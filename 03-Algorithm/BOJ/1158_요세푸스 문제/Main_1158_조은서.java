import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1158_조은서 {

	static int n;
	static int k;
	static Deque<Integer> stack;
	static int[] answer;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		stack = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			stack.addLast(i+1);
		}

		cnt = 0;
		answer = new int[n];
		
		while(stack.size() > 0) {
			for(int i=0; i<k-1; i++) {
				stack.addLast(stack.removeFirst());
			}
			answer[cnt] = stack.removeFirst();
			cnt++;
		}
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int i=0; i<n-1; i++) {
			sb.append(answer[i]).append(", ");
		}
		sb.append(answer[n-1]).append(">");
		
		System.out.println(sb);
	}

}