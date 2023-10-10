package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4013_탁하윤 {
	static int mgn[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	// tc 개수
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());	// 회전 횟수
			mgn = new int[4][8];	// 1~4번 자석
			for (int i = 0; i < 4; i++) {	// 각 8개 날 극 정보 받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					mgn[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int mg = Integer.parseInt(st.nextToken());	// 회전 자석 번호
				int dir = Integer.parseInt(st.nextToken());	// 회전 방향 1: 시계, -1: 반시계
				
				find(mg-1, dir);	// 회전 가능한 자석 여부 찾고 회전하기
			}
			
			int score = 0;
			if(mgn[0][0] == 1) score+=1;
			if(mgn[1][0] == 1) score+=2;
			if(mgn[2][0] == 1) score+=4;
			if(mgn[3][0] == 1) score+=8;
			sb.append("#").append(tc).append(" ").append(score).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void find(int mg, int dir) {
		left(mg-1, -dir);	// 왼쪽 자석 회전 가능한지 확인하고 가능하면 반대 방향으로 회전하기
		right(mg+1, -dir);	// 오른쪽 자석 회전 가능한지 확인하고 가능하면 반대 방향으로 회전하기
		rotate(mg, dir);	// 자석 회전하기
	}

	private static void left(int mg, int dir) {
		if(mg<0) return;	// 1번 자석보다 왼쪽은 없으므로 return
		if(mgn[mg][2] != mgn[mg+1][6]) {	// n-1번 자석과 n번 자석의 자성이 다르다면 회전
			left(mg-1, -dir);
			rotate(mg, dir);
		}
	}
	
	private static void right(int mg, int dir) {
		if(mg>3) return;	// 4번 자석보다 오른쪽은 없으므로 return
		if(mgn[mg][6] != mgn[mg-1][2]) { // n+1번 자석과 n번 자석의 자성이 다르다면 회전
			right(mg+1, -dir);
			rotate(mg, dir);
		}
	}

	private static void rotate(int mg, int dir) {
		if(dir == 1) {	// 시계 방향 ->
			int tmp = mgn[mg][7];
			for (int i = 7; i > 0; i--) {
				mgn[mg][i] = mgn[mg][i-1];
			}
			mgn[mg][0] = tmp;
		} else {	// 반시계 방향 <-
			int tmp = mgn[mg][0];
			for (int i = 0; i < 7; i++) {
				mgn[mg][i] = mgn[mg][i+1];
			}
			mgn[mg][7] = tmp;
		}
	}

}
