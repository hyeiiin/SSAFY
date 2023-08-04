package algorithm.swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14uWl6AF0CFAYD
 * 암호생성기
 * @author SSAFY
 *
 */
public class Solution_1225_박정인 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		for (int tc = 1; tc <= 10; tc++) {
			sb.append("#").append(tc).append(" ");
			br.readLine();
			
			Queue<Integer> q = new LinkedList<>();
			
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {				
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			int cycleCnt = 1;
			while (true) {
				int now = q.poll();
				now = now - cycleCnt < 0 ? 0 : now - cycleCnt;
				q.offer(now);
				if (now == 0) {					
					break;
				}
				
				if (cycleCnt == 5) {
					cycleCnt = 0;
				}
				
				
				cycleCnt++;
			}
			
			
//			int value = 1;
//			while(value != 0) {
//				for (int i = 1; i <= 5; i++) {
//					value = q.poll();
//					value -= i;
//					if (value < 0) {
//						value = 0;
//					}
//					q.offer(value);
//					if (value == 0) break;
//				}
//			}
			
			while (!q.isEmpty()) {
				sb.append(q.poll()).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
