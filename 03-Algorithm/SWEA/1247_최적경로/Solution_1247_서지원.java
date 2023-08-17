package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_1247_서지원 {
	
	static int N, result;
	static int[] home, company, visited;
	static List<int[]> customer;
	
	// 두 위치의 거리 구하기
	private static int getDistance(int[] a, int[] b) {
		return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
	}
	
	// 고객 집 방문하면서 거리 구하기
	// k : 깊이, idx : 이전 인덱스, distance : 현재까지의 거리
	private static void dfs(int k, int idx, int distance) {
		// 현재 result의 값보다 distance가 큰 경우 더 이상 볼 필요없다.
		if (result < distance) return;
		
		if (k == N - 1) {
			result = Math.min(result, distance + getDistance(customer.get(idx), home));
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i] == 1) continue;
			visited[i] = 1;
			// 깊이, 현재 인덱스, 거리 + 이전 인덱스와 현재 인덱스와 거리
			dfs(k + 1, i, distance + getDistance(customer.get(i), customer.get(idx)));
			visited[i] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			
			int x, y;
			// 회사 좌표
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			company = new int[] {x, y};
			
			// 집의 좌표
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new int[] {x, y};
			
			// N명의 고객의 좌표
			customer = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customer.add(new int[] {x, y});
			}
			
			visited = new int[N];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				visited[i] = 1;
				// 깊이, 인덱스, 회사와 첫 번째로 방문하는 고객의 거리
				dfs(0, i, getDistance(company, customer.get(i)));
				visited[i] = 0;
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}
