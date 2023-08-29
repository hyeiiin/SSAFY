import java.io.*;
import java.util.*;

public class Main_17143_김현영 {

	static int r, c, m;
	static int sizeSum, maxShark;	//낚시한 상어의 크기 합, 상어배열크기
	static int[][] map; // 낚시판
	static Shark[] shark; // 상어들을 저장하는 배열

	// 상어 이동시키기
	static void moveAllShark() {
		// 낚시판을 하나씩 탐색하며 상어가 있다면 이동시키고 원래위치에서 제거
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if (map[i][j] != 0) {
					move(map[i][j], shark[map[i][j]].s);
					map[i][j] = 0; // 원래위치에서 상어 제거
				}
			}
		}

		// 낚시판에 상어 1마리씩 두기
		// 상어 배열을 탐색하며 낚시판에 두고 낚시판에 있는 상어가 현재 상어보다 작다면 잡아먹기
		for (int i = 0; i < shark.length; i++) {
			if (shark[i] == null || !shark[i].status)
				continue;

			int nr = shark[i].r;
			int nc = shark[i].c;
			if (map[nr][nc] < shark[i].z) {
				if (map[nr][nc] != 0)
					shark[map[nr][nc]].status = false;
				map[nr][nc] = shark[i].z;
			} else {
				shark[i].status = false;
			}
		}

	}// moveAllShark end

	/**
	 * @param num    상어 번호
	 * @param remain 남아있는 이동영역 수
	 */
	static void move(int num, int remain) {
		// 위로 이동
		if (shark[num].d == 1) {
			remain = remain % ((r - 1) * 2);
			if (shark[num].r - remain <= 1) {
				remain = remain - (shark[num].r - 1);
				shark[num].r = 1;
				shark[num].d = 2;
				move(num, remain);
			} else {
				shark[num].r = shark[num].r - remain;
			}
		}
		// 아래로 이동
		else if (shark[num].d == 2) {
			remain = remain % ((r - 1) * 2);
			if (shark[num].r + remain >= r) {
				remain = remain - (r - shark[num].r);
				shark[num].r = r;
				shark[num].d = 1;
				move(num, remain);
			} else {
				shark[num].r = shark[num].r + remain;
			}
		}
		// 오른쪽으로 이동
		else if (shark[num].d == 3) {
			remain = remain % ((c - 1) * 2);
			if (shark[num].c + remain >= c) {
				remain = remain - (c - shark[num].c);
				shark[num].c = c;
				shark[num].d = 4;
				move(num, remain);
			} else {
				shark[num].c = shark[num].c + remain;
			}
		}
		// 왼쪽으로 이동
		else if (shark[num].d == 4) {
			remain = remain % ((c - 1) * 2);
			if (shark[num].c - remain <= 1) {
				remain = remain - (shark[num].c - 1);
				shark[num].c = 1;
				shark[num].d = 3;
				move(num, remain);
			} else {
				shark[num].c = shark[num].c - remain;
			}
		}
	} // move end

	// 낚시하기
	static void fishing(int kingC) {
		for (int i = 0; i <= r; i++) {
			if (map[i][kingC] != 0) {
				sizeSum += map[i][kingC];
				shark[map[i][kingC]].status = false;
				map[i][kingC] = 0;
				break;
			}
		}
	}

	// 상어의 정보를 가진 클래스
	static class Shark {
		int r;
		int c;
		int s;
		int d;
		int z;
		boolean status;

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.status = true;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken()); // 낚시판 행 개수
		c = Integer.parseInt(st.nextToken()); // 낚시판 열 개수
		m = Integer.parseInt(st.nextToken()); // 상어 개수

		// 상어가 한마리도 없다면 0 출력하고 종료
		if (m == 0) {
			System.out.println(0);
			return;
		}

		// 상어 정보 입력
		shark = new Shark[10001];	//상어의 사이즈 중 최대 크기인 10000까지 사용
		maxShark = 0;	//입력받은 상어 중 가장 크기가 큰 값 저장
		map = new int[r + 1][c + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int sharkR = Integer.parseInt(st.nextToken()); // 행좌표
			int sharkC = Integer.parseInt(st.nextToken()); // 열좌표
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 이동방향
			int z = Integer.parseInt(st.nextToken()); // 크기
			maxShark = maxShark < z ? z : maxShark;
			shark[z] = new Shark(sharkR, sharkC, s, d, z);
			map[sharkR][sharkC] = z;
		}

		//상어배열을 상어 중 가장 크기가 큰 값까지 잘라내기
		shark = Arrays.copyOf(shark, maxShark + 1);

		sizeSum = 0;
		for (int i = 1; i <= c; i++) {
			fishing(i);// 낚시
			moveAllShark(); // 상어들 이동
		}

		System.out.println(sizeSum);
	}
}