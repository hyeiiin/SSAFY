import java.io.*;
import java.util.*;

public class Solution_1225_김아현 {

	static int[] numArr;
	static int target;

	public static void main(String[] args) throws Exception {
		int test = 10;
		int minus;
		Queue<Integer> queue;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;

		for (int t = 1; t <= 10; t++) {
			queue = new ArrayDeque<>();
			Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			numArr = new int[8];
			for (int i = 0; i < 8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}

			minus = 1;
			target = queue.poll() - minus;

			if (target == 0) {
				queue.offer(0);
			}

			while (target != 0) {
				queue.offer(target);
				minus++;
				target = queue.poll() - minus;

				if (target <= 0) {
					queue.offer(0);
					break;
				}

				if (minus == 5) {
					minus = 0;
				}
			}
			
			sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for (int q : queue) {
				sb.append(q);
				sb.append(" ");
			}
			System.out.println(sb);
		}
	}
}
