import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution { // SWEA_6808
	static int TC;
	static int[] player;
	static int[] enemy;
	static final int MAX_SIZE = 18; // 카드 숫자 범위
	static List<Integer> card;
	static int win;
	static int lose;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc < TC + 1; tc++) { // TC
			StringBuilder sb = new StringBuilder();
			// 카드: 1 ~ 18, 선택: 9장, 상대 카드: 내가 없는 번호들의 집합  // 
			win = 0;
			lose = 0;
			player = new int[10]; // 본인 카드
			enemy = new int[10]; // 상대 카드
			boolean[] visited = new boolean[10]; // 뽑은 카드 방문 처리용
			List<Integer> number = new ArrayList<Integer>(); // 카드 뽑은 순서 저장
			
			// 카드 번호 모음
			card = new ArrayList<Integer>();
			for (int i = 1; i < MAX_SIZE + 1; i++) {
				card.add(i);
			}

			// 본인 카드 초기화
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < 10; i++) {
				int j = Integer.parseInt(st.nextToken());
				player[i] = j;
				
				// 본인 카드의 수를 카드 번호 목록에서 제거함
				// 남은 카드는 상대방의 카드가 됨.
				card.remove(card.indexOf(j));  
			}

			// 상대 카드 초기화
			for (int i = 1; i < 10; i++) {
				enemy[i] = card.remove(0);
			}
			
			cardChoice(0, number, visited);
			
			sb.append("#" + tc + " ").append(win + " " + lose);
			System.out.println(sb.toString());

		} // TC
	}

	// 9장의 카드 중 9개를 뽑는 순서의 조합
	private static void cardChoice(int cnt, List<Integer> number, boolean[] visited) {
		if (cnt == 9) {
			int pScore = 0;  // 본인 점수
			int eScore = 0;  // 상대 점수
			for (int i = 1; i < number.size() + 1; i++) { // 1 ~ 9
				int temp = number.get(i - 1);  // 임의의 순서를 뽑음.
				
				// 카드 승패 비교
				if (player[i] > enemy[temp]) {
					pScore += (player[i] + enemy[temp]);
				} else if (player[i] < enemy[temp]) {
					eScore += (player[i] + enemy[temp]);
				}
			}
			
			// 라운드당 승, 패 누적
			if (pScore > eScore) {
				win++;
			} else if(pScore < eScore) {
				lose++;
			}
			return;
		}
		
		// 9장 중 9장을 뽑는 순서의 조합 구하기.
		for (int i = 1; i < 10; i++) {
			if (visited[i] == true) {
				continue;
			}
			else if (visited[i] == false) {
				visited[i] = true;
				number.add(i);
				cardChoice(cnt + 1, number, visited);
				visited[i] = false;
				number.remove(number.size() - 1);
			}
		}
	}


}
