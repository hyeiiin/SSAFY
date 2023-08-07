package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1158_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());	// 순열 크기
		int K = Integer.parseInt(st.nextToken());	// 순서대로 K번째 제거
		Queue<Integer> que = new ArrayDeque<>();	// 요세푸스 순열 (큐)
		int cnt = 1;								// K번째 인지 확인하기 위한 변수
		
		for(int i=1; i<=N; i++) {
			que.offer(i);	// 1부터 N까지 que offer

		}
		
		sb.append("<");
		
		while(!que.isEmpty()) {	// que가 empty할 때 동안
			int temp = que.poll();	// temp에 que의 처음 넣은 값을 빼고 대입
			if(cnt == K) {	// 만약 cnt가 K번째라면
				sb.append(temp).append(", ");	// temp를 출력하고 cnt는 다시 1로 초기화
				cnt = 1;
				continue;
			}
			que.offer(temp);	// cnt가 K번째가 아니라면 que의 뒤에 다시 offer, cnt값 증가
			cnt++;				
		}
		
		sb.append(">");
		sb.delete(sb.length()-3, sb.length()-1);	// 출력을 위해 콤마와 띄어쓰기 삭제
		System.out.println(sb);
		
	}

}
