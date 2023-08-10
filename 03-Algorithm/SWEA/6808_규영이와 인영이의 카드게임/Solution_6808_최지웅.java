package todo.lesson._0810;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_6808_최지웅 {
	
	static List<Integer> kyCard;
	static List<Integer> iyCard;
	
	static int[] seq = new int[9];
	
	static int kyScore;
	static int iyScore;
	
	static int winCase;
	
	static void match() {
		
		kyScore = 0;
		iyScore = 0;
		
		for (int i = 0; i < 9; i++) {
			if (kyCard.get(i) > iyCard.get(seq[i])) {
				kyScore += kyCard.get(i) + iyCard.get(seq[i]);
			} else {
				iyScore += kyCard.get(i) + iyCard.get(seq[i]);
			}
		}
		
		if (kyScore > iyScore) winCase++;
	}
	
	static void permutation(int cnt, int bits) {
		if (cnt == 9) {
			match();
		} else {
			for (int i = 0; i < 9; i++) {
				if ((bits & 1 << i) != 0) continue;
				seq[cnt] = i;
				permutation(cnt + 1, bits | 1 << i);
			}
		}
	}

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[] cards;
		final int KY = 1;
		final int IY = 0;
		
		final int TOTALCASE = 362880;	// 9!
		
		int T = Integer.parseInt(br.readLine());
		int x;
		
		for (int t = 1; t <= T; t++) {
			
			cards = new int[18 + 1];
			kyCard = new ArrayList<>();
			iyCard = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for (int n = 1; n <= 9; n++) {
				x = Integer.parseInt(st.nextToken());
				cards[x] = KY;
				kyCard.add(x);
			}
			
			for (int i = 1; i <= 18; i++) {
				if (cards[i] == IY) {
					iyCard.add(i);
				}
			}
			
			winCase = 0;
			permutation(0, 0);
			sb.append("#").append(t).append(" ").append(winCase).append(" ").append(TOTALCASE - winCase).append("\n");
		}
		System.out.println(sb);
	}

}
