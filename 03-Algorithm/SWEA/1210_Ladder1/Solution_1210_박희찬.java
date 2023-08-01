import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int TC = 10;
	static int tc;
	static int res;
	static int size = 100;
	static int[][] lst = new int[size][size];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t < TC + 1; t++) {  // TC
			st = new StringTokenizer(br.readLine());
			tc = Integer.parseInt(st.nextToken());
			
			// 사다리 초기화
			for (int i = 0; i < size; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					lst[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 도착점 찾은 후 함수 호출
			for (int i = 0; i < size; i++) {
				if (lst[size-1][i] == 2) {
					res = bfs(size-1, i); 
					break;
				}
			}
			
			System.out.println("#" +tc + " " + res);
		}  // TC
		
		
	}
	
	// 도착점 부터 역탐색
	static int bfs(int x, int y) {  // 99에서 시작
		while (x > 0) {
			
			/* 방문처리 안해보려고 if-while로 좌, 우 검사
			*  if로 가야할 방향 다시 명시 안해주면, 
			*  좌(우)로 갔다가 다시 반대 우(좌)로 가는 예외가 생김
			*/
			if (y + 1 < size && lst[x][y + 1] == 1) {  
				while (y + 1 < size && lst[x][y + 1] == 1) y++;
			}
			
			else if (y - 1 > 0 && lst[x][y - 1] == 1) {
				while (y - 1 > 0 && lst[x][y - 1] == 1) y--; 
			}
			
			x--;
		}
		
		return y;
		
	}

}
