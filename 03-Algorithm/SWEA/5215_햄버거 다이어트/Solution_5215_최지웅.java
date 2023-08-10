package todo.lesson._0810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_최지웅 {
	
	static int N, L;
	static int[] S, K;
	
	static int maxScore;
	
	static void combination(int idx, int score, int cal) {
		if (idx >= N) {
			maxScore = Math.max(maxScore, score);
		} else {
			if (cal + K[idx] <= L) {
				combination(idx + 1, score + S[idx], cal + K[idx]);
			}
			combination(idx + 1, score, cal);
		}
	}

	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			S = new int[N];
			K = new int[N];
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				S[n] = Integer.parseInt(st.nextToken());
				K[n] = Integer.parseInt(st.nextToken());
			}
			
			maxScore = Integer.MIN_VALUE;
			combination(0, 0, 0);
			
			sb.append("#").append(t).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb);
	}

}
