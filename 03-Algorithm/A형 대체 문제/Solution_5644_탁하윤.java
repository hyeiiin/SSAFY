package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5644_탁하윤 {
	static int M, bcCnt, bc[][], A[], pathA[], B[], pathB[];
	static int[] dx = {0, 0, 1, 0, -1};	// 무 상 우 하 좌
	static int[] dy = {0, -1, 0, 1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	// 이동 시간
			bcCnt = Integer.parseInt(st.nextToken());	// 배터리 개수
			
			A = new int[] {1, 1};	// 사용자 A 시작위치
			B = new int[] {10, 10};	// 사용자 B 시작위치
			
			pathA = new int[M+1];	// 각 시간마다 A 이동 방향
			pathB = new int[M+1];	// 각 시간마다 B 이동 방향
			
			StringTokenizer stA = new StringTokenizer(br.readLine());
			StringTokenizer stB = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				pathA[i] = Integer.parseInt(stA.nextToken());
				pathB[i] = Integer.parseInt(stB.nextToken());
			}
			
			bc = new int[bcCnt][4];	// 배터리 정보
			for (int i = 0; i < bcCnt; i++) {
				st = new StringTokenizer(br.readLine());
				bc[i][0] = Integer.parseInt(st.nextToken());	// x
				bc[i][1] = Integer.parseInt(st.nextToken());	// y
				bc[i][2] = Integer.parseInt(st.nextToken());	// C : 범위
				bc[i][3] = Integer.parseInt(st.nextToken());	// P : 성능
			}
			sb.append("#").append(tc).append(" ").append(move()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int move() {
		int total = 0;	// 최대 충전 양
		for (int t = 0; t <= M; t++) {	// 각 시간마다 이동 좌표 구하기
			A[0] += dx[pathA[t]];
			A[1] += dy[pathA[t]];
			B[0] += dx[pathB[t]];
			B[1] += dy[pathB[t]];
			total += getMaxCharge();	// 최대 충전량 구하기
		}
		return total;
	}

	private static int getMaxCharge() {
		int max = 0;	// 최대 충전량
		for (int a = 0; a < bcCnt; a++) {	// 모든 배터리에 대해 최대 충전량 계산하기
			for (int b = 0; b < bcCnt; b++) {
				int sum = 0;
				int perA = charge(a, A[0], A[1]);	// 특정 배터리와 사용자 위치 거리 비교하여 배터리 충전량 받기
				int perB = charge(b, B[0], B[1]);
				
				if(a!=b) sum = perA+perB;	// 서로 다른 배터리라면 그냥 더해주기
				else sum = Math.max(perA, perB);	// 같다면 P가 더 높은걸 더해주기
				
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	private static int charge(int c, int x, int y) { // 배터리와 사용자 위치 거리 비교하여 배터리 충전량 받기
		if(Math.abs(bc[c][0]-x)+Math.abs(bc[c][1]-y) <= bc[c][2]) {
			return bc[c][3];	// 거리가 된다면 성능 리턴
		} else {
			return 0;	// 안된다면 충전할 수 없음
		}
	}

}
