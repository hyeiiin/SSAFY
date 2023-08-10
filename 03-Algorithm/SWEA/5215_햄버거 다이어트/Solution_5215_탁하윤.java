package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_탁하윤 {
	static int N, L, source[][];
	static int maxS;
	static boolean isSelected[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());	// Test Case 개수
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 재료의 수
			L = Integer.parseInt(st.nextToken());	// 제한 칼로리
			source = new int[N][2];					// 재료의 점수와 칼로리를 담을 배열
			isSelected = new boolean[N];			// 방문처리를 위한 boolean 배열
			maxS = 0;	// 최대 맛점수 초기화
			
			// 재료 점수와 칼로리 배열 초기화
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<2; j++) {
					source[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sourceSubset(0);	// 부분집합 재귀 호출
			System.out.printf("#%d %d\n", tc, maxS);
		}

	}
	// 부분 집합
	private static void sourceSubset(int cnt) {	// cnt : 직전까지 고려된 재료의 개수, 현재 처리할 재료의 인덱스
		
		if (cnt == N) {	// 부분집합 완성
			int s= 0, k = 0;	// 맛 점수, 칼로리
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					s += source[i][0];	// 선택된것의 맛의 총점
					k += source[i][1];	// 선택된것의 총 칼로리
				}
			}
			if(k <= L && s >= maxS) {	// 총 칼로리가 전체 칼로리 이하이고 맛의 총점이 max보다 크다면 max값 변경
				maxS = s;
			}
			return;
		}
		isSelected[cnt] = true;	// 선택된 경우
		sourceSubset(cnt+1);			
		isSelected[cnt] = false; // 부분집합 구성으로 안넣음
		sourceSubset(cnt+1);

	}

}
