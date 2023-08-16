package algo_0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6987_조은서 {

	// 팀 별 매치
	static int home[] = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int away[] = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	static int[] win;
	static int[] draw;
	static int[] lose;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		win = new int[6];
		draw = new int[6];
		lose = new int[6];
		
		for(int tc=0; tc<4; tc++) {
			int total = 0;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<6; i++) {
				win[i] = Integer.parseInt(st.nextToken()); // 이김
				draw[i] = Integer.parseInt(st.nextToken()); // 비김
				lose[i] = Integer.parseInt(st.nextToken()); // 짐
				total += win[i] + draw[i] + lose[i];
			}
			
			// 우선 승부 결과의 합이 30이 아니면 불가능한 결과임
			if(total != 30) { 
				sb.append(0 + " ");
				continue;
			}
			
			// 결과의 합이 30이면 이제 각 팀의 승부 결과를 알아보자!
			if(backtracking(0)) { 
				sb.append(1 + " ");
			}
			else {
				sb.append(0 + " ");
			}

		}
		
		System.out.println(sb);
	}
	
	// 각 팀 승부 결과에 대해 가능한 결과인지 탐색
	private static boolean backtracking(int game) {
		if(game==15) { // 게임 15번 하면 끝!
			return true;
		}
		// 이기는 경우
		if(win[home[game]] > 0 && lose[away[game]] > 0) {
			win[home[game]]--;
			lose[away[game]]--;
			if(backtracking(game + 1)) return true;
			win[home[game]]++;
			lose[away[game]]++;
		}
		
		// 지는 경우 
		if(lose[home[game]] > 0 && win[away[game]] > 0) {
			lose[home[game]]--;
			win[away[game]]--;
			if(backtracking(game + 1)) return true;
			lose[home[game]]++;
			win[away[game]]++;
		}
		
		// 비기는 경우 
		if(draw[home[game]] > 0 && draw[away[game]] > 0) {
			draw[home[game]]--;
			draw[away[game]]--;
			if(backtracking(game + 1)) return true;
			draw[home[game]]++;
			draw[away[game]]++;
		}
				
		return false;
	}

}
