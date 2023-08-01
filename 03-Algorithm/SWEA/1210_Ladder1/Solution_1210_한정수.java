import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_한정수 {
	public static boolean search(String[][] board, int x, int y) {
		int[] dx = {1, 0, 0};  //아래 우 좌
		int[] dy = {0, 1, -1}; //아래 우 좌
		boolean isGoingRight = false;
		boolean isGoingLeft = false;
		while(true) {
//			System.out.println("x : "+x+" y : "+y);
			//일단 현재 기준 좌우 탐색부터.
			//우
			//보드안에 있다면, 0인가 1인가.
			if(y + dy[1] < 100) {
				if(board[x][y+dy[1]].equals("1")) {
					if (!isGoingLeft) {
						isGoingRight = true;
						y+=dy[1];
						continue;
					}
					
				}
				else {
					
				}
			}
			//좌
			if(y+dy[2]>=0) {
				if(board[x][y+dy[2]].equals("1")) {
					if(!isGoingRight) {
						isGoingLeft = true;
						y+=dy[2];
						continue;
					}
					
				}
				else {
					
				}
			}
			//일단 현재 위치 검사.
			if(board[x][y].equals("2")) {
				return true;
			}
			
			//당연히 1만 따라갈거임. 

			
			//아래로 가는데 보드 밖이면 끝
			if(x + dx[0] >= 100) {
				break;
			}
			
			//아래로 가는중
			x+=dx[0];
			y+=dy[0];
			isGoingRight = false;
			isGoingLeft = false;
		}
		
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int test_case = 1; test_case <= 10; test_case++) {
			int test_num = Integer.parseInt(br.readLine());
			String[][] board = new String[100][100];
			StringTokenizer st;
			for (int i=0 ; i<100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<100; j++) {
					board[i][j] = st.nextToken();
				}
			}
			//입력완료===============
			int idx = -1;
			for(int i=0; i<100; i++) {
				if(board[0][i].equals("1")) {
					if(search(board,0,i)) {
						idx = i;
						break;
					}
				}
				
			}
			System.out.printf("#%d %d",test_case, idx);
			System.out.println();
			
		}
		
		
		
	}

}
