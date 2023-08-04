import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	static char isPair[];
	static int answer = 1, ANSWER, N;
	static Stack<Integer> stack = new Stack<>();
	static String input;
	/*
	 * 1. 문자열을 입력받는다
	 * 2. 배열에 옮겨 담는다
	 * 3. stack 배열을 선언한다.
	 * 		import java.util.Stack;
	 * 		Stack<Integer> stack = new Stacl<>();
	 * 		stack.push(n) : stack에 n추가
	 * 		stack.pop() : stack 값 제거
	 * 		stack.peek() : stack의 가장 상단값 출력
	 * 		stack.size() : 크기 출력
	 * 		stack.empty : 비어있으면 true 출력
	 * 		stack.contains(n) : n이 스택 안에 있는지 (있으면 true)
	 * 4. 배열의 길이만큼 반복하며 stack에 집어 넣는다.
	 * 		* 넣기 전에 스택 안에 마지막 원소랑 비교한다 (peek 페어는 arr[i]?) / 페어 검사법은 유니코드로 최대 2 차이남 (절대값 : NUM = Math.abs(value))
	 * 		pair면 pop / 아니면 push
	 * 5. for문이 끝났을 때 stack.size > 0 or (!stack.empty) 일 경우, answer 0
	 */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 테스트케이스 10번
		for (int tc=1; tc<11; tc++) {
			
			// 입력 및 초기화
			N = Integer.parseInt(br.readLine());
			input = br.readLine();
			isPair = input.toCharArray();
			stack = new Stack<>();
			
			for (int i = 0; i < N; i++) {
				// stack이 비었음 or 스택최상단의 값과 넣으려는 값의 아스키코드 값 차이가 2보다 클 때(=페어가 아닐때)
				if (stack.empty()) {
					stack.push((int) isPair[i]);
					continue;
				}
				
				// 페어가 아닐 때, 새로운 괄호 PUSH
				if (Math.abs(((int)isPair[i] - (int) stack.peek())) > 2) {
					stack.push((int) isPair[i]);
				}
				// 페어라면 기존 괄호 POP
				else {
					stack.pop();
				}
			}
			
			if (stack.empty()) ANSWER = 1;
			else ANSWER = 0;
			
			// 출력
			System.out.printf("#%d %d\n", tc, ANSWER);
		
		}
	}
}
