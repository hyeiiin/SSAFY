import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_문예은 {
	static class Monkey{ // 이동가능 좌표, 잔여 점프횟수, 이동시간
		int r;
		int c;
		int k;
		int time;
		public Monkey(int r, int c, int k, int time) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
			this.time = time;
		}
	}
	static int C,R,K; // 가로, 세로, 말 점프 횟수
	static int[][] horseMove = {{-1,-2},{-2,-1},{-2,1},{2,-1},{1,-2},{-1,2},{2,1},{1,2}};
	static int[][] monkeyMove = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<Monkey> queue = new ArrayDeque<>(); // 원숭이 이동가능 위치 담아 탐색시킬 큐
	
	public static void main(String[] args) throws IOException {
		/*
		 * K번만 말 점프 가능, 나머지는 사방이동 가능
		 * 최소한의 동작으로 도착하는 방법, 불가능은 -1
		 * 
		 * 가로 W, 세로 H
		 * 0평지, 1 장애물(점프는 가능)
		 * 
		 * 큐 -> 좌표, K잔여횟수, 시간(최소)
		 * 
		 * visited -> 정수처리, 말 점프 잔여횟수 기록, 만약 이동하려는 좌표와 비교하여 같으면 건너뛰기, 다르면 큐에 넣기
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		K = Integer.parseInt(br.readLine()); // 말 점프 가능 횟수
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken()); // 가로 길이
		R = Integer.parseInt(st.nextToken()); // 세로 길이
		int[][] zoo = new int[R][C]; // 맵 배열
		boolean[][][] visited = new boolean[R][C][K+1]; // 최적화를 위한 방문처리배열
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				zoo[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		Monkey start = new Monkey(0, 0, 0, 0);
		queue.add(start); // 0,0 시작점에서 출발
		visited[start.r][start.c][0] = true; // 말뛰기로 온 횟수 기준으로 방문처리하기 위해 배열 초기화

		int count = Integer.MAX_VALUE; // 최종 출력할 최소이동시간
		
		while (!queue.isEmpty()) {
			Monkey cur = queue.poll(); // 큐에서 꺼내 이동시킬 원숭이
			int curR = cur.r;
			int curC = cur.c;
			int curK = cur.k;
			int depth = cur.time;
			if(curR == R-1 && curC == C-1) {
				count = Math.min(count, depth);
				System.out.println(count);
				return;
			}
			if (curK <= K-1) { // 말 점프 가능하면 , horse+monkey 이동 좌표 모두 추가
				for (int i = 0; i < horseMove.length; i++) {
					int moveR = curR + horseMove[i][0];
					int moveC = curC + horseMove[i][1];
					if(moveR >= 0 && moveR < R && moveC >= 0 && moveC < C 
							&& !visited[moveR][moveC][curK+1] && zoo[moveR][moveC]!=1) {
						queue.add(new Monkey(moveR, moveC, curK+1, depth+1));
						visited[moveR][moveC][curK+1] = true;
					}
				}
			} // 말 점프 불가능하면, 사방탐색만 진행
			for (int i = 0; i < monkeyMove.length; i++) {
				int moveR = curR + monkeyMove[i][0];
				int moveC = curC + monkeyMove[i][1];
				if(moveR >= 0 && moveR < R && moveC >= 0 && moveC < C 
						&& !visited[moveR][moveC][curK] && zoo[moveR][moveC]!=1) {
					queue.add(new Monkey(moveR, moveC, curK, depth+1));
					visited[moveR][moveC][curK] = true;;
				}
			}
		}
		System.out.println(-1);
	}
}
