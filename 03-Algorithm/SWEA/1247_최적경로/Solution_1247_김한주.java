import java.io.*; 

import java.util.*; 


class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//거리 반환 메서드 
	public int getDistance(Point o) {
		return Math.abs(this.x-o.x) + Math.abs(this.y-o.y); 
	}
	
	public String toString() {
		return this.x+":"+this.y; 
	}
}

class Solution
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	// x~100
	//y~100 
	
	static int n; // 고객의 수 ~10 
	static int result; 
	static Point company; 
	static Point endPoint; 
	static Point[] customers; 
	static Point[] routes; 
	static boolean[] isUsed; 
	
	static void init() throws IOException{
		n = Integer.valueOf(buffer.readLine());
		tokens = new StringTokenizer(buffer.readLine()); 
		
		company = new Point(Integer.valueOf(tokens.nextToken()), Integer.valueOf(tokens.nextToken())); 
		endPoint = new Point(Integer.valueOf(tokens.nextToken()), Integer.valueOf(tokens.nextToken()));
		routes = new Point[n]; 
		customers = new Point[n]; 
		isUsed = new boolean[n]; 
		result = Integer.MAX_VALUE;; 
		for(int i=0; i<n; i++) {
			customers[i] = new Point(Integer.valueOf(tokens.nextToken()), Integer.valueOf(tokens.nextToken()));
		}

	}
	
	static void bt(int cur) {
		
		//루트 n개 선택한 경우의 수 도달 
		if(cur==n) {
			//최종 거리 계산 
			int tmpDist = 0; 
			tmpDist += company.getDistance(routes[0]);
			
			for(int i=1; i<n; i++) {
				tmpDist += routes[i].getDistance(routes[i-1]); 
			}
			
			tmpDist += routes[n-1].getDistance(endPoint); 
			//거리 갱신 
			result = Math.min(tmpDist, result);
			return; 
		}
		//n개의 방문지점 순열로 뽑기 
		for(int next=0; next<n; next++) {
			if(!isUsed[next]) {
				isUsed[next] = true;
				routes[cur]= customers[next]; 
				bt(cur+1);
				isUsed[next] = false; 
			}
		}
	}
	
	public static void main(String[] args)throws IOException{
		int T = Integer.valueOf(buffer.readLine());
		StringBuilder format = new StringBuilder(); 
		for(int t=1; t<=T; t++) {
			init();
			//n개의 방문지점 순열로 뽑기 
			bt(0); 
			//customers의 순열 구하기 
			format.append("#").append(t).append(" ").append(result).append("\n"); 
		}
		
		System.out.println(format); 
		
		
	}
	
	
}
	




