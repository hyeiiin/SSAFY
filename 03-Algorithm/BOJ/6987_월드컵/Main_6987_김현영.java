import java.io.*;
import java.util.*;

public class Main_6987_김현영 {
	static int[][] table;	//경기 정보
	static boolean isAvailavle;	// 경기의 결과가 가능한지, 불가능한지 저장

	static int[] me = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4 };	//6개국에 대해서 나의 경기 횟수
	static int[] you = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };		//6개국에 대해서 상대의 경기 횟수

	static void func(int count) {
		if (count == 15) {	//경기횟수가 15번이면 가능한 결과 반환
			isAvailavle = true;
			return;
		}
		int meIdx = me[count];
		int youIdx = you[count];

		// 승
		// 나의 승이 남아 있고 상대의 패가 남아 있으면 각각 1씩 감소시키고 다음 경기 결과 확인
		if (table[meIdx][0] > 0 && table[youIdx][2] > 0) {
			table[youIdx][2]--;
			table[meIdx][0]--;
			func(count + 1);
			table[youIdx][2]++;
			table[meIdx][0]++;

		}
		// 무
		// 나의 무와 상대의 무가 남아 있으면 각각 1씩 감소시키고 다음 경기 결과 확인
		if (table[meIdx][1] > 0 && table[youIdx][1] > 0) {
			table[youIdx][1]--;
			table[meIdx][1]--;
			func(count + 1);
			table[youIdx][1]++;
			table[meIdx][1]++;
		}
		// 패
		// 나의 패가 남아 있고 상대의 승이 남아 있으면 각각 1씩 감소시키고 다음 경기 결과 확인
		if (table[meIdx][2] > 0 && table[youIdx][0] > 0) {
			table[youIdx][0]--;
			table[meIdx][2]--;
			func(count + 1);
			table[youIdx][0]++;
			table[meIdx][2]++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		round: for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			table = new int[6][3]; // 결과 표
			int totalPlay = 0; // 라운드별 경기 수
			int play = 0; // 국가별 경기 수
			for (int j = 0; j < 6; j++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				table[j][0] = win;
				table[j][1] = draw;
				table[j][2] = lose;
				totalPlay = totalPlay + win + draw + lose;
				play = win + draw + lose;
			}
			// 라운드의 경기가 30이 아니고 나라별 경기수가 5가 아니면 false
			if (totalPlay != 30 || play == 1) 
				sb.append(0).append(" ");
			//경기 횟수가 맞다면 승무패를 비교하여 결과 확인
			else {
				isAvailavle = false;
				func(0);
				if (isAvailavle) 
					sb.append(1).append(" ");
				else
					sb.append(0).append(" ");
			}

		}

		System.out.println(sb.toString());
	}
}
