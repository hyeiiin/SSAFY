package todo.lesson._0816;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_6987_최지웅 {
	
	static class Result {
		int win, draw, lose;
	}
	
	static List<Result> results;
	
	static class Match {
		int home, away;
		
		Match(int home, int away) {
			this.home = home;
			this.away = away;
		}

		@Override
		public String toString() {
			return "Match [home=" + home + ", away=" + away + "]";
		}
		
	}
	
	static List<Match> matches;
	
	static boolean sumCheck() {
		
		int matchCnt;
		for (int i = 0; i < 6; i++) {
			matchCnt = 0;
			matchCnt += results.get(i).win;
			matchCnt += results.get(i).draw;
			matchCnt += results.get(i).lose;
			if (matchCnt != 5) return false;
		}
		return true;
	}
	
	static boolean simulCheck(int index) {
		
		boolean possible = false;
		
		if (index >= 15) {
			return true;
		} 		
		else {
			
			int home = matches.get(index).home;
			int away = matches.get(index).away;
			
			Result homeRes = results.get(home);
			Result awayRes = results.get(away);
			
			
			if (homeRes.win > 0 && awayRes.lose > 0) {
				homeRes.win--;
				awayRes.lose--;
				if (!possible) possible = simulCheck(index + 1);
				awayRes.lose++;
				homeRes.win++;
			}
			
			if (homeRes.draw > 0 && awayRes.draw > 0) {
				homeRes.draw--;
				awayRes.draw--;
				if (!possible) possible = simulCheck(index + 1);
				awayRes.draw++;
				homeRes.draw++;
			}
			
			if (homeRes.lose > 0 && awayRes.win > 0) {
				homeRes.lose--;
				awayRes.win--;
				if (!possible) possible = simulCheck(index + 1);
				awayRes.win++;
				homeRes.lose++;
			}
		}
		
		return possible;
	}
	
	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		matches = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 6; j++) {
				matches.add(new Match(i, j));
			}
		}
		
		for (int r = 1; r <= 4; r++) {
			results = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				results.add(new Result());
				results.get(i).win = Integer.parseInt(st.nextToken());
				results.get(i).draw = Integer.parseInt(st.nextToken());
				results.get(i).lose = Integer.parseInt(st.nextToken());
			}
			
			final int POSSIBLE = 1;
			final int IMPOSSIBLE = 0;
			
			if (sumCheck() && simulCheck(0)) {
				sb.append(POSSIBLE);
			} else {
				sb.append(IMPOSSIBLE);
			}
			sb.append(" ");
		}
		
		System.out.println(sb);
	}

}
