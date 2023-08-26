package BOJ;

import java.io.*;
import java.util.*;

/**
 * 상어클래스
 * 속도, 방향인덱스, 크기
 */
class Shark{
	public int speed, dirIdx, size;

	public Shark(int speed, int dirIdx, int size) {
		super();
		this.speed = speed;
		this.dirIdx = dirIdx;
		this.size = size;
	}
	
}

public class Main_17143_김민석 {
	//격자판
	private static Shark[][] board;
	//행 열 크기
	private static int R,C;
	//상하좌우
	private static int[] dx = {-1,1,0,0};
	private static int[] dy = {0,0,1,-1};
	/**
	 * 상어들 움직이기
	 */
	private static void move() {
		//임시 격자판
		Shark[][] tmp = new Shark[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				//상어가 있다면 이동시킴
				if(board[i][j] != null) {
					//현재 상어
					Shark cur = board[i][j];
					int x = i;
					int y = j;
					//speed 만큼 반복
					for (int cnt = 0; cnt < cur.speed; cnt++) {
						//speed 1만큼 이동했을 때 다음 이동 위치
						int nx = x + dx[cur.dirIdx];
						int ny = y + dy[cur.dirIdx];
						//다음위치가 격자판 안쪽이면
						if(isIn(nx,ny)) {
							//해당 방향으로 이동
							x += dx[cur.dirIdx];
							y += dy[cur.dirIdx];
						} else {
							//벽을 만난 거니까 반대방향으로 이동
							x -= dx[cur.dirIdx];
							y -= dy[cur.dirIdx];
							//이동방향 바꾸기
							cur.dirIdx = ((cur.dirIdx==0 || cur.dirIdx==1) ? 1:5) - cur.dirIdx;
						}
					}
					//최종위치가 비어있으면 상어 넣기
					if(tmp[x][y] == null) {
						tmp[x][y] = cur;
					//아니라면 사이즈 비교해서 더큰상어로 갱신
					} else {
						if(tmp[x][y].size < cur.size) tmp[x][y] = cur; 
					}
				}
			}
		}
		//원본격자판에 결과 반영
		for (int i = 0; i < R; i++) {
			board[i] = Arrays.copyOf(tmp[i], C);
		}
	}
	
	/**
	 * 격자판 안쪽인지 확인
	 * @param x
	 * @param y
	 * @return 해당 좌표 격자판 안쪽인지 여부
	 */
	private static boolean isIn(int x, int y) {
		if(x>=0 && y>=0 && x<R && y<C) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//입력 및 초기화
		int answer = 0;
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		board = new Shark[R][C];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int speed = Integer.parseInt(st.nextToken());
			int dirIdx = Integer.parseInt(st.nextToken())-1;
			int size = Integer.parseInt(st.nextToken());
			//상어 객체 생성
			Shark tmp = new Shark(speed, dirIdx, size);
			//해당 객체 격자판에 넣어주기
			board[x][y] = tmp;
		}

		//어부가 이동하는 만큼
		for (int pos = 0; pos < C; pos++) {
			for (int i = 0; i < R; i++) {
				//어부위치에서 해당 열에 가장 가까운 상어를 잡음 
				if(board[i][pos] != null) {
					//잡은상어 사이즈 더해주기
					answer += board[i][pos].size;
					//상어잡음
					board[i][pos] = null;
					//한마리 잡았으니 break
					break;
				}
			}
			//이동
			move();
		}
		
		System.out.println(answer);
	}
}
