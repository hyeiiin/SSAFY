package 백준;

import java.util.*;
import java.io.*;

public class Main_1158_신동근 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();	// 원을 이룬다 -> 큐를 이용하는게 좋다
		// 1부터 N까지 큐에 집어넣음
		for(int i=1; i<=N; i++) {
			queue.add(i);
		}
		
		int start = 1;	// 시작순서 (1번부터 시작)
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		// 큐가 비어있지 않을때까지 반복
		while(!queue.isEmpty()) {
			// K번째 순서가 온 경우 그 순서의 사람(번호) 제거
			if(start == K) {
				// 만약 큐에 저장된 값이 1개밖에 없는 경우 마지막에 ">"문자열 붙여줘야 하므로
				if(queue.size() == 1) {
					sb.append(queue.poll()).append(">");
				}
				else {
					sb.append(queue.poll()).append(", ");
				}
				start = 0;	// 순서 다시 초기화해줌
			}
			// K번째가 아닌 경우 큐의 맨 뒤로 보내준다 (원형을 만들기 위해)
			else {
				int num = queue.poll();
				queue.add(num);
			}
			start++;	// 시작순서 증가
		}
		System.out.println(sb);	// 저장된 StringBuilder 출력

	}

}
