package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2961
 * 도영이가 만든 맛있는 음식
 * @author SSAFY
 *
 */
public class Main_2961_박정인 {	
	static int[][] arr;
	static boolean[] visited;
	static long min = Long.MAX_VALUE;
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][2];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
						
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		create(0, 0);
		System.out.println(min);
	}
	
	private static void create(int depth, int cnt) {
		if (depth == N) {
			if (cnt == 0)	return;
			
			long s = 1;
			long b = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					s *= arr[i][0];
					b += arr[i][1];
				}
			}
			
			min = Math.min(min,  Math.abs(s - b));
			return;
		}
		

		visited[depth] = true;
		create(depth + 1, cnt + 1);
		visited[depth] = false;
		create(depth + 1, cnt);
	}
}
