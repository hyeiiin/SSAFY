package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6987_탁하윤 {
	static int score[][];
	static int[] t1 = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};	// 경기 순서 team1
	static int[] t2 = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};	// 경기 순서 team2
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 4; i++) {	// 입력: 네 가지 결과
			st = new StringTokenizer(br.readLine());
			
			score = new int[6][3];	// 6개국
			int sum = 0;	// 경기수 체크
			
			for(int j=0; j<6; j++) {	// 결과 값 초기화
				score[j][0] = Integer.parseInt(st.nextToken());	// 승
				score[j][1] = Integer.parseInt(st.nextToken());	// 무
				score[j][2] = Integer.parseInt(st.nextToken());	// 패
				
				sum += score[j][0]+score[j][1]+score[j][2];	// 경기 승, 무, 패 합
			}
			
			if(sum != 30) {	// 경기 승, 무, 패 합이 30이 아니라면, 불가능한 결과
				sb.append(0).append(" ");
				continue;
			}
			
			if(back(0)) {	// 예측 가능한 결과라면 1, 아니라면 0
				sb.append(1).append(" ");
			} else {
				sb.append(0).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
	
	private static boolean back(int match) {	// match: 현재까지 진행된 경기 수
		if(match == 15) {	// 15번 모두 했다면 예측 가능한 결과
			return true;
		}
		
		if(score[t1[match]][0]>0 && score[t2[match]][2]>0) {	// t1이 이긴 경우
			score[t1[match]][0]--;	// 승 하나 빼기
			score[t2[match]][2]--;	// 패 하나 빼기
			if(back(match+1)) return true;	// 다음 경기
			score[t1[match]][0]++;
			score[t2[match]][2]++;
		}
		
		if(score[t1[match]][2]>0 && score[t2[match]][0]>0) {	// t1이 진 경우
			score[t1[match]][2]--;
			score[t2[match]][0]--;
			if(back(match+1)) return true;
			score[t1[match]][2]++;
			score[t2[match]][0]++;
		}
		
		if(score[t1[match]][1]>0 && score[t2[match]][1]>0) {	// 비긴 경우
			score[t1[match]][1]--;
			score[t2[match]][1]--;
			if(back(match+1)) return true;
			score[t1[match]][1]++;
			score[t2[match]][1]++;
		}
		
		return false;	// 여기 까지 왔다면 불가능한 결과
	}

}
