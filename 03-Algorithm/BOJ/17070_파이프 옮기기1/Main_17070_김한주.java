import java.io.*; 
import java.util.*; 
class Solution
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	//가로 세로 대각선 
	static final int[] dx = {0,1,1};
	static final int[] dy = {1,1,0};
	
	static int[][][] dp; 
    //dp정의 = dp[방향][x][y] x,y에 방향으로 놓을 때 가짓수 총합 
    //dp일반식 
        //밑 일반식에서 사용하는 모든 좌표는 배열 안에 있어야하고, block이 아니여야한다. 
        //dp[가로][x][y] = dp[가로][x-1][y] + dp[대각선][x-1][y-1]; 
        //dp[대각선][x][y] = dp[가로][x-1][y] + dp[대각선][x-1][y-1] + dp[세로][x-1][y]
        //dp[세로][x][y] = dp[세로][x--1][y] + dp[대각선][x-1][y-1]; 
	static int[][] map; 
	static int n; 
	
	
	static final int BLOCK =1; 
	
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		map = new int[n][n]; 
		
		dp = new int[3][n][n];
		
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<n; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken()); 
			}
		}
		
		//초기값 설정 
		for(int y=1; y<n; y++) {
			if(map[0][y]==BLOCK)break; 
			dp[0][0][y] = 1;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		for(int x=1; x<n; x++) {
			for(int y=2; y<n; y++) {
				if(map[x][y]==BLOCK)continue; 
				for(int dir=0; dir<3; dir++) {
                    //직전 위치 확인 
					int pX = x-dx[dir];
					int pY = y-dy[dir];
					if(canGo(pX, pY)) {
						if(dir==0) {
                            //현재 위치 가로로 놓을려는 경우 직전 위치는 가로와 대각선 가능
							dp[dir][x][y] = dp[0][pX][pY]+dp[1][pX][pY]; 
						}else if(dir==1) {
                            //대각선으로 놓는 경우 설치 위치에 벽이 없는지 확인한다. 
							if(canGo(pX+1,pY)&&canGo(pX,pY+1)) {
                        //현재 위치 대각선으로 놓을려는 경우 직전 위치는 가로세로대각선 가능 
                            dp[dir][x][y] = dp[0][pX][pY] + dp[1][pX][pY] + dp[2][pX][pY];
							}
						}else if(dir==2) {
							//현재 위치 세로로 놓는 경우 직전 위치는 세로 대각선 가능 
								dp[dir][x][y] = dp[1][pX][pY]+dp[2][pX][pY]; 	
									
						}
					}
				}
			}
		}
		//결과 = n-1, n-1에서 가로로 놓을 때 + " 세로로 놓을 때 + " 대각으로 놓을 때 경우의 수
		System.out.println(dp[0][n-1][n-1]+dp[1][n-1][n-1]+dp[2][n-1][n-1]); 
		
	}
	static int result = 0; 
	
	static final char[] dirs = {'-','|','/'}; 
	
	static boolean canGo(int x,int y) {
		return inRange(x,y)&&map[x][y]!=BLOCK; 
	}
	
	static boolean inRange(int x, int y) {
		return x>=0&&y>=0&&x<n&&y<n;
	}
}
