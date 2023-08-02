package lesson.boj_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15650_최지웅 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb; 

	static int N, M;
	
	static boolean[] isSelected;
	static int[] seq;

	static void permutation(int idx, int start)
	{
		if (idx == M) {
			for (int i = 1; i <= M; i++) {
				sb.append(seq[i]).append(" ");
			}
			sb.append("\n");
		} else {
			for (int n = start; n <= N; n++) {
				isSelected[n] = true;
				seq[idx + 1] = n;
				permutation(idx + 1, n + 1);
				isSelected[n] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N + 1];
		seq = new int[M + 1];
		permutation(0, 1);
		
		System.out.println(sb.toString());
	}

}
