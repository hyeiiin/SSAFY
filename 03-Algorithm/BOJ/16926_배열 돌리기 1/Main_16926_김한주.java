import java.io.*; 

import java.util.*; 


class Solution
{
	//입력 변수 정의 
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int n;
	static int m; 
	static int r; 
	static int[][] map; 
	
	
	
	static StringBuilder result; 
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken());
		r = Integer.valueOf(tokens.nextToken()); 
		
		
		map = new int[n][m];
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine());  
			for(int y=0; y<m; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken()); 
			}
		}
	}
	static int[][] rotate(){
		int[][] tmp = new int[map.length][map[0].length]; 
		
		for(int start=0; start<map.length/2; start++) {

			
			//1번째 행 이동 
			for(int y=start; y<map[0].length-start-1; y++) {
				tmp[start][y] = map[start][y+1];
			}
			
			//마지막 열 이동 
			for(int x=start; x<map.length-start-1; x++) {
				tmp[x][m-start-1] = map[x+1][m-start-1];
			}
			
			//마지막 행 이동 
			for(int y=map[0].length-1-start; y>=start+1; y--) {
				tmp[n-start-1][y] = map[n-start-1][y-1];
			}
			
			//첫번째 열 이동 
			for(int x= map.length-1-start; x>=start+1; x--) {
				tmp[x][start] = map[x-1][start];
			}
			
			
		}
		
		
		return tmp; 
	}
	
	public static void main(String[] args)throws IOException{
		init();
		for(int i=0; i<r; i++) {
			map = rotate(); 
			
		}
		print(map);
		
		
	}
	
	static void print(int[][] map) {
		StringBuilder sb = new StringBuilder();
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				sb.append(map[x][y]).append(" ");
			}sb.append("\n");
		}
		System.out.println(sb); 
		
	}
	
}	



