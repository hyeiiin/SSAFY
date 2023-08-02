import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_11659_고영훈 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());

		final int[] arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
		}

		for (int x = 0; x < M; x++) {
			st = new StringTokenizer(br.readLine());
			final int start = Integer.parseInt(st.nextToken()) - 1;
			final int end = Integer.parseInt(st.nextToken());
			final int sum = arr[end] - arr[start];
			bw.write(sum + "\n");
		}
		bw.flush();
	}
}
