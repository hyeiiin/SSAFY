package ssafy.Boj;

import java.io.*;
import java.util.*;

// 우선순위큐
public class Main_11286_김아현 {

	private static PriorityQueue<int[]> pqueue; // 최소값을 꺼내기 위해 숫자들을 넣을 공간. (우선순위큐)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine()); // 연산의 개수;
		pqueue = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// 첫번째 원소(절댓값)기준 정렬. 절댓값이 같다면 실제값이 작은 수를 앞으로.
				int t = Integer.compare(o1[0], o2[0]);
				if(t == 0) {
					t = Integer.compare(o1[1], o2[1]);
				}
				return t;
			}
		});
		
		
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 입력값이 0이라면
			if(num == 0) {
				// pqueue가 비어있으면 0 
				if(pqueue.isEmpty()) {
					sb.append(0).append("\n");
				}else {
				// 아니면 pqueue에서 절댓값이 가장 작은 값 출력
					int[] temp = pqueue.poll();
					sb.append(temp[1]).append("\n");
				}
			}else {
				// {절댓값, 숫자} 순서로 pqueue에 넣기
				int[] narr = new int[]{Math.abs(num),num};
				pqueue.offer(narr);
			}
		}
		
		
		sb.deleteCharAt(sb.length() -1); // StringBuilder에서 마지막 개행문자 삭제
		System.out.println(sb.toString());
	}

}
