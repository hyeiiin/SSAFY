package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16435
 * @author SSAFY
 *
 */
public class Main_16435_박정인 {
	static int N, L, arr[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 가장 낮은 높이의 과일부터 확인하기 위한 오름차순 정렬
		Arrays.sort(arr);
		
		// 현재 과일이 확인 안한 과일들 중 가장 낮은 높이이다. 
		// 따라서 현재 과일이 길이보다 높은 경우 종료
		for (int h : arr) {
			if (h > L) {
				break;
			}
			
			L++;
		}
		
		System.out.println(L);
	}
}
