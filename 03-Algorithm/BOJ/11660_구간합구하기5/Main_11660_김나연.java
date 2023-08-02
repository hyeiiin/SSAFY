import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_김나연 {
	
	static int n,m;
	static int a[][], dp[][];
	static int x1, y1, x2, y2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		a=new int[n+1][n+1];
		dp=new int[n+1][n+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				
				dp[i][j] = dp[i-1][j]+dp[i][j-1] - dp[i-1][j-1]+a[i][j];
			}
		}
		
		for (int i = 0; i < m; i++) {	
			st = new StringTokenizer(br.readLine());
			
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			int sum=0;
			sum = dp[x2][y2] - dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1];
			System.out.println(sum);
		}
	}

}
