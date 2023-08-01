import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2805_조은서 {
	public static int n;
	public static int[][] farm;
	public static int sum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine()); // 농장의 크기
			farm = new int[n][n];
					
			for(int i=0;i<n;i++) {
				String str = br.readLine();
				int[] line = new int[n];
				for(int j=0; j<n; j++) {
					line[j] = str.charAt(j) - '0';
				}
				farm[i] = line;
			}
			
			int row_start = n/2;
			int count =0;
			for(int i=row_start; i < n; i++) {
				for(int j=count; j <n-count; j++) {
					sum += farm[i][j];
				}
				count++;
			}
			
			count = 1;
			for(int i=row_start-1; i>=0; i--) {
				for(int j=count; j < n-count; j++) {
					sum += farm[i][j];
				}
				count++;
			}
			System.out.println("#" + tc + " " + sum);
			sum = 0;
		}
	}
	
}
