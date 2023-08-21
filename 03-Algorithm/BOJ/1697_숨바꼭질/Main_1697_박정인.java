package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1697
 * @author SSAFY
 *
 */
public class Main_1697_박정인 {
	static int N, K;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[100001];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(N);
		arr[N] = 1;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			if (now == K) {				
				break;
			}
			
			if (now - 1 >= 0 && arr[now - 1] == 0) {
				arr[now - 1] = arr[now] + 1;	
				q.offer(now - 1);
			}
			
			if (now + 1 <= 100000 && arr[now + 1] == 0) {
				arr[now + 1] = arr[now] + 1;
				q.offer(now + 1);
			} 
			
			if (2 * now <= 100000 && arr[2*now] == 0) {
				arr[2 * now] = arr[now] + 1;
				q.offer(2 * now);
			}
		}
		
		System.out.println(arr[K] - 1);
	}
}
