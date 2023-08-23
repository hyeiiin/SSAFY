import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15683_이세은 {

	private static int n, m, min = Integer.MAX_VALUE;
	private static int[][] room;
	// 상하좌우 이동
	private static int[] moveR = { -1, 1, 0, 0 };
	private static int[] moveC = { 0, 0, -1, 1 };

	// 각 cctv마다 회전했을 때 갈 수 있는 반경 구해놓기, cctv번호가 인덱스
	// 상하좌우 0,1,2,3
	private static int[][][] types = { {}, { { 0 }, { 1 }, { 2 }, { 3 } }, { { 2, 3 }, { 0, 1 } },
			{ { 0, 3 }, { 3, 1 }, { 1, 2 }, { 2, 0 } }, { { 0, 2, 3 }, { 0, 1, 3 }, { 1, 2, 3 }, { 0, 1, 2 } },
			{ { 0, 1, 2, 3 } } };

	private static List<CCTV> cctvList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 사무실 가로
		m = Integer.parseInt(st.nextToken()); // 사무실 세로

		room = new int[n][m];

		// 사무실 정보 받기
		// 각각 cctv반경 쉽게 확인하기 위해 cctv 정보와 각각 좌표 저장해둔다.
		cctvList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] >= 1 && room[i][j] <= 5) { // cctv일 경우 정보 저장
					cctvList.add(new CCTV(room[i][j], i, j));
				}
			}
		}
		search(0, room);
		System.out.println(min);
	}

	// 반경 경우의수 확인 메서드, 분기 cnt에 대해 list의 각 cctv를 탐색 후 room에 저장
	public static void check(CCTV cctv, int[][] room, int t) {
	
		for (int j = 0; j < types[cctv.no][t].length; j++) { // 각 cctv에 저장된 방향 인덱스만큼 돌려주기
			int newR = cctv.i, newC = cctv.j; //cctv가 위치하는 곳에서 반경을 감시하기 위해 위치 고정
			int dir = types[cctv.no][t][j]; //cctv번호에 따라 정해진 분기 t에서 회전
			// 범위 유효하면서 벽이 아닌 경우 cnt분기로 탐색 계속하기, 탐색 끝났다면 다음 cctv로 넘어가기 위해 메서드 종료
			while (true) {
				newR += moveR[dir];
				newC += moveC[dir];
				//범위 유효하고 벽 아닌경우 감시 영역
				if (newR >= 0 && newC >= 0 && newR < n && newC < m && room[newR][newC] != 6) {
					if (room[newR][newC] == 0) { // 빈칸
						room[newR][newC] = -1; // 반경 표시
					}
					//만약 다른 cctv만난다면 그냥 넘어가고 다음턴에 이동
				} else {
					break;
				}
			}
		}
	}

	// 구해진 반경으로 각 cctv탐색 후 사각지대 카운트 하는 메서드
	public static void search(int cnt, int[][] room) {
		if (cnt == cctvList.size()) { // 모든 cctv탐색 완료
			int cnt0 = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (room[i][j] == 0) { // 사각지대 카운트
						cnt0++;
					}
				}
			}
			min = Math.min(min, cnt0); // 사각지대 최소인 것 찾기
			return;
		}

		CCTV searchFor = cctvList.get(cnt);
		for (int t = 0; t < types[searchFor.no].length; t++) { // 사무실에 존재하는 cctv 모두 반경 확인
			// 각 분기마다의 감시영역 독립적으로 확인하기 위한 기존 사무실 배열 복사, 복사된 배열로 반경 확인
			int[][] tmpRoom = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					tmpRoom[i][j] = room[i][j];
				}
			}
			check(searchFor, tmpRoom, t); // cctv마다 회전시키며 반경 탐색해서 감시 영역 처리
			search(cnt + 1, tmpRoom); // cnt 다음 분기 진행
		}
	}

	// cctv 정보 저장 클래스
	static class CCTV {
		int no;
		int i;
		int j;

		public CCTV(int no, int i, int j) {
			this.no = no;
			this.i = i;
			this.j = j;
		}
	}

}