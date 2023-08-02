import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11660_고영훈 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());

		final int[][] arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) + arr[i][j - 1];
			}
			for (int j = 1; j <= N; j++) {
				arr[i][j] += arr[i - 1][j];
			}
		}

		for (int x = 0; x < M; x++) {
			st = new StringTokenizer(br.readLine());
			final int x1 = Integer.parseInt(st.nextToken()) - 1;
			final int y1 = Integer.parseInt(st.nextToken()) - 1;
			final int x2 = Integer.parseInt(st.nextToken());
			final int y2 = Integer.parseInt(st.nextToken());
			final int sum = arr[x2][y2] - arr[x2][y1] - arr[x1][y2] + arr[x1][y1];
			bw.write(sum + "\n");
		}
		bw.flush();
	}
}
