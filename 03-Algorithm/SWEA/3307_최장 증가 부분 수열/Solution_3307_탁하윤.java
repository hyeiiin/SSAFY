package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3307_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 개수
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());	// 수열의 길이
			int[] arr = new int[N];	// 수열을 담을 배열
			int[] lis = new int[N];	// lis 배열 (i자리에 올 수 있는 가장 작은 값을 의미)
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {	// 수열 원소 초기화
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			// Arrays.binarySearch(arr, startIndex, endIndex, key);
			// arr: 수열, key: 찾고자 하는 수
			// startIndex: 찾고자 하는 대상 범위의 첫 번째 인덱스, endIndex: 찾고자 하는 대상 범위의 마지막 인덱스+1
			int ret = 0;	// 최장 수열의 길이
			for (int i = 0; i < N; i++) {
				int index = Arrays.binarySearch(lis, 0, ret, arr[i]);
				index = Math.abs(index)-1;	// Arrays.binarySearch로 찾은 위치는 key보다 큰 최초의 위치
				lis[index] = arr[i];	// 작은 값으로 갱신
				if(ret == index) {
					ret++;	// lis에 삽입하는 위치가 맨 마지막라면 수열에 숫자 한 개 추가된 것 -> 길이 갱신
				}
			}
			System.out.printf("#%d %d\n", tc, ret);
		}
	}

}
