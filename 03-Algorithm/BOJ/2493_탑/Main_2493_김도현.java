package baekjun;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_김도현 {

	public static void main(String[] args) throws Exception, IOException {
		Stack<int[]> stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= n; i++) {
			int v = Integer.parseInt(st.nextToken());
			while (!stack.isEmpty()) {
				if (stack.peek()[1] >= v) {
					System.out.print(stack.peek()[0] + " ");
					break;
				}
				stack.pop();
			}
			if (stack.isEmpty()) {
				System.out.print("0 ");
			}
			stack.push(new int[] { i, v });
		}
	}

}
