package ssafy.swea;

import java.io.*;
import java.util.*;

// 4013. 특이한 자석
public class Solution_4013_김아현 {
	
	static int k;
	static int[] magnetDir = new int[4];;
	static int[][] magnets, rotation;
 	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
		
		
		for (int test = 1; test <= t; test++) {
			sb.append("#").append(test+" ");
			k = Integer.parseInt(br.readLine()); // 자석을 회전시키는 횟수
			magnets = new int[4][8]; // 4개의 자석의 자성 정보 (빨간색 화살표 위치의 날부터 시계방향 순서)
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			rotation = new int[k][2]; // 자석 번호, 회전 방향 (1:시계방향  -1:반시계방향) 
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				rotation[i][0] = Integer.parseInt(st.nextToken())-1; // 자석 번호 (0번 ~ 3번)
				rotation[i][1] = Integer.parseInt(st.nextToken()); // 회전 방향
			}
			
			// k번 회전
			for (int r = 0; r < k; r++) {
				
				isEqual(r);
				startRotate(rotation[r][0], rotation[r][1]); // 회전시작 (가장 먼저 회전하려는 타겟, 타겟의 회전 방향)
			}
			sb.append(getScore()).append("\n");
		}
	
		System.out.println(sb.toString());
	}
 	
 	static void startRotate(int target, int dir) {
 		for (int i = 0; i < 4; i++) {
 			if(magnetDir[i] == 0) continue;
 			else {
 				rotate(i, magnetDir[i]);
 			}
 		}
 	}
 
 	/**
 	 * 붙어있는 자석끼리 자성이 같은지 확인하느 메소드
 	 * return 0:같음  1:다름
 	 */
 	static void isEqual(int cnt) {
 		Arrays.fill(magnetDir, 0);
 	
 		magnetDir[rotation[cnt][0]] = rotation[cnt][1];
 		
 		// 오른쪽
 		for (int i = rotation[cnt][0]; i <= 4-rotation[cnt][0]; i++) {
 			
 			if(i+1 < 4) {
 	 			int m1 = magnets[i][2];
 	 			int m2 = magnets[i+1][6];
 	 			
 	 			if(m1 == m2) {
 					break;
 				}else {
 					magnetDir[i+1] = -magnetDir[i];
 				}
 			}else {
 				break;
 			}

 		}
 		
 		// 왼쪽
 		for (int i = rotation[cnt][0]; i > 0; i--) {
 			int m1 = magnets[i-1][2];
 			int m2 = magnets[i][6];
 			
			if(m1 == m2) {
				break;
			}else {
				magnetDir[i-1] = -magnetDir[i];
			}
		}
 	}
 	
	/**
	 * 방향값에 따라 회전하는 메소드
	 * @param target 방향을 바꿀 자석 번호
	 * @param dir 1:시계방향  2:반시계방향 
	 */
	static void rotate(int target,  int dir) {
		int temp = 0;
		switch (dir) {
		case -1:
			temp = magnets[target][0];
			for (int i = 1; i < 8; i++) {
				magnets[target][i-1] = magnets[target][i];
			}
			magnets[target][7] = temp;
			break;
		default:
			temp = magnets[target][7];
			for (int i = 7; i > 0 ; i--) {
				magnets[target][i] = magnets[target][i-1];
			}
			magnets[target][0] = temp;
			break;
		}
	}

	static int getScore() {
 		int sum = 0;
 		int count = 0;
 		for (int[] magnet : magnets) {
			sum += magnet[0]*Math.pow(2, count++);
		}
 		return sum;
 	}
}

