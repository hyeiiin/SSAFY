package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_박정인 {
	static int N, M;	
	static boolean[] visited;	// 방문체크할 배열
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[] output = new int[M];	// 출력할 내용들을 보관할 배열
		visited = new boolean[N];
		perm(output, 0);
		System.out.println(sb);
	}

	private static void perm(int[] output, int depth) {
		if (depth == M) {	// M번 반복했을 경우 
			for (int o : output) {
				sb.append(o).append(" ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				output[depth] = i + 1;
				visited[i] = true;
				perm(output, depth + 1);
				visited[i] = false;
			}
		}
	}
}
