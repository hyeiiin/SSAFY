import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_11286_고영훈 {
	static class Pair {
		int value;
		int abs;

		public Pair(int value) {
			this.value = value;
			this.abs = Math.abs(value);
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = Integer.parseInt(br.readLine());
		final Queue<Pair> heap = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair a, Pair b) {
				return a.abs != b.abs ? a.abs - b.abs : a.value - b.value;
			}
		});
		for (int i = 0; i < N; i++) {
			final int x = Integer.parseInt(br.readLine());
			if (x != 0) {
				heap.offer(new Pair(x));
			} else {
				final Pair pair = heap.poll();
				sb.append(pair != null ? pair.value : 0).append("\n");
			}
		}
		System.out.println(sb);
	}
}
