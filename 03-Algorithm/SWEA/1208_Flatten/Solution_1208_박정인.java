package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV139KOaABgCFAYh
 * Flatten
 * 
 * @author SSAFY
 *
 */
public class Solution_1208_박정인 {
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();				
		
		for (int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[100];			
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				int h = Integer.parseInt(st.nextToken());
				arr[i] = h;
			}
			
			Arrays.sort(arr);
			
			int result = -1;
			while (N != 0) {
				if (arr[99] - arr[0] <= 1) {
					result = arr[99] - arr[0];
					break;
				}
				arr[99]--;
				arr[0]++;
				N--;
				Arrays.sort(arr);
			}
			
			result = result == -1 ? arr[99] - arr[0] : result;
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	/**
	 * PriorityQueue 이용 
	 * 배열 정렬보다 느림
	 * 233ms
	 */
//	static int N;
//	static int[] arr;
//	public static void main(String[] args) throws Exception {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = null;
//		StringBuilder sb = new StringBuilder();				
//		
//		for (int tc = 1; tc <= 10; tc++) {
//			N = Integer.parseInt(br.readLine());
//			arr = new int[100];
//			
//			PriorityQueue<Integer> maxQ = new PriorityQueue<>((n1, n2) -> Integer.compare(n2,  n1));
//			PriorityQueue<Integer> minQ = new PriorityQueue<>();
//			
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < 100; i++) {
//				int h = Integer.parseInt(st.nextToken());
//				maxQ.offer(h);
//				minQ.offer(h);
//			}
//			
//			int max = 0;
//			int min = 0;
//			int result = -1;
//			while (N != 0) {
//				max = maxQ.poll();
//				min = minQ.poll();
//				
//				if (max - min <= 1) {
//					result = max - min;
//					break;
//				}
//				
//				maxQ.remove(min);
//				minQ.remove(max);								
//				
//				max--;
//				min++;		
//				maxQ.offer(max);
//				minQ.offer(max);
//				maxQ.offer(min);
//				minQ.offer(min);
//				N--;
//			}
//			
//			result = result == -1 ? maxQ.poll() - minQ.poll() : result;
//			
//			sb.append("#").append(tc).append(" ").append(result).append("\n");
//		}
//		
//		System.out.println(sb);
//	}
}
