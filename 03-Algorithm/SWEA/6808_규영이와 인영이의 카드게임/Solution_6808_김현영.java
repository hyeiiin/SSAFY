import java.io.*;
import java.util.*;

public class Solution_6808_김현영 {

	static List<Integer> cardK; // 규영이 카드
	static List<Integer> cardY; // 인영이 카드
	static List<Integer> nowCardY; // 인영이의 카드 순서 저장
	static int scoreK, scoreY; 	// 매 라운드의 규영이, 인영이 점수
	static int win, lose; 		// 승패 경우의 수 저장
	static boolean[] isVisited; //인영이 카드 방문처리
	
	static void getCardY(int count) {
		if (count == 9) {
			// 라운드 규영이, 인영이 점수 초기화
			scoreK = 0;
			scoreY = 0; 
			// 카드 게임시작. 더 큰 수의 카드를 내는 사람에게 두 카드의 합을 점수로 줌
			for (int i = 0; i < 9; i++) {
				int sum = cardK.get(i) + nowCardY.get(i);
				if (cardK.get(i) > nowCardY.get(i))
					scoreK += sum;
				else
					scoreY += sum;
			}
			//승패 나누기
			if (scoreK > scoreY)
				win++;
			else if ( scoreK < scoreY)
				lose++; 
			return;
		}
		for (int i = 0; i < 9; i++) {
			if (!isVisited[i]) {
				nowCardY.add(cardY.get(i)); // 현재 카드 순서에 카드 저장
				isVisited[i] = true;	//방문처리
				getCardY(count+1); // 다음 카드 찾기
				int index = cardY.indexOf(nowCardY.get(nowCardY.size()-1));
				isVisited[index] = false; //백트래킹
				nowCardY.remove(nowCardY.size() - 1);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			// 1-18 카드 전체
			Set<Integer> card = new HashSet<>();
			for (int i = 1; i <= 18; i++) {
				card.add(i);
			}

			// 규영이 카드 입력
			cardK = new ArrayList<Integer>();
			for (int i = 0; i < 9; i++) {
				int n = Integer.parseInt(st.nextToken());
				cardK.add(n);
				card.remove(n); // 전체 카드에서 규영이 카드 삭제
			}
			// 인영이 카드 입력
			cardY = new ArrayList<Integer>(card);
			
			win = 0;
			lose = 0;
			nowCardY = new ArrayList<>();
			isVisited = new boolean[9]; 
			getCardY(0);

			// 출력
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			sb.append(win).append(" ").append(lose);
			System.out.println(sb.toString());
		}
	}
}
