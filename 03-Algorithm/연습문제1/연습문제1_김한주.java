
class Solution
{	
	

	
	
	public static void main(String[] args) {
		final int YELLOW = 1;
		final int BLUE = 0; 
		int[][] dp = new int[2][9];
		
		//dp[현재쓴색상][현재층] = 현재층을 해당 색상으로 칠하는 경우의 수 
		//점화식 
			//dp[blue][stage] = dp[YELLOW][stage-1] 
                // 현재층에 파란색을 칠할 경우 반드시 전층은 노란색이여야함
			//dp[yellow][stage] = dp[blue][stage-1] + dp[yellow][stage-1] 
                //현재층에 노란색을 칠할 경우 전 층의 색은 상관 없음 
		
		
		//초기식 
			//각 색을 1층에 칠하는 수는 1가지씩 
		dp[BLUE][1] =1;
		dp[YELLOW][1] = 1; 
		
		//점화식 채우기 
		for(int stage=2; stage<9; stage++) {
			for(int color=0; color<2; color++) {	
				if(color==BLUE) {
					dp[color][stage] = dp[YELLOW][stage-1]; 
				}else if(color==YELLOW) {
					dp[color][stage] = dp[YELLOW][stage-1] + dp[BLUE][stage-1]; 
				}
			}
		}
		
		//출력 : 8층을 파란색으로 칠하는 경우의수 + 노란색으로 칠하는 경우의 수 
		System.out.println(dp[BLUE][8]+dp[YELLOW][8]); 
		
		
		
	}
	
	
}
	




