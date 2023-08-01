import java.io.*; 

import java.util.*; 


class Point{
	int x, y, dir;
	public Point(int x, int y, int dir) {
		this.x = x;
		this.y  =y; 
		this.dir = dir; 
	}
	
	public String toString() {
		return this.x+" "+this.y+" "+this.dir;
	}
}
class Solution_1210_김한주
{
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens; 
	
	static int[][] map; 
	static Point end; 
	
	static final int[] dx = {-1,0,0};
	static final int[] dy = {0,-1,1}; 
	
	static final int UP = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2; 
	
	static final int SIZE = 100; 
	
	static void init() throws IOException{
		int dummy = Integer.valueOf(buffer.readLine()); 
		map = new int[SIZE][SIZE]; 
		for(int x=0; x<SIZE; x++) {
			tokens = new StringTokenizer(buffer.readLine());
			for(int y=0; y<SIZE; y++) {
				map[x][y] = Integer.valueOf(tokens.nextToken());
				if(map[x][y]==2) {
					end = new Point(x,y,0); 
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		 int T = 10;
		 StringBuilder result = new StringBuilder(); 
		 for(int test=1; test<=T; test++) {
			 init(); 
			 
			 Point start = findStart(); 
			 
			 result.append("#").append(test).append(" ").append(start.y).append("\n"); 
		 }
		
		 System.out.println(result); 
	}
	
	//입력 받으면서 도착지 저장하기 
	static Point findStart() {
		Queue<Point> que = new LinkedList<>(); 
		que.add(end); 
		
		while(!que.isEmpty()) {
			Point cur = que.poll(); 
 
			map[cur.x][cur.y] = 3; 
			if(cur.x==0) {
				return cur; 
			}
			
			if(cur.dir==LEFT||cur.dir==RIGHT) {
				if(canGo(cur.x+dx[UP], cur.y+dy[UP])) {
					
					que.add(new Point(cur.x+dx[UP], cur.y+dy[UP], UP));
					continue; 
				}else if(canGo(cur.x+dx[cur.dir], cur.y+dy[cur.dir])){
					que.add(new Point(cur.x+dx[cur.dir], cur.y+dy[cur.dir], cur.dir));
					continue;
				}
			}else if(cur.dir==UP) {
				
				if(canGo(cur.x+dx[LEFT], cur.y+dy[LEFT])){

					que.add(new Point(cur.x+dx[LEFT], cur.y+dy[LEFT], LEFT));
					continue;
				}else if(canGo(cur.x+dx[RIGHT], cur.y+dy[RIGHT])){

					que.add(new Point(cur.x+dx[RIGHT], cur.y+dy[RIGHT], RIGHT));
					continue;
				}else if(canGo(cur.x+dx[UP], cur.y+dy[UP])) {

					que.add(new Point(cur.x+dx[UP], cur.y+dy[UP], UP));
					continue; 
				}
			}
			
			
		}return new Point(-1,-1,-1); 
		
	}
	
	static boolean canGo(int x, int y) {
		return inRange(x, y)&&map[x][y]==1; 
	}
	static boolean inRange(int x, int y) {
		return x>=0&&x<SIZE&&y>=0&&y<SIZE; 
	}
	//도착지에서 출발하기 
		//행 확인 == 0 
			//return x; 
			
		//내가 지금 서쪽, 동쪽을 보고 있을 경우 
			//윗칸 확인 
				//갈 수 있으면 방향 위쪽으로 틀고 간다.
					//continue;
			//보고있는 칸 확인 
				//갈 수 있으면 그 방향으로 간다. 
					//continue; 
			//해당 라인까지오면 길이 막혀있는 경우이므로 
				//없는 경우를 리턴한다.
	
		//내가 지금 북쪽을 보고 있을 경우 
			//윗칸 확인
				//갈 수 있으면 방향 위쪽으로 틀고 간다. 
			//서쪽칸 확인 
				//갈 수 있으면 방향 서쪽으로 틀고간다.  
			//동쪽칸 확인 
				//갈 수 있으면 방향 동쪽으로 틀고 간다. 
				//continue; 
		
	
	static void print(int[][] arr) {
		for(int[] ar: arr) {
			for(int a: ar) {
				System.out.print(a+" ");
			}System.out.println(); 
		}
	}
	
}	



