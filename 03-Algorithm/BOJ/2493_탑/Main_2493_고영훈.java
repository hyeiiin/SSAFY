import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_고영훈 {
	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(br.readLine());
		final int[] heights = new int[N];
		final Stack<Integer> stack = new Stack<>();
		final StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			final int h = Integer.parseInt(st.nextToken());
			heights[i] = h;
			while (!stack.isEmpty() && heights[stack.peek()] < h) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				sb.append(0);
				sb.append(" ");
			} else {
				sb.append(stack.peek() + 1);
				sb.append(" ");
			}
			stack.push(i);
		}
		sb.append("\n");
		System.out.println(sb);
	}
}
