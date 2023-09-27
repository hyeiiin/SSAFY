import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1194_문예은 {
	// 달이 차오른다, 가자.
	static class Point{
		int r;
		int c;
		int cnt;
		int keys;
		public Point(int r, int c, int cnt, int keys) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.keys = keys;
		}
	}
	
	private static boolean isIn(int r, int c) {
		if (r >= N || r < 0 || c >= M || c < 0) {
			return false;
		}
		return true;
	}
	
	static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
	static int N,M;
	public static void main(String[] args) throws IOException {
		
		/*
		 * 미로 구성 
		 * 세로 N 가로 M (1-50)
		 * 빈칸 . 벽# 열쇠(소문자, 여러개가능) 문(대문자) 현재위치0  출구(1, 여러개가능)
		 * 
		 * 이동횟수 최소값 구하기
		 * 탈출 불가는 -1 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로 길이
		M = Integer.parseInt(st.nextToken()); // 가로 길이
		char[][] miro = new char[N][M]; // 미로 맵
		Deque<Point> path = new ArrayDeque<>(); // 이동경로 좌표 담을 큐
		String oneLine; // 미로 한줄씩 받아오기
		for (int n = 0; n < N; n++) {
			oneLine = br.readLine();
			for (int m = 0; m < M; m++) {
				miro[n][m] = oneLine.charAt(m);
				if (miro[n][m]== '0') {
					path.add(new Point(n, m, 0, 0)); // 시작점 저장
				}
			}
		}
//		System.out.println(Arrays.deepToString(miro));
		/*
		 * 시작점 0에서 시작, 1을 만나면 종료
		 * 더이상 이동할 수 없는데(큐 비었는데) 아직 1 못만나면 -1 출력
		 * 
		 * 사방탐색
		 * 방문배열 체크 - 행,열,열쇠비트값(1~32까지의 합:63)
		 * 배열범위내, 방문전, 벽이나 열쇠없는 문 아닐 때 이동
		 * 열쇠 만나면 비트마스킹 or로 추가, 문 만나면 비트마스킹 and로 검사
		 * 이동횟수 1 증가
		 */
		boolean[][][] visited = new boolean[N][M][64]; // 열쇠비트마스크 합
		int answer = -1;
		while (!path.isEmpty()) {
			Point cur = path.poll();
			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;
			int keys = cur.keys; // 열쇠 비트마스킹
			if (miro[r][c]=='1') {
				answer = cnt;
				break;
			}
			for (int i = 0; i < 4; i++) { // 사방탐색
				int nextR = r + del[i][0];
				int nextC = c + del[i][1];
				if (!isIn(nextR, nextC)) continue;
				if (visited[nextR][nextC][keys]) continue;
				if (miro[nextR][nextC]=='#') continue;
				// 대문자(문) 만났을 때
				if (miro[nextR][nextC] >= 'A' && miro[nextR][nextC] <= 'F') {
					int door = miro[nextR][nextC];
					if((keys & 1 << (door-65)) > 0) { // 문에 맞는 열쇠 있을 때
						// 해당 문 좌표, 큐에 넣고 방문처리
						path.add(new Point(nextR, nextC, cnt+1, keys));
						visited[nextR][nextC][keys] = true;
					} else { // 문에 맞는 열쇠 없을 때
						// 방문처리만
						visited[nextR][nextC][keys] = true;
					} // 소문자(열쇠) 만났을 때
				} else if (miro[nextR][nextC] >= 'a' && miro[nextR][nextC] <= 'f') { 
					// 열쇠 추가, 방문처리
					int key = miro[nextR][nextC];
					int newKey = keys | (1 << (key-97));
					visited[nextR][nextC][newKey] = true;
					path.add(new Point(nextR, nextC, cnt+1, newKey));
				} else { // 빈칸일 때
					path.add(new Point(nextR, nextC, cnt+1, keys));
					visited[nextR][nextC][keys] = true;
				}
			}
		}
		System.out.println(answer);
	}
}
