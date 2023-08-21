import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_1697_고영훈 {
	final static Scanner sc = new Scanner(System.in);
	final static int N = sc.nextInt();
	final static int K = sc.nextInt();
	final static boolean[] visited = new boolean[K * 2 + 1];

	private static int bfs() {
		int count = 0;
		List<Integer> list = new ArrayList<>();
		list.add(N);
		while (true) {
			final List<Integer> newList = new ArrayList<>();
			for (final int x : list) {
				if (x == K) {
					return count;
				}
				if (visited[x]) {
					continue;
				}
				visited[x] = true;
				if (x < K) {
					newList.add(x * 2);
					newList.add(x + 1);
				}
				if (x > 0) {
					newList.add(x - 1);
				}
			}
			count++;
			list = newList;
		}
	}

	public static void main(String[] args) {
		System.out.println(N > K ? N - K : bfs());
	}
}
