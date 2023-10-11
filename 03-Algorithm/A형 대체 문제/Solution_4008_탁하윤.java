package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 4008. [모의 SW 역량테스트] 숫자 만들기
 */
public class Solution_4008_탁하윤 {
	static int N, oper[], nums[], max, min;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());	// 숫자 개수
			
			oper = new int[4];	// 연산자 + - * / 개수
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				oper[i] = Integer.parseInt(st.nextToken());
			}
			
			nums = new int[N];	// 숫자 정보
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			
			subs(1, nums[0]);	// 연산하기
			
			sb.append("#").append(tc).append(" ").append(max-min).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void subs(int cnt, int sum) {
		if(cnt == N) {	// 숫자 모두 계산했다면 종료
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		if(oper[0] > 0) {
			oper[0]--;
			subs(cnt+1, sum+nums[cnt]);
			oper[0]++;
		}
		if(oper[1] > 0) {
			oper[1]--;
			subs(cnt+1, sum-nums[cnt]);
			oper[1]++;
		}
		if(oper[2] > 0) {
			oper[2]--;
			subs(cnt+1, sum*nums[cnt]);
			oper[2]++;
		}
		if(oper[3] > 0) {
			oper[3]--;
			subs(cnt+1, sum/nums[cnt]);
			oper[3]++;
		}
		
	}

}
