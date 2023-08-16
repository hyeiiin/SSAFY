import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_6987_고영훈 {
	// T: 팀 수
	// M: 매치 수 = C(6, 2)
	final static int T = 6;
	final static int M = 15;
	// 입력받은 각 팀의 승, 무, 패 값을 저장합니다.
	final static Team[] teams = new Team[T];
	// 각 매치에 해당하는 팀을 저장합니다.
	final static Match[] matches = new Match[M];
	// 입력값이 말이 되는지 체크하기 전에 0으로 초기화하고 말이 되면 1로 바꿉니다.
	static int answer;

	/**
	 * 각 팀의 승, 무, 패
	 */
	static class Team {
		int win;
		int draw;
		int loss;

		public Team(int win, int draw, int loss) {
			this.win = win;
			this.draw = draw;
			this.loss = loss;
		}
	}

	/**
	 * 매치별 맞짱깔 두 팀
	 */
	static class Match {
		final int a;
		final int b;

		public Match(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}

	/**
	 * 입력값이 말이 되면 answer를 1로 바꾸고 아니면 아무것도 안합니다.
	 * 
	 * @param mIndex: 매치 인덱스
	 */
	private static void kickOff(final int mIndex) {
		// 입력값이 말이 되는 걸로 밝혀졌으면(answer==1) 후다닥 나갑니다.
		if (answer == 1) {
			return;
		}
		// 마지막 경기까지 했으면 입력값이 말이 되는 겁니다.
		if (mIndex == M) {
			answer = 1;
			return;
		}
		// 승패 증감이 가능한 경우에만 경우의 수를 지정해서 다음 경기를 진행하고 아니면 무시합니다.
		// 호출이 끝나면 변경한 승패를 다시 복구합니다.
		final Match m = matches[mIndex];
		final Team a = teams[m.a];
		final Team b = teams[m.b];
		if (a.win > 0 && b.loss > 0) {
			a.win--;
			b.loss--;
			kickOff(mIndex + 1);
			a.win++;
			b.loss++;
		}
		if (a.loss > 0 && b.win > 0) {
			a.loss--;
			b.win--;
			kickOff(mIndex + 1);
			a.loss++;
			b.win++;
		}
		if (a.draw > 0 && b.draw > 0) {
			a.draw--;
			b.draw--;
			kickOff(mIndex + 1);
			a.draw++;
			b.draw++;
		}
	}

	public static void main(String[] args) throws Exception {
		final StringBuilder sb = new StringBuilder();
		final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			// sumFlag: 입력받은 승,무,패 값의 합이 5가 아니면 0을 반환하기 위해 선언한 플래그입니다.
			boolean sumFlag = false;
			// mIndex: 조합을 만들기 위해 사용하는 매치 인덱스입니다.
			int mIndex = 0;
			final StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < T; j++) {
				final int win = Integer.parseInt(st.nextToken());
				final int draw = Integer.parseInt(st.nextToken());
				final int loss = Integer.parseInt(st.nextToken());
				teams[j] = new Team(win, draw, loss);
				for (int k = 0; k < j; k++) {
					matches[mIndex++] = new Match(j, k);
				}
				if (win + draw + loss != 5) {
					sumFlag = true;
				}
			}
			// 검증한 결과를 출력합니다.
			if (sumFlag) {
				sb.append("0 ");
			} else {
				answer = 0;
				kickOff(0);
				sb.append(answer + " ");
			}
		}
		System.out.println(sb);
	}
}
