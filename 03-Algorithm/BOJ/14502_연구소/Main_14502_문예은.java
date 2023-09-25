import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_문예은 {

	// 연구소
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] del = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int R, C, pathSize, max, lab[][];
	static ArrayList<Point> virus, path;

	public static void main(String[] args) throws IOException {
		/*
		 * 바이러스 - 사방으로 퍼짐 벽 세우기 - 3번 고정
		 * 
		 * 0 있는 좌표 담기 - 3개씩 선택하며 벽 세우기 -> 중복X순서X - 조합! 2 좌표 담기 - 그때마다 2 퍼뜨려서 0 세어보기
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		lab = new int[R][C];
		virus = new ArrayList<>();
		path = new ArrayList<>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				lab[r][c] = Integer.parseInt(st.nextToken());
				if (lab[r][c] == 2) // virus
					virus.add(new Point(r, c));
				else if (lab[r][c] == 0) // hallway
					path.add(new Point(r, c));
			}
		}
		pathSize = path.size(); // 초기 0 개수
		max = 0; // 최종 출력할 최대 안전영역 개수
		boolean[] selected = new boolean[pathSize];
		buildWall(selected, 0, 0, 3); // 전체배열 개수, 선택배열, 시작점, 깊이, 뽑을 개수
		System.out.println(max);
	}

	private static void buildWall(boolean[] selected, int start, int depth, int r) {
		if (depth == r) {
			int[][] copy = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					copy[i][j] = lab[i][j];
				}
			}
			for (int i = 0; i < pathSize; i++) {
				if (selected[i]) { // 해당 인덱스의 통로좌표에 벽 세우기
					Point cur = path.get(i);
					copy[cur.r][cur.c] = 1;
				}
			}
			safeRangeCount(copy);
			return;
		}
		for (int i = start; i < pathSize; i++) {
			if (!selected[i]) {
				selected[i] = true;
				buildWall(selected, i + 1, depth + 1, r);
				selected[i] = false;
			}
		}
	}

	private static void safeRangeCount(int[][] copy) {
		int safeCnt = pathSize - 3; // 초기 0 개수 (lab배열의 0 개수 - 벽 3개 추가한 개수)
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < virus.size(); i++) {
			queue.add(virus.get(i)); // 바이러스 좌표, 큐에 담기
		}
		while (!queue.isEmpty()) {
			Point spread = queue.poll(); // 바이러스 하나씩 빼면서 확산시키기
			for (int d = 0; d < 4; d++) {
				int dr = spread.r + del[d][0];
				int dc = spread.c + del[d][1];
				if (dr < 0 || dr >= R || dc < 0 || dc >= C)
					continue;
				if (copy[dr][dc] != 0)
					continue;
				copy[dr][dc] = 2;
				queue.offer(new Point(dr, dc));
				safeCnt--;
			}
		}
		max = Math.max(max, safeCnt);
	}

}
