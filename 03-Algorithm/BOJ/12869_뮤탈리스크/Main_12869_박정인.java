package algorithm.boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/12869
 * @author SSAFY
 *
 */
public class Main_12869_박정인 {
	static int N;	// scv 개수
	static int[] scv = {0, 0, 0};	// scv의 체력 >> 최대 3개 scv 존재
	static int[][][] HP;
	static int result = Integer.MAX_VALUE;
	
	// 뮤탈리스크가 주는 피해의 경우
	static int[][] damage = {
			{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}
	};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}
		
		// scv 체력이 각각 hp1, hp2, hp3 남았을 때 공격 횟수의 최솟값
		// scv는 0 ~ 60의 체력을 가질 수 있기에 61로 선언
		HP = new int[61][61][61];
		attack(scv[0], scv[1], scv[2], 0);
		System.out.println(result);
	}
	
	/**
	 * 
	 * @param hp1 scv[0]의 체력
	 * @param hp2 scv[1]의 체력
	 * @param hp3 scv[2]의 체력
	 * @param cnt 공격한 횟수
	 */
	private static void attack(int hp1, int hp2, int hp3, int cnt) {
		// 체력은 음수값이 나와도 0
		if (hp1 < 0)	hp1 = 0;
		if (hp2 < 0)	hp2 = 0;
		if (hp3 < 0)	hp3 = 0;
		
		// 해당 scv0, 1, 2의 hp에 해당하는 최소 공격 횟수가 0이 아니면서 cnt가 이 보다 크거나 같다면 더이상 해당 깊이로는 탐색할 필요가 없다(dfs 가지치기)
		// 전달 받은 값보다 더 작은 값이 들어가 있고, 이미  값이 있다면 다른 어택 시도
		if (HP[hp1][hp2][hp3] <= cnt && HP[hp1][hp2][hp3] != 0) {
			return;
		} else {
			// 현재 공격 횟수 저장해두기 
			HP[hp1][hp2][hp3] = cnt;
		}
		
		// 모든 scv 체력이 0이 된 경우 >> 종료 조건
		if (hp1 ==0 && hp2 == 0 && hp3 == 0) {
			result = Math.min(result, cnt);
			return;
		}

		// 3개의 scv를 공격하는 경우의 수 = 6가지
		for (int i = 0; i < 6; i++) {
			attack(hp1 - damage[i][0], hp2 - damage[i][1], hp3 - damage[i][2], cnt + 1);
		}		
	}
		
}	
