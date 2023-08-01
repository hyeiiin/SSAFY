import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int TC = 10;
	static int dump;
	static int size = 100;
	static int[] lst;
	static int[] cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		StringTokenizer st;
//		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc < TC + 1; tc++) { ////////// TC
 
			st = new StringTokenizer(br.readLine());
			dump = Integer.parseInt(st.nextToken()); // 1 <= N <= 1000

			st = new StringTokenizer(br.readLine());
			lst = new int[size]; // 가로 길이는 항상 100으로 주어진다.
			for (int i = 0; i < size; i++) {
				lst[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(lst);
			
			int left = 0 ;
			int right = size - 1;
			int res = 0;
			
			while(dump > 0 ) {
				lst[left] += 1;  // 최저점 + 1
				lst[right] -= 1;  // 최고점 - 1
				Arrays.sort(lst);  // 높이순 재정렬
				res = lst[right] - lst[left];  // 최고점 - 최저점 갱신
				
				// 주어진 덤프 횟수 이내에 평탄화 완료 = break
				if (res == 0 || res == 1) {
					break;
				}
				
				dump --;
			}
			System.out.println("#" + tc + " " + res);
		} ////////// TC
	}
}
