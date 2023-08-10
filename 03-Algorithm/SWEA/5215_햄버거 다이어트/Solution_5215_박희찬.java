import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution { // SWEA_5215
	static int TC;
	static int N, L;
	static int[][] DP;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc < TC + 1; tc++) { // TC
			StringBuilder sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
			
			// N, L(1 ≤ N ≤ 20, 1 ≤ L ≤ 1_0000)
			N = Integer.parseInt(st.nextToken());  // 재료의 수
			L = Integer.parseInt(st.nextToken());  // 총 제한 칼로리
			
			int[] favorite = new int[N + 1];  // 재료의 선호 점수
			int[] calorie = new int[N + 1];  // 재료의 칼로리
			
			for (int i = 1; i < N + 1; i++) {
				st = new StringTokenizer(br.readLine());
				favorite[i] = Integer.parseInt(st.nextToken());
				calorie[i] = Integer.parseInt(st.nextToken());
			}
			
			// [재료 개수][총 제한 칼로리]
			DP = new int[N + 1][L + 1];
			
			// 각 재료당 선호 점수, 칼로리
			int favo, cal;
			for (int row = 1; row < N + 1; row++) {  // 재료 번호
				for (int col = 1; col < L + 1; col++) {  // 현재 시점 최대 칼로리
					favo = favorite[row];
					cal = calorie[row];
					
					// 1. 현재 재료를 추가해도 제한 칼로리를 넘지 않는 경우.
					if (cal <= col) {
						// Max(현재 재료를 넣었을 때의 최댓값, 현재 재료를 넣기전까지의 최댓값)
						DP[row][col] = Math.max(favo + DP[row - 1][col - cal], DP[row - 1][col]);
					}
					
					// 2. 현재 재료를 추가하면 제한 칼로리를 넘는 경우.
					else {
						// 현재 칼로리 상황에서 현재 재료를 넣기전의 최댓값.
						DP[row][col] = DP[row - 1][col];
					}
					
				}
			}
			
			sb.append("#").append(tc).append(" ").append(DP[N][L]);
			System.out.println(sb.toString());
		} // TC
	}
}
