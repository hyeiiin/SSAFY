package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBJKA6qr2oDFAWr
 * 
 * @author SSAFY
 *
 */
public class Solution_3289_박정인 {
	static int[] parents;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parents = new int[N + 1];

			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (op == 0) {	// union 
					union(a, b);
				} else {	// find
					sb.append(find(a) == find(b) ? 1 : 0);
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int find(int x) {
		if (x == parents[x])	return x;
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a < b)	parents[b] = a;
		else parents[a] = b;
	}
}
