package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1233_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for(int tc=1; tc<=10; tc++) {
			int N = Integer.parseInt(br.readLine());	// 정점 개수
			int result = 1;		// 유효성 검사 결과
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());	// 노드 받기
				st.nextToken();	// 노드 번호(정점)
				String str = st.nextToken();	// 노드 데이터
				if(!st.hasMoreTokens() && (str.equals("/") || str.equals("*") || str.equals("+") || str.equals("-"))) {
					// 토큰이 더 없는 경우는 리프 노드, 노드 데이터가 연산자라면 유효하지 않음
					result = 0;
				}
			}
			System.out.printf("#%d %d\n", tc, result);
		}
		
	}
}
