package algo;

import java.io.*;
import java.util.*;


class Point{
	int x, y; 
	
	public Point(int x, int y) {
		this.x=x;
		this.y= y;
	}
	
	public String toString() {
		return this.x+":"+this.y; 
	}
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    static int r;
    static int c; 
    
    static int T; 
    
    static int[][] map; 
    
    static Point up;
    static Point down;
    
    static void init()throws IOException{
    	tokens = new StringTokenizer(buffer.readLine());
    	
    	r = Integer.valueOf(tokens.nextToken());
    	c = Integer.valueOf(tokens.nextToken());
    	T = Integer.valueOf(tokens.nextToken()); 
    	
    	
    	map = new int[r][c]; 
    	
    	for(int x=0; x<r; x++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		for(int y=0; y<c; y++) {
    			map[x][y] = Integer.valueOf(tokens.nextToken());
    			if(map[x][y]==-1) {
    				if(up==null) {
    					up = new Point(x,y); 
    				}else {
    					down = new Point(x,y); 
    				}
    			}
    		}
    	}
    	
    	
    }
    
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1}; 
    
    static int[][] diffuse(){
    	int[][] nextMap = new int[r][c]; 
    	
    	for(int x=0; x<r; x++) {
    		for(int y=0; y<c; y++) {
    			int c = 0; 
    			//4방향 탐색 후 갈 수 있으면 카운트 + nextMap에 넣어주기 
    			for(int dir=0; dir<4; dir++) {
    				int nx = x + dx[dir];
    				int ny = y + dy[dir]; 
    				
    				if(inRange(nx, ny)&&map[nx][ny]!=-1) {
    					nextMap[nx][ny] += map[x][y]/5; 
    					c++; 
    				}
    			}
    			
    			nextMap[x][y] += (map[x][y]-(map[x][y]/5)*c); 
    			//현재 위치 업데이트
    		}
    	}

    	return nextMap; 
    }
    
    static boolean inRange(int x, int y) {
    	return x>=0&&y>=0&&x<r&&y<c; 
    }
    
    static int[][] activateAirCleaner() {
    	int[][] nextMap = new int[r][c]; 
    	
    	
    	for(int x=0; x<r; x++) {
    		for(int y=0; y<c; y++) {
    			nextMap[x][y] = map[x][y]; 
    		}
    	}
    	
    	//위 쪽 범위 순환 
    	//첫번째 열 움직이기 
    	for(int x=1; x<up.x; x++) {
    		nextMap[x][0] = map[x-1][0];
    	}
    	
    	//첫번째 행 움직이기
    	for(int y=0; y<c-1; y++) {
    		nextMap[0][y] = map[0][y+1];
    	}
    	
    	//마지막 열 움직이기
    	for(int x=0; x<up.x; x++) {
    		nextMap[x][c-1] = map[x+1][c-1];
    	}
    	
    	//마지막 행 움직이기 
    	for(int y=c-2; y>=1; y--) {
    		nextMap[up.x][y+1] = map[up.x][y];
    	}
    	
    	nextMap[up.x][up.y+1] = 0; 
    	
    	//아래쪽 범위 순환 
    	//맨 첫번째 열 움직이기 
    	for(int x=down.x+1; x<r-1; x++) {
    		nextMap[x][0] = map[x+1][0]; 
    	}
    	
    	//맨 마지막행 움직이기 
    	for(int y=0; y<c-1; y++) {
    		nextMap[r-1][y] = map[r-1][y+1];
    	}
    	
    	//맨 마지막 열 움직이기
    	for(int x=down.x; x<r; x++) {
    		nextMap[x][c-1] = map[x-1][c-1];
    	}
    	
    	//맨 첫번째 행 움직이기 
    	for(int y=c-2; y>=1; y--) {
    		nextMap[down.x][y+1] = map[down.x][y];
    	}
    	nextMap[down.x][1] = 0; 
    	return nextMap; 
    }
    
    public static void main(String[] args) throws IOException{
    	init(); 

    	for(int t=1; t<=T; t++) {
    		//확산 
    			//모든 맵 순회하면서 다음 맵 제작 
//    		System.out.println("확산"); 
    		map = diffuse(); 
//    		print(map); 
    		//공기청정기
    		map = activateAirCleaner();
//    		System.out.println("공기청정기");
//    		print(map); 
    	}
    	
    	System.out.println(getScore()); 
    	
    }
    
    static int getScore() {
    	int score = 0; 
    	for(int x=0; x<r; x++) {
    		for(int y=0; y<c; y++) {
    			
    			score+=map[x][y];
    		}
    	}
    	
    	return score+2; 
    }
    
    static void print(int[][] map) {
    	for(int x=0; x<r; x++) {
    		for(int y=0; y<c; y++) {
    			System.out.print(map[x][y]+" ");
    		}System.out.println(); 
    	}
    }
    
    
    
    //공기청정기 
    	//1번 열에 설치 
    	//크기는 두행 차지 
    
    
    //확산 
    	//인접한 네 방향으로 확산 
    	//인접한 방향에 공기청정기나 칸이 없다면 일어나지 않음 
    	//확산되는 양 = /5이고 소수점은 버림 
    	//남은 양 = 원본 -원본/5 * 확산 방향 개수 
    
    //공기 청정기 작동 
    	//위쪽 공기청정기 반시계방향
    	//아래쪽 공기청정기 시계방향 
    	//미세먼지가 공기 청정기로 들어갈 경우 없어짐
    	//새로 나오는 공기는 0 
   
    
    
   

}
