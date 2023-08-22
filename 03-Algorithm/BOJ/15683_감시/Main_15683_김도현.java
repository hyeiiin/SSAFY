package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

	class cctv {
	    int x;
	    int y;
	    int Type;
	
	    public cctv(int x, int y, int Type) {
	        this.x = x;
	        this.y = y;
	        this.Type = Type;
	    }
	
	}
	
	public class Main_15683_김도현 {
		
	static int N,M,map[][],minArea;
	static ArrayList<cctv> list;
	
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    N = Integer.parseInt(st.nextToken());
	    M = Integer.parseInt(st.nextToken());
	    map = new int[N][M];
	    list = new ArrayList<>();
	    minArea = Integer.MAX_VALUE;
	    for (int i = 0; i < N; i++) {
	        st = new StringTokenizer(br.readLine());
	        for (int j = 0; j < M; j++) {
	            map[i][j] = Integer.parseInt(st.nextToken());
	            if(map[i][j]!=0 && map[i][j] != 6) {
	                list.add(new cctv(i, j, map[i][j]));
	            }
	        }
	    }
	    combination(0);
	    System.out.println(minArea);
	    
	}
	
	public static void calArea() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0) {
					cnt++;
				}
			}
		}
		if(minArea>cnt) {
			minArea = cnt;
		}
	}
	

	public static void combination(int depth) {
	    if (depth == list.size()) {
	        calArea();
	        return;
	    }
	    
	    cctv currentCCTV = list.get(depth);
	    
	    for (int dir = 0; dir < 4; dir++) {
	        
	        int[][] prevMap = new int[N][M]; // 현재 depth에서 탐색하기 전에 맵을 백업
	        for (int i = 0; i < N; i++) {
	            prevMap[i] = Arrays.copyOf(map[i], M);
	        }
	        
	        drawMap(currentCCTV, dir);
	        combination(depth + 1);
	        
	        // 백트래킹: 맵을 이전 상태로 복구
	        for (int i = 0; i < N; i++) {
	            map[i] = Arrays.copyOf(prevMap[i], M);
	        }
	    }
	}

	
	public static void drawMap(cctv cctv, int dir) {
		int x = cctv.x;
		int y = cctv.y;
		
		if(cctv.Type==5) {
    		while(check(x, y-1)) {
    			y--;
    			map[x][y] = -1;
    		}
    		x=cctv.x;
    		y=cctv.y;
    		while(check(x, y+1)) {
    			y++;
    			map[x][y] = -1;
    		}
    		x=cctv.x;
    		y=cctv.y;
    		while(check(x-1, y)) {
    			x--;
    			map[x][y] = -1;
    		}
    		x=cctv.x;
    		y=cctv.y;
    		while(check(x+1, y)) {
    			x++;
    			map[x][y] = -1;
    		}
	    }else if(cctv.Type==1) {
	    	if(dir==0) { // 좌
	    		while(check(x, y-1)) {
	    			y--;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==1) {// 우
	    		while(check(x, y+1)) {
	    			y++;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==2) {// 상
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==3) {// 하
	    		while(check(x+1, y)) {
	    			x++;
	    			map[x][y] = -1;
	    		}
	    	}
	    }else if(cctv.Type==2) {
	    	if(dir==0) { // 좌
	    		while(check(x, y-1)) { // 좌우
	    			y--;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y+1)) {
	    			y++;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==1) { //우
	    		while(check(x, y+1)) { // 우좌
	    			y++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y-1)) {
	    			y--;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==2) { // 상
	     		while(check(x+1, y)) { // 상하
	     			x++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==3) { //하
	     		while(check(x+1, y)) { // 하상
	     			x++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    	}
	    }else if(cctv.Type==3) {
	    	if(dir==0) { // 좌, 상
	    		while(check(x, y-1)) {
	    			y--;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==1) {// 우, 하
	    		while(check(x, y+1)) {
	    			y++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x+1, y)) {
	    			x++;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==2) {// 상, 우
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y+1)) {
	    			y++;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==3) {// 하 좌
	    		while(check(x+1, y)) {
	    			x++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y-1)) {
	    			y--;
	    			map[x][y] = -1;
	    		}
	    	}
	    }else if(cctv.Type==4) {
	    	if(dir==0) { // 좌, 상, 하
	    		while(check(x, y-1)) {
	    			y--;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x+1, y)) {
	    			x++;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==1) {// 우, 하, 상
	    		while(check(x, y+1)) {
	    			y++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x+1, y)) {
	    			x++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==2) {// 상, 우, 좌
	    		while(check(x-1, y)) {
	    			x--;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y+1)) {
	    			y++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y-1)) {
	    			y--;
	    			map[x][y] = -1;
	    		}
	    	}else if(dir==3) {// 하, 좌, 우
	    		while(check(x+1, y)) {
	    			x++;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y-1)) {
	    			y--;
	    			map[x][y] = -1;
	    		}
	    		x=cctv.x;
	    		y=cctv.y;
	    		while(check(x, y+1)) {
	    			y++;
	    			map[x][y] = -1;
	    		}
	    	}
	    }
	}

	public static boolean check(int x, int y) {
		if(x>=0 && y>=0 && x<N && y<M && map[x][y]!=6) {
			return true;
		}
		return false;
	}
}