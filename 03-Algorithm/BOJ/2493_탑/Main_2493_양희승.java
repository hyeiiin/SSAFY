/*
 *	[Python]
  	n = int(input())
	top = list(map(int, input().split()))

	answer = [0 for _ in range(n)]
	stack = []

	for i in range(len(top)):
    while stack:
        if top[stack[-1][0]] < top[i]:
            stack.pop()
        else:
            answer[i] = stack[-1][0] + 1
            break
    stack.append((i, top[i]))

	print(*answer)

 */

package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class G5_2493 {
	
	static int N;
	static int[] ANSWER, TOP;
	static Stack<Integer> stack = new Stack<>();
	static Stack<Integer> stackIdx = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		TOP = new int[N];
		ANSWER = new int[N];
		
		for (int i = 0; i < N; i++) {
			TOP[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			while (stack.size() != 0) {
				if (TOP[stackIdx.peek()] < TOP[i]) {
					stack.pop();
					stackIdx.pop();
				} else {
					ANSWER[i] = stackIdx.peek() + 1;
					break;
				}
			}
			stackIdx.add(i);
			stack.add(TOP[i]);
		}
		
		for (int ans : ANSWER) {
			System.out.print(ans + " ");
		}
		
	}
}
