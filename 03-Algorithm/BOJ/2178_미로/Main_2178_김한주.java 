import java.io.*; 

import java.util.*; 

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x =x;
		this.y =y;
	}
	
}

class Solution
{	
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); 
	static StringTokenizer tokens; 
	
	static int n; 
	static int m; 
	
	static int[][] map;
	static int[][] distances; 
	
	static final int NOT_VISITED = -1;
	static final int BLANK =1;
	static final int BLOCK = 0; 
	
    //초기화부 
	static void init() throws IOException{
		tokens = new StringTokenizer(buffer.readLine()); 
		n = Integer.valueOf(tokens.nextToken());
		m = Integer.valueOf(tokens.nextToken()); 
		
		map= new int[n][m];
		distances = new int[n][m]; 

        //distance내 -1을 방문안한 상태로 정의하기 위해 미리 -1로 초기화 
		for(int i=0; i<n; i++) {
			Arrays.fill(distances[i], NOT_VISITED);
		}
        //map 정보 입력 
		for(int x=0; x<n; x++) {
			String line = buffer.readLine(); 
			for(int y=0; y<m; y++) {
				map[x][y] = Integer.valueOf(line.charAt(y)-'0'); 
			}
		}
	}
	
	//디버깅용 출력 
	static void print(int[][] map) {
		for(int[] ma: map) {
			for(int m : ma) {
				System.out.print(m+" ");
			}System.out.println(); 
		}
	}
	
    //4방 탐색 좌표 미리 정의 
	static final int[] dx = {-1,1,0,0};
	static final int[] dy = {0,0,-1,1}; 
	
	static void BFS(Point p) {
		Queue<Point> que = new ArrayDeque<>();
		que.add(p);
        //최초 시작위치 1 
		distances[p.x][p.y] = 1;
		
		while(!que.isEmpty()) {
            //현재 좌표 가져오기 
			Point cur = que.poll();
            //4방 진행 
			for(int dir=0; dir<4; dir++) {
                //다음 좌표 만들어보기 
				int nX = cur.x + dx[dir];
				int nY = cur.y + dy[dir];
                //다음좌표 유효성 검사 
				if(canGo(nX, nY)) {
                    //유효할 경우 갈 수 있는 위치이므로 distances배열 업데이트 
					distances[nX][nY] = distances[cur.x][cur.y]+1; 
					que.add(new Point(nX, nY)); 
				}
			}
		}
	}
    //인덱스 유효성 확인 
	static boolean inRange(int x, int y) {
		return x>=0&&x<n&&y>=0&&y<m; 
	}
	//갈 수 있는 구역인지 확인 
	static boolean canGo(int x, int y) {
		return inRange(x,y)&&map[x][y]==BLANK&&distances[x][y] == NOT_VISITED; 
	}
	public static void main(String[] args) throws IOException{
        //초기화
		init(); 
        //0,0에서 각점의 최소도달 거리를 저장한 distance 배열을 bfs로 만들기 
		BFS(new Point(0,0));
		System.out.println(distances[n-1][m-1]); 
	}
	
	
}
	




