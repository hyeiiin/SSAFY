package swea;

import java.util.*;
import java.io.*;

public class Solution_4013_김태훈 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int k;
	static int magnet[][], info[][];
	static boolean visit[];
	static void init() throws IOException{
		k = Integer.parseInt(br.readLine());
		magnet = new int[4][8];
		info = new int[k][2];
		
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 8; j++) {
				// i = 자석번호, j = 톱날 n/s
				// n = 0, s = 1,
				magnet[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			info[i][0] = Integer.parseInt(st.nextToken())-1;	//자석번호
			info[i][1] = Integer.parseInt(st.nextToken());	//회전방향
		}
	}
	
	static void solve() {
		for (int i = 0; i < k; i++) {
			visit = new boolean[4];
			check(info[i][0], info[i][1]);
		}
		//정답 적는곳
		//1번 자석 s면 1점, 2번자석 s면 2점, 3번자석 s면 4점, 4번자석 s면 8점
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			if(magnet[i][0] == 1) {
				sum += Math.pow(2, i);
			}
		}
		sb.append(sum);
	}
	
	//회전 메소드
	static void turn(int num, int dir) {
		//1이면 ->, -1이면 <-
		if(dir == 1) {
			int temp = magnet[num][7];
			for (int i = 7; i > 0; i--) {
				magnet[num][i] = magnet[num][i-1];
			}
			magnet[num][0] = temp;
		}
		else {
			int temp = magnet[num][0];
			for (int i = 1; i < 8; i++) {
				magnet[num][i-1] = magnet[num][i];
			}
			magnet[num][7] = temp;
		}
	}
	
	//2번 idx랑 6번 idx 다른 극인지 확인하는 메소드
	static void check(int num, int dir) {
		visit[num] = true; // 현재 자석 방문체크
		int reverseDir = dir*-1; // 반대 방향 설정

		// 왼쪽 자석과 비교
		if (num - 1 >= 0 && !visit[num - 1]) {
			if (magnet[num][6] != magnet[num - 1][2]) {
				check(num - 1, reverseDir);
			}
		}

		// 오른쪽 자석과 비교
		if (num + 1 < 4 && !visit[num + 1]) {
			if (magnet[num][2] != magnet[num + 1][6]) { 
				check(num + 1, reverseDir);
			}
	    }

	    turn(num, dir);
	}

	
	
	public static void main(String[] args) throws IOException{

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			init();
			solve();
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
