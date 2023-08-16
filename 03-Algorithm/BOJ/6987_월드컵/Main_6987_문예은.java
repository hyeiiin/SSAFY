package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_6987_문예은 {
	static int[][] matchTeam = {{0,1},{0,2},{0,3},{0,4},{0,5},{1,2},{1,3},{1,4},{1,5},{2,3},{2,4},{2,5},{3,4},{3,5},{4,5}};
	static int[] winNum = new int[6], drawNum = new int[6], loseNum = new int[6];
	static boolean iamNotGiregi; // 기자 리포트 오류 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int t = 0; t < 4; t++) { // 고정된 테스트케이스, 총 네가지의 기록지
			st  = new StringTokenizer(br.readLine());
			iamNotGiregi = false; 
			
			for (int i = 0; i < 6; i++) { // 각 팀별로 승,무,패 점수 기록
				winNum[i] = Integer.parseInt(st.nextToken());
				drawNum[i] = Integer.parseInt(st.nextToken()); 
				loseNum[i] = Integer.parseInt(st.nextToken()); 
			}
			
			
			int sum = IntStream.of(winNum).sum()+IntStream.of(drawNum).sum()+IntStream.of(loseNum).sum(); // 배열 내 원소 합 메소드
			if(sum != 30) iamNotGiregi = false; // 모든 승무패 점수 합이 30이 아니라면 오류
			else holymolyWorldCup(0); // 각 팀 매치에 따라 승무패 유효성 검증
			
			if(iamNotGiregi) sb.append(1+" "); // 가능한 결과 1, 불가능한 결과 0
			else sb.append(0+" ");
		}
		System.out.println(sb.toString());
	}

	private static void holymolyWorldCup(int matchNum) {
		if(matchNum == 15) {
			iamNotGiregi = true; // 마지막 대진까지 도달했으면 점수표 오류 없음
			return;
		}
		int team1 = matchTeam[matchNum][0]; // 팀별 대진 시작
		int team2 = matchTeam[matchNum][1];
		
		// team1 승리하는 경우
	    if(winNum[team1] > 0 && loseNum[team2] > 0) {  // 두 팀이 대진할 수 있는 점수가 남아있을 때
	    	winNum[team1]--; loseNum[team2]--; 
	    	holymolyWorldCup(matchNum+1); // 다음 대진 시작
	    	winNum[team1]++; loseNum[team2]++; 
	    }
		// team1 패배하는 경우
	    if(loseNum[team1] > 0 && winNum[team2] > 0) {  // 두 팀이 대진할 수 있는 점수가 남아있을 때
	    	loseNum[team1]--; winNum[team2]--; 
	    	holymolyWorldCup(matchNum+1); // 다음 대진 시작
	    	loseNum[team1]++; winNum[team2]++; 
	    }
		// 무승부하는 경우
	    if(drawNum[team1] > 0 && drawNum[team2] > 0) {  // 두 팀이 대진할 수 있는 점수가 남아있을 때
	    	drawNum[team1]--; drawNum[team2]--; 
	    	holymolyWorldCup(matchNum+1); // 다음 대진 시작
	    	drawNum[team1]++; drawNum[team2]++; 
	    }
	}

}
