import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// BOJ_2493
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1 <= N <= 50_0000
		int N = Integer.parseInt(st.nextToken()); // 탑의 개수
		Stack<Integer> stack = new Stack<>();  // Input : 탑
		Stack<Integer> res = new Stack<>();  // 탑 번호

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (i == 0) {  // 처음 숫자 넣고 시작
				stack.push(num);
				res.push(i);
				sb.append(0).append(" ");
			}

			else {  // 2번째 숫자 ~ 끝까지
				while (!stack.isEmpty()) {
					// 현재 탑이 top보다 크다 -> 레이저 무시됨
					if (stack.peek() < num) {  // 무시되는 탑 제거
						stack.pop();
						res.pop();
					} else {  // 현재 탑이 top보다 작다 -> 레이저 닿음
						// 해당 탑의 번호 출력을 위한 저장
						sb.append(res.peek() + 1).append(" ");
						break;
					}
				}
			}
			
			// 현시점에서 이전 탑이 없음 -> 현재 숫자가 가장 큼 
			if (stack.isEmpty()) {  // 따라서 수신 = 0
				sb.append(0).append(" ");
			}
			
			// 현재 탑을 stack에 쌓음
			stack.push(num);
			res.push(i);
		}

		System.out.println(sb.toString());

	} // Main

}
