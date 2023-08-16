import java.io.*;
import java.util.*;

public class Main {
	
	static int score[][];
	static int predict[][];
	static int flag = 0;
	
	static void lego(int cnt, int team1, int team2) {
		if(team2 >= 6) {
			for(int i = 0; i < 3; i++) {
				if(score[team1][i] != predict[team1][i])return;
			}
			team1 += 1;
			team2 = team1 + 1;
		}
	
		if(cnt == 15) {
			for(int i = 0; i < 3; i++) {
				if(score[team1][i] != predict[team1][i])return;
			}
			flag = 1;
			return;
		}
		
		for(int j = 0; j < 3; j++) {
			if(j == 0) {
				//win
				score[team1][0]++;
				score[team2][2]++;
				lego(cnt+1, team1, team2+1);
				score[team1][0]--;
				score[team2][2]--;
			}else if(j == 1) {
				//lose
				score[team1][1]++;
				score[team2][1]++;
				lego(cnt+1, team1, team2+1);
				score[team1][1]--;
				score[team2][1]--;
			}else if(j == 2) {
				//draw
				score[team1][2]++;
				score[team2][0]++;
				lego(cnt+1, team1, team2+1);
				score[team1][2]--;
				score[team2][0]--;
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test = 0; test < 4; test++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			score = new int[6][3];
			predict = new int[6][3];
			flag = 0;
			for(int i = 0; i < 6; i++) {
				predict[i][0] = Integer.parseInt(st.nextToken());
				predict[i][1] = Integer.parseInt(st.nextToken());
				predict[i][2] = Integer.parseInt(st.nextToken());
			}
			lego(0, 0, 1);
			sb.append(flag).append(" ");
		}
		System.out.println(sb.toString());
	}

}
