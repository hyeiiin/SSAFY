import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_16935_고영훈 {
	static int N;
	static int M;

	static class Range {
		final int startN;
		final int endN;
		final int startM;
		final int endM;

		public Range(int startN, int endN, int startM, int endM) {
			this.startN = startN;
			this.endN = endN;
			this.startM = startM;
			this.endM = endM;
		}
	}

	private static void swapNM() {
		final int temp = N;
		N = M;
		M = temp;
	}

	private static void rotate(final int opcode, final int[][] mat) {
		final Deque<int[]> q = new ArrayDeque<>();
		switch (opcode) {
		case 1:
			// offer <- row
			for (int i = 0; i < N; i++) {
				q.offer(mat[i]);
			}
			// pollLast -> row
			for (int i = 0; i < N; i++) {
				mat[i] = q.pollLast();
			}
			break;
		case 2:
			// offer <- col
			for (int j = 0; j < M; j++) {
				final int[] col = new int[N];
				for (int i = 0; i < N; i++) {
					col[i] = mat[i][j];
				}
				q.offer(col);
			}
			// pollLast -> col
			for (int j = 0; j < M; j++) {
				final int[] col = q.pollLast();
				for (int i = 0; i < N; i++) {
					mat[i][j] = col[i];
				}
			}
			break;
		case 3:
			// offer <- row
			for (int i = 0; i < N; i++) {
				q.offer(mat[i].clone());
			}
			// swap N and M
			swapNM();
			// pollLast -> col
			for (int j = 0; j < M; j++) {
				final int[] col = q.pollLast();
				for (int i = 0; i < N; i++) {
					mat[i][j] = col[i];
				}
			}
			break;
		case 4:
			// offer <- row
			for (int i = 0; i < N; i++) {
				q.offer(mat[i].clone());
			}
			// swap N and M
			swapNM();
			// poll -> col -> reverse
			for (int j = 0; j < M; j++) {
				final int[] col = q.poll();
				for (int i = 0; i < N; i++) {
					mat[N - i - 1][j] = col[i];
				}
			}
			break;
		case 5:
		case 6: {
			final Range r1 = new Range(0, N / 2, 0, M / 2);
			final Range r2 = new Range(0, N / 2, M / 2, M);
			final Range r3 = new Range(N / 2, N, M / 2, M);
			final Range r4 = new Range(N / 2, N, 0, M / 2);
			final Range[] from = { r1, r2, r3, r4 };
			final Range[] to = opcode == 5 ? new Range[] { r2, r3, r4, r1 } : new Range[] { r4, r1, r2, r3 };
			// offer <- subRow
			for (final Range r : from) {
				for (int i = r.startN; i < r.endN; i++) {
					q.offer(Arrays.copyOfRange(mat[i], r.startM, r.endM));
				}
			}
			// poll -> subRow
			for (final Range r : to) {
				for (int i = r.startN; i < r.endN; i++) {
					final int[] row = q.poll();
					for (int j = r.startM; j < r.endM; j++) {
						mat[i][j] = row[j - r.startM];
					}
				}
			}
		}
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		final int R = Integer.parseInt(st.nextToken());
		// init
		final int max = Math.max(N, M);
		final int[][] mat = new int[max][max];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// rotate
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
			final int opcode = Integer.parseInt(st.nextToken());
			rotate(opcode, mat);
		}
		// print
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(mat[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
