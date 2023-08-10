import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_6808_한정수 {
	
	static boolean[] card_selected;
	static int[] card_fixed;
	static int vic_cnt = 0;
	static int lose_cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
	
		
		
		
		for (int test_case=1 ; test_case<=T ; test_case++) {
			card_selected = new boolean[19]; // 0 1 2 .... 18 = false
			card_fixed = new int[10];
			st = new StringTokenizer(br.readLine());
			int idx = 1;
			while(st.countTokens()!= 0) {
				int temp = Integer.parseInt(st.nextToken());
				//일단 고정된 애들 골라내고.
				card_selected[temp] = true;
				card_fixed[idx] = temp;
				idx += 1;
			}

			

			
			
			perm(0, 1, 0, 0);
			System.out.printf("#%d %d %d", test_case, vic_cnt, 362880-vic_cnt);
			System.out.println();
			
			vic_cnt = 0;
			lose_cnt = 0;

			// 둘이 낸 카드를 비교.
			// 높은 사람이 두 카드 합만큼 점수 +
			// 
		}
	}
	public static void perm(int cnt, int card_cur, int score_A, int score_B) {
		if(cnt == 9) {
			if(score_A > score_B) {
				vic_cnt += 1;
			}
			return;
		}
		for(int i=1 ; i<=18 ; i++) {
			if(card_selected[i]) {
				continue;
			}
			card_selected[i] = true;
			if (card_fixed[card_cur] > i) {
				//규영(고정)이가 이기면
				perm(cnt+1, card_cur+1, score_A+card_fixed[card_cur]+i, score_B);
			}
			else {
				//인영이가 이기면
				perm(cnt+1, card_cur+1, score_A, score_B+card_fixed[card_cur]+i);
				
			}
			card_selected[i] = false;
		}
	}

}
