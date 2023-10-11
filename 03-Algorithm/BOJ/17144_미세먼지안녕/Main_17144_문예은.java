import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17144_문예은 {
	static class Munji{
		int r;
		int c;
		int mount;
		public Munji(int r, int c, int mount) {
			this.r = r;
			this.c = c;
			this.mount = mount;
		}
	}
	static int R, C;
	static int[] airMachine;
	static int[][] del = {{-1,0},{1,0},{0,-1},{0,1}};
	private static boolean isIn(int r, int c) {
		if (r >= R || r < 0 || c >= C || c < 0) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		// 미세먼지 안녕!
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken()); // 목표 정화시간
		
		int[][] room = new int[R][C]; // 방
		int[][] room2 = new int[R][C]; // 방 복제
		airMachine = new int[2]; // 공기청정기가 차지하는 행 인덱스
		int a = 0; // 공기청정기 좌표 입력에 사용할 변수
		Queue<Munji> queue = new ArrayDeque<>(); // 미세먼지 좌표와 양 담을 큐
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				room[r][c] = Integer.parseInt(st.nextToken());
				if (room[r][c] == -1) { // 공기청정기
					airMachine[a++] = r; // 행 좌표(열은 0번으로 고정)
				} else if (room[r][c] != 0) { // 미세먼지
					queue.add(new Munji(r, c, room[r][c])); // 좌표, 먼지양
				}
			}
		}

		while (T-- > 0) {
			int qSize = queue.size();
			while (qSize-- > 0) { // 현재 큐에 들어있는 먼지에 대해서만 확산진행
				// 1. 미세먼지 확산
				Munji cur = queue.poll(); // 확산시킬 먼지 칸 꺼내기
				int curR = cur.r;
				int curC = cur.c;
				int curMount = cur.mount;
				int kan = 0; // 확산되는 칸 개수
				for (int i = 0; i < 4; i++) {
					int nextR = curR + del[i][0];
					int nextC = curC + del[i][1];
					if (isIn(nextR, nextC) && !isAirMachine(nextR, nextC)) {
						room2[nextR][nextC] += (int)curMount/5;
						kan++;
					}
				}
				room2[curR][curC] += curMount - (int)(curMount/5)*kan;
			}

			// 2. 공기청정기 작동
			// 위쪽
			for (int i = airMachine[0]-2; i >= 0; i--) {
				room2[i+1][0] = room2[i][0];
			}
			for (int i = 1; i < C; i++) {
				room2[0][i-1] = room2[0][i];
			}
			for (int i = 1; i <= airMachine[0]; i++) {
				room2[i-1][C-1] = room2[i][C-1];
			}
			for (int i = C-2; i > 0; i--) {
				room2[airMachine[0]][i+1] = room2[airMachine[0]][i];
			}
			room2[airMachine[0]][1] = 0; // 공기청정기의 청정한 바람 칸
			// 아래쪽
			for (int i = airMachine[1]+1; i < R-1; i++) {
				room2[i][0] = room2[i+1][0];
			}
			for (int i = 1; i < C; i++) {
				room2[R-1][i-1] = room2[R-1][i];
			}
			for (int i = R-2; i >= airMachine[1]; i--) {
				room2[i+1][C-1] = room2[i][C-1];
			}
			for (int i = C-2; i > 0; i--) {
				room2[airMachine[1]][i+1] = room2[airMachine[1]][i];
			}
			room2[airMachine[1]][1] = 0; // 공기청정기의 청정한 바람 칸
			// 공기청정기 위치 기록
			room2[airMachine[0]][0] = -1;
			room2[airMachine[1]][0] = -1;
			
			// 3. 남은 미세먼지 큐에 담기
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (room2[r][c] == -1 || room2[r][c] == 0) continue;
					queue.add(new Munji(r, c, room2[r][c]));
				}
			}
			// 4. room으로 다시 복제하고, room2 초기화
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					room[i][j] = room2[i][j];
				}
			}
			room2 = new int[R][C];
		}
		int answer = 0;
		for (Munji munji : queue) {
			answer += munji.mount;
		}
		System.out.println(answer);
	}
	private static boolean isAirMachine(int nextR, int nextC) {
		if (nextC == 0 && (nextR == airMachine[0] || nextR == airMachine[1])) {
			return true;
		}
		return false;
	}
	/*
	 * r*c 집
	 * 공기청정기는 항상 0번 열에 설치, 2개 행 차지
	 * 미세먼지 양 A
	 * 1. 미세먼지 확산(큐에 먼저 담기, 맵에 결과 적용)
	 * - 사방으로 확산, 단 공기청정기나 범위 밖으로는 확산일어나지 않음
	 * - 확산되는 양은 A/5, 남는 양은 A- (A/5)*확산된 칸개수
	 * 2. 공기청정기 작동
	 * - 위쪽은 반시계, 아래쪽은 시계방향 순환
	 * - 바람방향으로 미세먼지 한칸씩 이동
	 * - 공기청정기로 들어간 미세먼지는 정화됨
	 * 
	 * T초 지난 후 방에 남은 미세먼지 양 구하기
	 */
}
