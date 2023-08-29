import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int x=Integer.parseInt(br.readLine());
		int[] dp=new int[1000001];
		dp[2]=dp[3]=1;
		for (int i = 2; i <= x; i++) {
			if(dp[i]==0) dp[i]=dp[i-1]+1;
			if(i+1<=x) {
				if(dp[i+1]==0||dp[i+1]>dp[i]+1) {
					dp[i+1]=dp[i]+1;
				}
			}
			if(i*2<=x) {
				if (dp[i*2]==0 || dp[i*2]>dp[i]+1) {
					dp[i*2]=dp[i]+1;
				}
			}
			if(i*3<=x) {
				if (dp[i*3]==0 || dp[i*3]>dp[i]+1) {
					dp[i*3]=dp[i]+1;
				}
			}
		}
		System.out.println(dp[x]);
	}
}

