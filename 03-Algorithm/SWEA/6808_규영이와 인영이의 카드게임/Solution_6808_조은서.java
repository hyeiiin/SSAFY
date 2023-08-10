package algo_0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808_조은서 {

	static int[] kyu; // 규영이 카드 숫자
	static int[] in; // 인영이 카드 숫자
	static boolean[] card; // 18개 카드 중 규영이 카드가 몇 번인지 확인용
	static int[] result; // 인영이 카드 조합 저장
	static boolean[] check; // 방문 여부 판별
	static int cnt; // 이긴 횟수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			kyu = new int[9]; // 규영이 카드
			in = new int[9]; // 인영이 카드
			card = new boolean[19];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				int num = Integer.parseInt(st.nextToken());
				card[num] = true; // 입력 받은 숫자는 규영이 카드 숫자임을 체크
				kyu[i] = num; // 규영이 카드 정보 추가
			}
			
			int idx = 0;
			for(int i=1; i<=18; i++) {
				if (card[i] != true) { // index 중 true로 표시된 것은 규영이 카드, false로 표시된 것은 인영이 카드
					in[idx++] = i; // 인영이 카드 정보 추가
				}
			}
			
			result = new int[9];
			check = new boolean[9];
			cnt = 0;
			perm(0);
			System.out.println("#" + tc+ " " + cnt + " " + (362880-cnt)); // 이긴 횟수, 진 횟수(=9!-이긴횟수)

		}

	}
	
	private static void perm(int index) {
		if(index==9) {
			int kyuScore = 0;
			int inScore = 0;
			for(int i=0; i<9; i++) {
				if(kyu[i] > result[i]) kyuScore += kyu[i] + result[i]; // 규영이 카드가 더 크면, 규영이 점수 추가
				else inScore += kyu[i] + result[i]; // 인영이 카드가 더 크면, 인영이 점수 추가
			}
			
			if (kyuScore > inScore) {cnt++;} // 규영이 점수가 더 높으면 규영이 이긴 횟수 증가
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(!check[i]) {
				result[index] = in[i]; // 인영이 카드에서 뽑은 숫자 저장
				check[i] = true; // 방문 표시
				perm(index+1);
				check[i]=false; // 방문 표시 해제
			}
		}	
	}

}
