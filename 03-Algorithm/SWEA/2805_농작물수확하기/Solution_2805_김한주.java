import java.io.*; 

import java.util.*; 


class Point{
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y  =y; 
	}
}
class Solution_2805_김한주
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static final int dx[] = {1,-1,-1,1};
	static final int dy[] = {-1,-1,1,1};
	
	static int n; 
	static int[][] map;
	static int result; 
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine()); 
		map = new int[n][n];
		result = 0; 
		for(int x=0; x<n; x++) {
			String line = buffer.readLine();  
			for(int y=0; y<n;y++) {
				map[x][y]= Integer.valueOf(line.charAt(y)-'0'); 
			}
		}
	}
	
	
	static int getProfit() {
		
		int midX = n/2;
		
		int midY = n/2;
		
		int profit = map[midX][midY]; 
		 
		
		for(int size=1; size<=midX; size++) {
			Point cur = new Point(midX, midY+size); 
			
			for(int dir=0; dir<4; dir++) {
				for(int offset=0; offset<size; offset++) {
					cur = new Point(cur.x+dx[dir], cur.y+dy[dir]); 
					profit+=map[cur.x][cur.y]; 
				}
			}
		}
		
		
		return profit; 
	}
	
	public static void main(String[] args)throws IOException{
		
		
		int T = Integer.valueOf(buffer.readLine()); 
		StringBuilder sb = new StringBuilder(); 
		for(int test=1; test<=T; test++) {
			init(); 
			int result = getProfit();
			sb.append("#").append(test).append(" ").append(result).append("\n");
			
		}
		
		System.out.println(sb); 
		//가운데에서 시작 
			//size 1~전체길이/2
				//시작점 = 가운데에서 오른쪽으로 한칸 
				//왼쪽 상승 대각선 -> 왼쪽 하강 대각선 -> 오른쪽 하강 대각선 -> 오른쪽 상승 대각선 
					//size만큼 반복하여 쭉쭉 올라감 
	}
	
	static void print(int[][] arr) {
		for(int[] ar: arr) {
			for(int a: ar) {
				System.out.print(a+" ");
			}System.out.println();
		}
	}
	
}
