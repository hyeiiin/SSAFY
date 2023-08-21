package todo.lesson._0821;

import java.util.*;

public class Main_1697_최지웅 {

	static Scanner in = new Scanner(System.in);

	static int N, K;
	static int MIN_POS = 0;
	static int MAX_POS = 100000;

	static boolean[] visited = new boolean[MAX_POS + 1];

	static int time;
	static int minTime;

	static boolean valid(int x) {
		return MIN_POS <= x && x <= MAX_POS;
	}

	static class Record {
		int time;
		int pos;

		Record(int time, int pos) {
			this.time = time;
			this.pos = pos;
		}

		@Override
		public String toString() {
			return "Record [time=" + time + ", pos=" + pos + "]";
		}
	}

	static void BFS() {

		Queue<Record> queue = new ArrayDeque<>();

		visited[N] = true;
		queue.offer(new Record(time, N));

		while (!queue.isEmpty()) {
			Record record = queue.poll();
			if (record.pos == K) {
				minTime = record.time;
				return;
			}

			int time = record.time;
			int pos = record.pos;
			if (valid(pos - 1) && !visited[pos - 1]) {
				visited[pos - 1] = true;
				queue.offer(new Record(time + 1, pos - 1));
			}
			if (valid(pos + 1) && !visited[pos + 1]) {
				visited[pos + 1] = true;
				queue.offer(new Record(time + 1, pos + 1));
			}
			if (valid(2 * pos) && !visited[2 * pos]) {
				visited[2 * pos] = true;
				queue.offer(new Record(time + 1, 2 * pos));
			}
		}
	}

	public static void main(String[] args) {

		N = in.nextInt();
		K = in.nextInt();

		BFS();
		System.out.println(minTime);
	}

}
