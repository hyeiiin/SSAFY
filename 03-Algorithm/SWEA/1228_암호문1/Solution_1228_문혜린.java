package algorithm.swea;

import java.io.*;
import java.util.*;

//암호문1
public class Solution_1228_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		LinkedList<Integer> list;
		
		for(int t=1; t<=10; t++) {
			//원본 암호문 길이
			int N = Integer.parseInt(br.readLine());
			//암호문 연결리스트
			list = new LinkedList<Integer>();
			st = new StringTokenizer(br.readLine());
			//원본 암호문
			for(int i=0; i<N; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			//명령어 개수
			int cnt = Integer.parseInt(br.readLine());
			//명령어
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<cnt; i++) {
				st.nextToken(); //I는 무시
				int x = Integer.parseInt(st.nextToken()); //x 위치
				int y = Integer.parseInt(st.nextToken()); //y개 숫자 삽입
				for(int j=0; j<y; j++) {
					//y개 만큼 x 위치 다음에 순서대로 삽입
					list.add(x+j, Integer.parseInt(st.nextToken()));
				}
			}
			//수정된 결과 처음 10개 숫자 출력
			sb.append("#"+t+" ");
			for(int i=0; i<10; i++) {
				sb.append(list.get(i)+ " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
