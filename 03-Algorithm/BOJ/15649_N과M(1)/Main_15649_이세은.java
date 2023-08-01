package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15649_이세은 {

	private static List<int[]> rslt;
	private static int[] nums;
	private static boolean[] visited;
	private static int m, n, pre;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 1부터 n까지 자연수
		m = Integer.parseInt(st.nextToken()); // 길이 m

		rslt = new ArrayList<int[]>();
		visited = new boolean[n + 1];
		nums = new int[m];

		makeNumberList(0);

		StringBuilder sb = new StringBuilder();

		for (int[] numbers : rslt) {
			for (int i = 0; i < numbers.length; i++) {
				sb.append(numbers[i]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();

	}

	public static void makeNumberList(int cnt) {

		if (cnt == m) {
			// 생성 완료
			int[] arr = Arrays.copyOf(nums, nums.length);
			rslt.add(arr);
			return;
		}
		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			nums[cnt] = i;
			visited[i] = true;
			makeNumberList(cnt + 1);

			visited[i] = false; // 이후에 다른 셋에 또 숫자 적용 위해서 false로
		}
	}

}
