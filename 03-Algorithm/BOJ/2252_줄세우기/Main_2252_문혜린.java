package algorithm.baekjoon;

import java.io.*;
import java.util.*;

//줄 세우기
public class Main_2252_문혜린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //학생 수
		int M = Integer.parseInt(st.nextToken()); //키 비교 횟수
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		int edgeCount[] = new int[N+1]; //진입 차수
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); //학생 A
			int B = Integer.parseInt(st.nextToken()); //학생 B
			graph.get(A).add(B); //그래프 A노드에 B 연결
			edgeCount[B]++; //B의 진입 차수 증가
		}
		
		Queue<Integer> q = new LinkedList<>();
		//진입 차수 0인 노드를 큐에 모두 넣음
		for (int i = 1; i <= N; i++) {
			if(edgeCount[i] == 0) {
				q.add(i);
			}
		}
		//큐가 빌 때까지 반복
		while(!q.isEmpty()) {
			int p = q.remove(); //진입 차수 0인 노드 꺼내기
			sb.append(p + " "); //순서대로 출력
			
			List<Integer> list = graph.get(p); //해당 노드 인접 리스트
			
			for (int i = 0; i < list.size(); i++) {
				edgeCount[list.get(i)]--; //인접 노드 간선 제거
				if(edgeCount[list.get(i)] == 0) { //간선 제거하여 진입 차수 0 됐을 경우
					q.add(list.get(i)); //큐에 삽입
				}
			}
		}
		System.out.println(sb);
	}

}
