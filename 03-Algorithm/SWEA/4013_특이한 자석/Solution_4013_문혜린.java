package swea;

import java.util.*;
import java.io.*;

//톱니바퀴
public class Solution_4013_문혜린 {
	static boolean gear[][];
	
	//톱니바퀴 상태 변경 (상태 바꿀 톱니바퀴 번호, 회전 방향)
	public static void oper(int num, int d){
		int dTmp = d;
		boolean[][] spinGear = new boolean[4][2]; //톱니바퀴 회전 시, 확인 해야하는 톱니 저장
		for (int i = 0; i < 4; i++) {
			spinGear[i][0] = gear[i][6]; //왼쪽
			spinGear[i][1] = gear[i][2]; //오른쪽
		}
		
		//현재 톱니 회전
		if(d == -1) {
			//현재 톱니 반시계로 회전
			boolean tmp = gear[num-1][0]; //회전하기 위해 처음 톱니 상태 저장
			for (int j = 0; j < 7; j++) {
				gear[num-1][j] = gear[num-1][j+1];
			}
			gear[num-1][7] = tmp;
		}
		else if(d == 1) {
			//현재 톱니 시계로 회전
			boolean tmp = gear[num-1][7]; //회전하기 위해 마지막 톱니 상태 저장
			for (int j = 7; j > 0; j--) {
				gear[num-1][j] = gear[num-1][j-1];
			}
			gear[num-1][0] = tmp;
		}
		
		//상태 바꾸는 톱니 기준으로 왼쪽
		for (int i = num-1; i > 0; i--) {
			if(spinGear[i][0] != spinGear[i-1][1]) { //현재 톱니 왼쪽과 이전에 맞물린 톱니 오른쪽의 극이 다를 경우
				if(dTmp == -1) { //반시계 방향일 경우
					//맞물린 이전 톱니 시계로 회전
					boolean prevtmp = gear[i-1][7]; //회전하기 위해 마지막 톱니 상태 저장
					for (int j = 7; j > 0; j--) {
						gear[i-1][j] = gear[i-1][j-1];
					}
					gear[i-1][0] = prevtmp;
							
				}
				else if(dTmp == 1) { //시계 방향일 경우
					//맞물린 이전 톱니 반시계로 회전
					boolean prevtmp = gear[i-1][0]; //회전하기 위해 처음 톱니 상태 저장
					for (int j = 0; j < 7; j++) {
						gear[i-1][j] = gear[i-1][j+1];
					}
					gear[i-1][7] = prevtmp;
				}
				
				//이전 톱니로 이동하면서 방향 바꿔주기
				if(dTmp == -1) dTmp = 1;
				else if(dTmp == 1) dTmp = -1;
			}
			else {
				break;
			}
		}
		
		dTmp = d;
		//상태 바꾸는 톱니 기준으로 오른쪽
		for (int i = num; i < 4; i++) {
			if(spinGear[i-1][1] != spinGear[i][0]) { //현재 톱니 오른쪽과 다음에 맞물린 톱니 왼쪽의 극이 다를 경우
				if(dTmp == -1) { //반시계 방향일 경우
					//맞물린 다음 톱니 시계로 회전
					boolean nexttmp = gear[i][7]; //회전하기 위해 마지막 톱니 상태 저장
					for (int j = 7; j > 0; j--) {
						gear[i][j] = gear[i][j-1];
					}
					gear[i][0] = nexttmp;
				}
				else if(dTmp == 1) { //시계 방향일 경우
					//맞물린 다음 톱니 반시계로 회전
					boolean nexttmp = gear[i][0]; //회전하기 위해 처음 톱니 상태 저장
					for (int j = 0; j < 7; j++) {
						gear[i][j] = gear[i][j+1];
					}
					gear[i][7] = nexttmp;
				}
				
				//다음 톱니로 이동하면서 방향 바꿔주기
				if(dTmp == -1) dTmp = 1;
				else if(dTmp == 1) dTmp = -1;
			}
			else {
				break;
			}
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테케 개수
		for (int t = 1; t <= T; t++) {
			int K = Integer.parseInt(br.readLine()); //회전 횟수
			
			gear = new boolean[4][8];
			
			for (int i = 0; i < 4; i++) {
				//톱니바퀴 정보 입력받기
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					if(Integer.parseInt(st.nextToken())==1) { //1:S극
						gear[i][j] = true;
					}
					else { //0:N극
						gear[i][j] = false;
					}
				}
			}
			//톱니바퀴 회전
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken()); //톱니바퀴 번호
				int d = Integer.parseInt(st.nextToken()); //방향 (1:시계, -1:반시계)
				oper(num, d); //회전
			}
			
			int score = 0;
			if(gear[0][0]) { //1번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 1점
				score += 1;
			}
			if(gear[1][0]) { //2번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 2점
				score += 2;
			}
			if(gear[2][0]) { //3번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 4점
				score += 4;
			}
			if(gear[3][0]) { //4번 톱니바퀴의 12시방향이 N극이면 0점, S극이면 8점
				score += 8;
			}
			sb.append("#").append(t).append(" ").append(score).append("\n");
		}
		System.out.println(sb);
	}

}

