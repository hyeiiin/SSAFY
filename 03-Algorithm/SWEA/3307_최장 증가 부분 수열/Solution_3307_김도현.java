package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3307_김도현 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			int result = 0;
			st = new StringTokenizer(br.readLine());
			int N  = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			st = new  StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int dp[] = new int[N];
			for (int i = 0; i < dp.length; i++) {
				dp[i] = 1;
			}
			for (int i = 0; i < dp.length; i++) {
				for (int j = i-1; j >= 0; j--) {
					if(arr[j]<arr[i] && dp[i] < dp[j]+1) {
						dp[i] = dp[j]+1;
					}
				}
			}
			
			Arrays.sort(dp);
			result = dp[N-1];
			System.out.println("#"+tc+" "+result);
		}
	}

}
