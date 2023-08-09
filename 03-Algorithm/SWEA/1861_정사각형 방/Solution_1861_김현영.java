import java.io.*;
import java.util.*;

public class Solution_1861_김현영 {

	static int[][] room;
	static int roomNum; //최대로 이동할 수 있는 방의 시작점 중 가장 작은 번호
	static int maxMove; //최대로 이동할 수 있는 횟수
	
	// 상하좌우
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	//방의 인덱스를 가지는 클래스
	static class Pos {
		int x;
		int y;
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	//인덱스가 방크기 n을 초과하는 지 확인하는 함수
	static boolean checkIndex(int x, int y, int n) {
		if( 0<= x && x<n && 0<=y && y<n)
			return true;
		return false;
	}
	
	static void bfs(Deque<Pos> q, int[][] copyRoom ) {
		int count = 1; // 현재부터 이동한 횟수 저장
		int firstRoomNum = copyRoom[q.peek().x][q.peek().y];		
		//q가 비워질 때까지 반복
		while(!q.isEmpty()) {
			Pos now = q.poll();	//현재 좌표 뽑아내기
			int nowNum = copyRoom[now.x][now.y];	//현재 좌표의 방 번호
			copyRoom[now.x][now.y] = 0; //방문처리
			//사방탐색
			for (int i = 0; i < 4; i++) {
				int r = now.x + dr[i];
				int c = now.y + dc[i];
				//다음 방으로 이동할 수 있는 경로라면 큐에 저장
				if(checkIndex(r,c,copyRoom.length) && copyRoom[r][c] == nowNum+1) {
					q.add(new Pos(r,c));
					count++;
				}	
			}
		} 		
		//현재 방에서 출발한 이동경로가 최대 경로일 때 출력할 방 번호와 이동횟수 갱신
		if(count > maxMove) {
			roomNum = firstRoomNum;
			maxMove = count;
		}
		//현자 방에서 출발한 이동경로가 최대경로와 값이 같을 때 방 번호가 더 작은 값 저장
		else if(count == maxMove) {
			roomNum = Math.min(roomNum, firstRoomNum);					
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken()); // 테스트케이스 수 입력

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 방 크기
			// nxn 방 입력
			room = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					room[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			//테스트 케이스마다 출력할 방 번호와 이동 횟수 초기화
			roomNum = Integer.MAX_VALUE;
			maxMove= 0;
			
			//room 배열을 카피한 이차원 배열. bfs에서 방문처리 시 사용
			int[][] copyRoom = new int[n][n];
			
			Deque<Pos> q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					q.add(new Pos(i,j));
					//이전의 방문처리를 없애기 위해서 room을 다시 카피함
					for (int a = 0; a < n; a++) {
						for (int b = 0; b < n; b++) {
							copyRoom[a][b] = room[a][b];
						}
					}
					bfs(q, copyRoom );
				}
			}
			
			//출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			sb.append(roomNum).append(" ").append(maxMove);
			System.out.println(sb.toString());

		}
	}// main end
}
