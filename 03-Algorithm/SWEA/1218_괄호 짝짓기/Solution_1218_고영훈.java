import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_1218_고영훈 {
	private static int solution(final String s) {
		final Stack<Character> stack = new Stack<>();
		int correct = 1;

		for (final char c : s.toCharArray()) {
			if (c == '(' || c == '[' || c == '{' || c == '<') {
				stack.add(c);
				continue;
			}
			if (stack.isEmpty()) {
				correct = 0;
				break;
			}
			final char p = stack.pop();
			if (p == '(' && c != ')' || p == '[' && c != ']' || p == '{' && c != '}' || p == '<' && c != '>') {
				correct = 0;
				break;
			}
		}
		return correct;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		final int T = 10;
		for (int t = 1; t <= T; t++) {
			br.readLine();
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			sb.append(solution(br.readLine()));
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
