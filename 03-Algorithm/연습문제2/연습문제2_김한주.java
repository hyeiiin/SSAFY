
class Solution
{	
	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	
	public static void main(String[] args) {
		int[][] dp = new int[3][7];
		
		final int RED = 0;
		final int BLUE = 1;
		final int YELLOW = 2; 
		//점화식 정의 
		//dp[색][현재길이] 
			//dp[붉은색][현재길이] = dp[붉은색][현재길이-2] + dp[노란색][현재길이-2] + dp[파란색][현재길이-2]
			//dp[파란색][현재길이] = dp[붉은색][현재길이-1] + dp[노란색][현재길이-1] + dp[파란색][현재길이-1]
			//dp[노란색][현재길이] = dp[붉은색][현재길이-1] + dp[노란색][현재길이-1] + dp[파란색][현재길이-1]
		
		
		//초기값 정의 
			//dp[븕은색][0] = 0
			//dp[붉은색][1]= 0
			//dp[붉은색][2] = 1
			
			//dp[파란색][0] = 0
			//dp[파란색][1] = 1
			//dp[파란색][2] = 2 
			
			//dp[노란색][0] = 0
			//dp[노란색][1] = 1
			//dp[노란색][2] = 2
		for(int length=1; length<=2; length++) {
			for(int color=0; color<3; color++) {
				dp[color][length] = length; 
				if(color==RED) {
					dp[color][length]--; 
				}
			}
		}
		
		
		//점화식으로 DP배열 채우기 
		for(int length=3; length<=6; length++) {
			dp[RED][length] = dp[RED][length-2] + dp[YELLOW][length-2] + dp[BLUE][length-2]; 
			dp[BLUE][length] = dp[RED][length-1] + dp[YELLOW][length-1] + dp[BLUE][length-1];
			dp[YELLOW][length] = dp[RED][length-1] + dp[YELLOW][length-1] + dp[BLUE][length-1];
		}
		
		//최종값 = 길이 6일 때 빨간색으로 칠한 경우 + " 파란색으로 칠한 경우 + " 노란색으로 칠한 경우 
		int result = 0; 
		for(int color=0; color<3; color++) {
			result+= dp[color][6]; 
		}
		System.out.println(result);
		
		
		
	}
	
