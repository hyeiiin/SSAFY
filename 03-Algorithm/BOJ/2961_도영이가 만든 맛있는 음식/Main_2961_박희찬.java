import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int N, S, B;
	static int s, b;
	static int[][] lst;
	static boolean[] use;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		lst = new int[N][2];
		use = new boolean[N];
		
		// 2차원 배열에 음식 정보 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			lst[i][0] = S; 
			lst[i][1] = B;
		}

		cooKing(0);
		
		System.out.println(min);
		
	} // Main

	static void cooKing(int idx) {
		if (idx == N ) {
			// 공집합 제외
			int fcnt = 0;
			for (int i = 0; i < N; i++) {
				if (!use[i]) fcnt++;					
			}
			
			if (fcnt == N) {
				return;
			}
		
			// 신맛, 쓴맛 계산
			s = 1;
			b = 0;
			for (int i = 0; i < N; i++) {
				if (use[i]) {
					s *= lst[i][0];
					b += lst[i][1];
				}
			}
			min = Math.min(min, Math.abs(s - b));  // 차이 최솟값 찾기
			return;
		}
		
		// 부분 집합 생성
		use[idx] = true;
		cooKing(idx + 1);
		use[idx] = false;
		cooKing(idx + 1);
	}
	
}
