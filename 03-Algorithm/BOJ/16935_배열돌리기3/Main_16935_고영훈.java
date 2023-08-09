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
			final int subN = N / 2;
			final int subM = M / 2;
			final Range[] from1234 = {
				new Range(0, subN, 0, subM),
				new Range(0, subN, subM, M),
				new Range(subN, N, subM, M),
				new Range(subN, N, 0, subM),
			};
			final Range[] to2341 = {
				new Range(0, subN, subM, M),
				new Range(subN, N, subM, M),
				new Range(subN, N, 0, subM),
				new Range(0, subN, 0, subM),
			};
			final Range[] to4123 = {
				new Range(subN, N, 0, subM),
				new Range(0, subN, 0, subM),
				new Range(0, subN, subM, M),
				new Range(subN, N, subM, M),
			};
			final Range[] to = opcode == 5 ? to2341 : to4123;
			// offer <- subRow
			for (final Range r : from1234) {
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
