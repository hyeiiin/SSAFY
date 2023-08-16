package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/6987
 * @author SSAFY
 *
 */
public class Main_6987_박정인 {
	static int nationCnt = 6;	// 나라 개수
	static int[][] worldCup;	
	static int[][] matchGame;
	static boolean isEnd;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		for (int tc = 0; tc < 4; tc++) {
			st = new StringTokenizer(br.readLine());
			worldCup = new int[6][3];	// 6개의 나라별 승, 무, 패 저장
			boolean isCorrectResult = true;	// 정상적인 결과인지 판단
			isEnd = false;	// 모든 나라 경기가 제대로 끝난 경우를 나타내는 변수
			
			// 총 경기 횟수
			int totalGameCnt = 0;
			for (int i = 1; i < nationCnt; i++) {
				totalGameCnt += i;
			}
			
			matchGame = new int[totalGameCnt][2];	// 각 경기별 나라 매칭
			int idx = 0;	// 매칭된 게임 인덱스 
			
			// i, j => 0번: A, ... , 5번: F
			for (int i = 0; i < nationCnt - 1; i++) {
				for (int j = i + 1; j < nationCnt; j++) {
					matchGame[idx][0] = i;
					matchGame[idx][1] = j;
					idx++;
				}
			}
			
			
			// 6개국의 결과 >> 나라별 승, 무, 패 순서로 입력
			for (int i = 0; i < nationCnt; i++) {
				int win = Integer.parseInt(st.nextToken());
				int draw = Integer.parseInt(st.nextToken());
				int lose = Integer.parseInt(st.nextToken());
				
				worldCup[i][0] = win;
				worldCup[i][1] = draw;
				worldCup[i][2] = lose;
				
				// 승 + 무 + 패 는 5가 나와야 한다. >> 각 나라마다 5경기 진행
				if (win + draw + lose != 5) {
					isCorrectResult = false;
				}
			}
			
			// 나올수 없는 결과인 경우
			if (!isCorrectResult) {
				sb.append(0).append(" ");
			} else {	// 각 결과에 대해 나올 수 있는 경우
				// 백트래킹 실시
				play(0, totalGameCnt);
				
				if (isEnd) {	// 모든 나라 경기 정상 종료
					sb.append(1).append(" ");
				} else {	// 모든 나라 경기 제대로 못 마친 경우
					sb.append(0).append(" ");
				}				
				
			}
			
		}	
		
		System.out.println(sb);
	}
	
	private static void play(int matchCnt, int totalGameCnt) {
		// 모든 나라가 다 경기한 경우
		if (matchCnt == totalGameCnt) {
			isEnd = true;	// 모든 나라가 제대로 경기를 다 끝냈다. 
			return;	// 메서드 종료
		}
		
		int homeTeam = matchGame[matchCnt][0];	// 홈팀 >> A, B, C ...
		int awayTeam = matchGame[matchCnt][1];	// 원정 팀 >> A, B, C ...
		
		// if문으로 해당 내용이 0보다 커야 내부에서 재귀호출을 한다. 
		// 조건 만족 못하면 더이상 호출 안함 
		// 홈팀 승, 원정팀 패
		if (worldCup[homeTeam][0] > 0 && worldCup[awayTeam][2] > 0) {
			worldCup[homeTeam][0]--;	// 홈팀 승리 수 -1
			worldCup[awayTeam][2]--;	// 원정팀 패배 수 -1
			play(matchCnt + 1, totalGameCnt);
			
			// 원래대로 
			worldCup[homeTeam][0]++;	
			worldCup[awayTeam][2]++;
		}
		
		// 홈팀, 원정팀 무승부
		if (worldCup[homeTeam][1] > 0 && worldCup[awayTeam][1] > 0) {
			worldCup[homeTeam][1]--;
			worldCup[awayTeam][1]--;
			play(matchCnt + 1, totalGameCnt);
			worldCup[homeTeam][1]++;
			worldCup[awayTeam][1]++;
		}
		
		// 홈팀 패, 원정팀 승
		if (worldCup[homeTeam][2] > 0 && worldCup[awayTeam][0] > 0) {
			worldCup[homeTeam][2]--;
			worldCup[awayTeam][0]--;
			play(matchCnt + 1, totalGameCnt);
			worldCup[homeTeam][2]++;
			worldCup[awayTeam][0]++;
		}
	}
}