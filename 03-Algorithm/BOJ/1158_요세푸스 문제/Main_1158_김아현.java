import java.util.*;

public class Main_1158_김아현 {

	private static Queue<Integer> queue;
	public static void main(String[] args) {
		queue = new LinkedList<>();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		// queue에 1부터 n까지 넣기
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		// queue가 빌 때까지
		while (!queue.isEmpty()) {
			// 앞에서 뺀 값 뒤로 추가.
			for (int i = 0; i < k - 1; i++) {
				queue.offer(queue.peek());
				queue.poll();
			}
			
			// k번째 값일 경우 queue에서 poll
			if (queue.size() == 1) {
				sb.append(queue.peek());
			} else {
				sb.append(queue.peek()).append(", ");
			}
			queue.poll();
		}

		sb.append(">");
		System.out.println(sb.toString());

		sc.close();
	}

}
