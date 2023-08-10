package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5215_전상혁 {
	static int N, L, taste[], cal[];
	static int max_sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#"+tc+" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); //재료의 수
			L = Integer.parseInt(st.nextToken()); //제한 칼로리
			max_sum = 0;
			taste = new int[N];
			cal = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				taste[i] = Integer.parseInt(st.nextToken()); //맛 점수
				cal[i] = Integer.parseInt(st.nextToken()); //칼로리	
			}
			
			combination(0, 0, 0);
				
			sb.append(max_sum).append("\n");
		}
		System.out.println(sb);
	}
	private static void combination(int cnt, int ts, int cs) {
		//칼로리의 합이 L 보다 크면 안됨
		if (cs>L) {
			return;
		}
		if (cs< L) {
			max_sum = Math.max(max_sum, ts);
		}
		if (cnt==N) {
			return;
		}
		
		combination(cnt+1, ts+taste[cnt], cs+cal[cnt]);

		combination(cnt+1, ts, cs);
		

	}

}
