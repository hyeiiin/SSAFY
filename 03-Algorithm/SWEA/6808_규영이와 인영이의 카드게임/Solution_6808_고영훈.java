import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_고영훈 {
	// FACTORIAL_9: 9!
	// CARDS: 총 카드 수
	// HANDS: 들고 있는 카드 수
	// myHand: 내가 순서대로 내는 카드 배열
	// win: 내가 이기는 경우의 수
	final static int FACTORIAL_9 = 362880;
	final static int CARDS = 18;
	final static int HANDS = CARDS / 2;
	final static int[] myHand = new int[HANDS];
	static int win;

	/**
	 * 순열: B가 카드 내는 순서의 경우를 구하고 점수를 계산해서 승패에 따라 win 갱신
	 * 
	 * @param count: 상대방이 낸 카드 수
	 * @param deck: 내 덱 + 상대 덱
	 * @param score: 내 점수 - 상대방 점수
	 */
	private static void permutation(final int count, final long deck, final int totalScore) {
		if (count == HANDS) {
			if (totalScore > 0) {
				win++;
			}
			return;
		}
		for (int enemyCard = 1; enemyCard <= CARDS; enemyCard++) {
			// 내 덱에 있거나 상대방 덱에 있는 카드면 스킵
			final long mask = 1 << enemyCard;
			if ((deck & mask) != 0) {
				continue;
			}
			// 내가 낸 카드와 상대방이 낸 카드를 비교해서 점수 계산
			final int myCard = myHand[count];
			final int score = myCard + enemyCard;
			permutation(count + 1, deck | mask, totalScore + (myCard > enemyCard ? score : -score));
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		// 입력 시작
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			final StringTokenizer st = new StringTokenizer(br.readLine());
			long deck = 0;
			for (int i = 0; i < HANDS; i++) {
				final int card = Integer.parseInt(st.nextToken());
				myHand[i] = card;
				deck |= 1 << card;
			}
			// 입력 끝
			win = 0;
			permutation(0, deck, 0);
			final int lose = FACTORIAL_9 - win;
			sb.append("#" + t + " " + win + " " + lose + "\n");
		}
		System.out.println(sb);
	}
}
