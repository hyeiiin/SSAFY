import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15650_고영훈 {
	static int[] arr;
	static int N;
	static int M;

	/**
	 * 조합
	 * 
	 * @param count
	 * @param start
	 */
	private static void combination(int count, int start) {
		if (count == M) {
			System.out.print(Integer.toString(arr[0]));
			for (int i = 1; i < M; i++) {
				System.out.print(" " + arr[i]);
			}
			System.out.println();
			return;
		}
		for (int x = start; x <= N; x++) {
			arr[count] = x;
			combination(count + 1, x + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		combination(0, 1);
	}
}
