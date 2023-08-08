import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_고영훈 {
	static int N;
	static int M;
	static int START;

	private static void rotate(final int[][] mat, final int[][] newMat) {
		for (int start = 0; start < START; start++) {
			final int subN = N - start;
			final int subM = M - start;
			int X, Y;
			// 세로 왼
			X = start;
			for (int y = start; y < subN - 1; y++) {
				newMat[y + 1][X] = mat[y][X];
			}
			// 가로 아래
			Y = subN - 1;
			for (int x = start; x < subM - 1; x++) {
				newMat[Y][x + 1] = mat[Y][x];
			}
			// 세로 오른
			X = subM - 1;
			for (int y = start + 1; y < subN; y++) {
				newMat[y - 1][X] = mat[y][X];
			}
			// 가로 위
			Y = start;
			for (int x = start + 1; x < subM; x++) {
				newMat[Y][x - 1] = mat[Y][x];
			}
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		START = Math.min(N, M) / 2;
		final int R = Integer.parseInt(st.nextToken());

		int[][] mat = new int[N][M];
		int[][] newMat = new int[N][M];
		for (int i = 0; i < N; i++) {
			final int[] row = mat[i];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				row[j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int r = 0; r < R; r++) {
			rotate(mat, newMat);
			int[][] temp = mat;
			mat = newMat;
			newMat = temp;
		}
		for (final int[] row : mat) {
			for (final int x : row) {
				sb.append(x).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
