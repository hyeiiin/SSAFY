package algorithm.swea;

import java.util.*;
import java.io.*;

//암호 생성기
public class Solution_1225_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for(int t=1; t<=10; t++) {
			int T = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			//큐에 초반 숫자 삽입
			Queue<Integer> queue = new LinkedList<>();
			for(int i=0; i<8; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			
			int j = 0;
			while(true) {
				int tmp = queue.remove(); //맨 앞 원소 꺼내기
				int num = tmp-(++j); //순서대로 1씩 증가하며 감소
				
				if(num <=0) { //감소하다가 0보다 작아지거나 0일 경우 0 저장
					queue.add(0);
					break; //암호 도출
				}
				if(j==5) { //사이클 종료되면
					j=0; //초기화
				}
				
				queue.add(num); //뒤로 이동
			}
			sb.append("#"+t+" ");
			for(int i=0; i<8; i++) {
				sb.append(queue.remove()+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
