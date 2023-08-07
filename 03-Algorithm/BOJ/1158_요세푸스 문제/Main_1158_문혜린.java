package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//요세푸스 문제
public class Main_1158_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken()); //N명의 사람
		int K = Integer.parseInt(st.nextToken()); //K번째 사람 제거
		
		Queue<Integer> q = new LinkedList<>();
		
		//큐 1~N까지 초기화
		for(int i=1; i<=N; i++) {
			q.add(i);
		}
		sb.append("<");
		while(!q.isEmpty()) {
			for(int i=0; i<K-1; i++) { //K번째 사람 제거하기 위해 K만큼 반복
				q.add(q.remove()); //가장 앞에 원소 뒤로 옮기기
			}
			sb.append(q.remove()+", "); //K번째 사람 제거
		}
		sb.delete(sb.length()-2, sb.length()); //마지막 원소 출력형식 맞춰주기
		sb.append(">");
		System.out.println(sb);
	}

}
