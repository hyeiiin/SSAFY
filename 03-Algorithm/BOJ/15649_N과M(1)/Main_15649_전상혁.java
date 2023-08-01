package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_전상혁 {
	static boolean[] isSelected; 
	static int[] nums;
	static int N;
	static int M;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		//1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

		isSelected = new boolean[N+1];
		nums = new int[M+1];	
		
		per(0);
		System.out.println(sb);
		
	}
	private static void per(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(nums[i]).append(" ");
//				System.out.print(nums[i] + " ");
			}
//			System.out.println();
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				nums[cnt] = i;
				per(cnt+1);
				isSelected[i] = false;
			}
		}
	}
		
}
