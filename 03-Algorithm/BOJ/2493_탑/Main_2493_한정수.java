import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main_2493_한정수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> count_stack = new Stack<>();
//		Stack<Integer> print_stack = new Stack<>();
		
		// ==========입력 끝
		
		//최초 temp = pop  count = 1
		//=========
		
		//비어있으면 그냥 push.
		//안비어있으면 들어있는애랑 비교 해서 input(temp가)이 작은가?
		//  >>temp가 작으면 원래 있던애 번호 출력하고 push
		//  >>temp가 크면 원래 스택에 본인보다 큰 애가 나올때까지 pop.

		
		int count = 1;
		int temp_pop = 0;
		int len = T-1;
		
		for (int i=1; i<=T ; i++) {
			int temp = Integer.parseInt(st.nextToken());
			if(stack.isEmpty()) {
//				print_stack.push(0);
				System.out.print(0+" ");
				stack.push(temp);
				count_stack.push(count);
			}
			else {
				while (true) {
					if (stack.isEmpty()) {
//						print_stack.push(0);
						System.out.print(0+" ");
						stack.push(temp);
						count_stack.push(count);
						break;
					}
					if (stack.peek() >= temp) {
//						print_stack.push(count_stack.peek());
						System.out.print(count_stack.peek()+" ");
						stack.push(temp);
						count_stack.push(count);
						break;
					}
					else {
						// temp가 더 크면. 
						stack.pop();
						count_stack.pop();
						
					}
					
				}
				
			}
			count += 1;
			
			
			
		}
		
		
	}

}
