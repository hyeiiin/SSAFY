import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class Solution_2805_김현영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());

			// 농장-이차원배열
			int[][] farm = new int[n][n];
			for (int i = 0; i < n; i++) {
				String farm_i = br.readLine();
				for (int j = 0; j < n; j++) {
					farm[i][j] = Integer.parseInt(farm_i.substring(j,j+1));
				}
			} 
			
			//농작물 수확 합
			int sum = 0;
			
			//마름모 정중앙 기준 중앙 포함 상단
			for (int i = 0; i <= n/2; i++) {
				for (int j = n/2-i; j <= n/2+i; j++) {
					sum += farm[i][j];
				}
			}
			//마름모 하단
			for (int i = n/2+1; i < n; i++) {
				for (int j = i-n/2; j < n-i+n/2; j++) {
					sum += farm[i][j];
				}
			}
			
			System.out.printf("#%d %d\n", t,sum);
		}
	}
}
