import java.io.*;
import java.math.BigInteger;
import java.util.*; 




class Main
{	
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	
	
	static final int MAX_SIZE = 1000001; 
	
	public static void main(String[] args) throws IOException{
		
		
		
		int n = Integer.valueOf(buffer.readLine());
		int[] dp = new int[MAX_SIZE+1];
		
		//dp배열 정의 
			//dp[i] = i를 만들기 위한 최소 연산 수 
		
		//초기값 정의 
			//idx= [0,1,2,3,4,5] 
			//dp = [0,0,1,1,3,3];
		for(int i=2; i<=5; i++) {
			dp[i] =1;
		}
		
		dp[5] =3;
		dp[4] = 3; 
		
		
		//점화식 정의 
			//2와 3 둘다 나눠지는 것이 가능할 경우 
				//dp[i] = min(dp[i-1], dp[i/2], dp[i/3])+1
			//3으로 나눠지는 것이 가능할 경우 
				//dp[i] = min(dp[i-1], dp[i/3])+1
			//2로 나눠지는 것이 가능할 경우 
				//dp[i] = min(dp[i-1], dp[i/2])+1
			//그 외의 경우 
				//dp[i] = dp[i-1] + 1 
		for(int i=6; i<=n; i++) {
			if(i%6==0) {
				dp[i] = Math.min(dp[i-1], dp[i/3]);
				dp[i] = Math.min(dp[i], dp[i/2]);
				dp[i]++; 
			}
			else if(i%3==0) {
				dp[i] = Math.min(dp[i-1], dp[i/3])+1;
			}else if(i%2==0) {
				dp[i] = Math.min(dp[i-1], dp[i/2])+1; 
			}else {
				dp[i] = dp[i-1]+1;
			}
			  
		}
		
		//결과 출력하기 

		System.out.println(dp[n]); 
		
		
		
	}
	
	
}
	




