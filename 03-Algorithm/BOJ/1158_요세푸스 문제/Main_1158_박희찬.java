import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1158
public class Main { 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1 <= K <= N <= 5,000
		int N, K;
		N = Integer.parseInt(st.nextToken());  // N번째 사람 중
		K = Integer.parseInt(st.nextToken());  // K번째 사람을 제거
		
		Deque<Integer> qu = new ArrayDeque<>();
		for (int i = 1; i < N + 1; i++) {
			qu.add(i);
		}
		
		StringJoiner sj = new StringJoiner(", ");  
		while (!qu.isEmpty()) {
			for (int i = 0; i < K -1; i++) {
				qu.addLast(qu.pollFirst());  // K번째 제거 -> 맨 앞에 삽입 = 원형 유지
			}
			sj.add(String.valueOf(qu.pollFirst()));
			
		}
		
		// Print
		System.out.println("<" + sj.toString() + ">");
	} // Main

	
}
