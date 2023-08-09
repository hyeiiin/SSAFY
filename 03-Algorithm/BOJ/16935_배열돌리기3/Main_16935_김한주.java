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
	static int[] commands;
	
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		r = Integer.valueOf(tokens.nextToken()); 
		
		map = new int[n][m]; 
		commands = new int[r]; 
		
		for(int x=0; x<n; x++) {
			tokens = new StringTokenizer(buffer.readLine()); 
			for(int y=0; y<m; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
			}
		}
		tokens = new StringTokenizer(buffer.readLine());
		for(int i=0; i<r; i++) {
			commands[i] = Integer.valueOf(tokens.nextToken());
		}
	}
	//연산 1: 상하반전 
	static int[][] command1(){
		int[][] next = new int[n][m]; 
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				next[x][y] = map[n-1-x][y]; 
			}
		}
		return next; 
	}
	
	
	//연산 2 : 좌우 반전 
	static int[][] command2(){
		int[][] next = new int[n][m]; 
		
		for(int x=0; x<n; x++) {
			for(int y=0; y<m; y++) {
				next[x][y] = map[x][m-1-y]; 
			}
		}
		return next; 
		
	}
	
    // 연산 3 : 오른쪽 90도 회전 
	static int[][] command3(){
		int[][] next = new int[m][n]; 
		
		for(int x=0; x<m; x++) {
			for(int y=0; y<n; y++) {
				next[x][y] = map[n-1-y][x];
			}
		}
		
		int tmp = n;
		n = m;
		m = tmp; 
		return next; 
	}
	
    //연산 4 : 왼쪽으로 90도 회전 
	static int[][] command4(){
		int[][] next = new int[m][n]; 
		
		for(int x=0; x<m; x++) {
			for(int y=0; y<n; y++) {
				next[x][y] = map[y][m-1-x];
			}
		}
		
		int tmp = n;
		n = m;
		m = tmp; 
		return next; 
	}
	//연산 5 
	static int[][] command5(){
		int[][] next = new int[n][m]; 
		//1번 구역 채우기 4번 그룹 -> 1번 그룹 
		for(int x=0; x<n/2; x++) {
			for(int y=0; y<m/2; y++) {
				next[x][y] = map[x+n/2][y];
			}
		}
		
		//2번 구역 채우기 : 1번 그룹 -> 2번 그룹 
		for(int x=0; x<n/2; x++) {
			for(int y=m/2; y<m; y++) {
				next[x][y] = map[x][y-m/2];
			}
		}
		
		//3번 구역 채우기 : 2번 그룹 -> 3번 그룹 
		for(int x=n/2; x<n; x++) {
			for(int y=m/2; y<m; y++) {
				next[x][y] = map[x-n/2][y];
			}
		}
		
		//4번 구역 채우기 : 3번 그룹 -> 4번 그룹 
		for(int x=n/2; x<n; x++) {
			for(int y=0; y<m/2; y++) {
				next[x][y] = map[x][y+m/2];
			}
		}
		
		return next; 
	}
	
	
	
	static int[][] command6(){
		int[][] next = new int[n][m]; 
		//1번 구역 채우기 : 2번 그룹 -> 1번 그룹 
		for(int x=0; x<n/2; x++) {
			for(int y=0; y<m/2; y++) {
				next[x][y] = map[x][y+m/2];
			}
		}
		
		//2번 구역 채우기 : 3번 그룹 -> 2번 그룹 
		for(int x=0; x<n/2; x++) {
			for(int y=m/2; y<m; y++) {
				next[x][y] = map[x+n/2][y];
			}
		}

		//3번 구역 채우기 : 4번 그룹 -> 3번 그룹 
		for(int x=n/2; x<n; x++) {
			for(int y=m/2; y<m; y++) {
				next[x][y] = map[x][y-m/2];
			}
		}
		
		//4번 구역 채우기 : 1번 그룹 -> 4번 그룹 
		for(int x=n/2; x<n; x++) {
			for(int y=0; y<m/2; y++) {
				next[x][y] = map[x-n/2][y];
			}
		}
		
		return next; 
	}
	
	public static void main(String[] args) throws IOException{
		init();
		for(int command : commands) {
			if(command ==1) {
				map= command1();
			}else if(command==2) {
				map = command2(); 
			}else if(command==3) {
				map = command3(); 
			}else if(command ==4) {
				map = command4(); 
			}else if(command==5) {
				map = command5();
			}else if(command==6) {
				map = command6(); 
			}

		}
		print(map); 
	}
	
	static void print(int[][] map) {
		StringBuilder sb= new StringBuilder(); 
		for(int[] ma : map) {
			for(int m : ma) {
				sb.append(m).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb); 
	}
}	



