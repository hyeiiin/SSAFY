import java.io.*;
import java.util.*;

public class Main_17144_김현영 {

	static int r, c;
	static int upPos, downPos;	//공기 청정기 위치
	// 우 하 좌 상
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken()); // 방의 행 크기
		c = Integer.parseInt(st.nextToken()); // 방의 열 크기
		int t = Integer.parseInt(st.nextToken()); // 공기청정기가 작동하는 시간

		// 방 정보 입력
		int[][] room = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if (room[i][j] == -1) {
					downPos = i;	//공기청정기 아래쪽 위치
					upPos = i - 1;	//공기청정기 위쪽 위치
				}
			}
		}

		// 공기청정기 작동하는 시간
		for (int i = 0; i < t; i++) {
			// 미세먼지 확산
			room = sprayDust(room);
 
			// 공기청정기 작동
			room = afterAirClean(room);
		}

		// 방에 남은 미세먼지 합 구하기
		int dust = 0;
		for (int i = 0; i< r; i++) {
			for (int j = 0; j < c; j++) {
				if(room[i][j]>0)
					dust+= room[i][j];
			}
		}
		System.out.println(dust);

	}
	
	// 미세먼지 확산
	static int[][] sprayDust(int[][] room) {
		int[][] nextRoom = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (room[i][j] == 0)
					continue;

				int dividedDust = room[i][j] / 5;
				// 방에 먼지가 있을 경우 사방으로 확산
				for (int k = 0; k < 4; k++) {
					int nr = i + dr[k];
					int nc = j + dc[k];
					if (checkIndex(nr, nc) && room[nr][nc] != -1) {
						nextRoom[nr][nc] += dividedDust;
						room[i][j] -= dividedDust;
					}
				}
				// 확산되고 남은 먼지 저장
				nextRoom[i][j] += room[i][j];
			}
		}

		return nextRoom;
	}
	
	// 공기청정기 작동
	static int[][] afterAirClean(int[][] room) {
		//배열 복사
		int[][] cleanRoom = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				cleanRoom[i][j] = room[i][j];
			}
		}

		// 위쪽 공기청정기 작동
		int x = upPos;
		int y = 1;
		// 우
		cleanRoom[x][y] = 0;
		for (int i = 0; i < c - 2; i++, y++) {
			cleanRoom[x][y + 1] = room[x][y];
		}
		// 상
		for (int i = 0; i < upPos; i++, x--) {
			cleanRoom[x - 1][y] = room[x][y];
		}
		// 좌
		for (int i = 0; i < c - 1; i++, y--) {
			cleanRoom[x][y - 1] = room[x][y];
		}
		// 하
		for (int i = 0; i < upPos - 1; i++, x++) {
			cleanRoom[x + 1][y] = room[x][y];
		}

		// 아래쪽 공기청정기 작동
		x = downPos;
		y = 1;
		// 우
		cleanRoom[x][y] = 0;
		for (int i = 0; i < c - 2; i++, y++) {
			cleanRoom[x][y + 1] = room[x][y];
		}
		// 하
		for (int i = 0; i < r - downPos - 1; i++, x++) {
			cleanRoom[x + 1][y] = room[x][y];
		} 
		// 좌
		for (int i = 0; i < c - 1; i++, y--) {
			cleanRoom[x][y - 1] = room[x][y];
		}
		// 상
		for (int i = 0; i < r - downPos-2; i++, x--) {
			cleanRoom[x - 1][y] = room[x][y];
		}

		return cleanRoom;
	}

	static boolean checkIndex(int x, int y) {
		if (0 <= x && x < r && 0 <= y && y < c)
			return true;
		return false;
	}

}
