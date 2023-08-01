package lesson.boj_ans.permutation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_최지웅 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static String answer;
	
	static int N;
	static int M;
	
	static int[] numbers;
	static boolean[] isSelected;
	
	static void permutation(int cnt) {
		if (cnt == M) {
			sb = new StringBuilder();
			for (int m = 1; m <= M; m++) {
				sb.append(numbers[m] + " ");
			}
			sb.append("\n");
			return;
		} else {
			for (int n = 1; n <= N; n++) {
				if (isSelected[n]) continue;
				numbers[cnt + 1] = n;
				isSelected[n] = true;
				permutation(cnt + 1);
				isSelected[n] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N + 1];
		isSelected = new boolean[N + 1];
		
		permutation(0);
		
		System.out.println(sb.toString());
	}
}
