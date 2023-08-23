package algorithm.swea;

import java.util.*;
import java.io.*;

public class Solution_3289_문혜린 {
	static int parent[]; //부모 노드
	
	public static void union(int a, int b) {
		a = find(a); //a의 부모 노드 찾기
		b = find(b); //b의 부모 노드 찾기
		
		if(a>b) { //a의 부모노드가 더 클 경우
			parent[a] = b; //a의 부모노드 b의 부모노드로 합치기
		}
		else { //b의 부모노드가 더 클 경우
			parent[b] = a; //b의 부모노드 a의 부모노드로 합치기
		}
	}
	public static int find(int n) { //같은 집합인지 찾기
		if(n == parent[n]) { //부모 노드가 자기 자신일 경우
			return n;
		}
		return find(parent[n]); //루트 노드까지 올라가기
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); //테스트 케이스 수
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); //n개의 집합
			int m = Integer.parseInt(st.nextToken()); //입력으로 주어지는 연산의 개수
			
			sb.append("#"+t+" ");
			
			//부모 노드 저장
			parent = new int[n+1];
			for (int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int oper = Integer.parseInt(st.nextToken()); //연산
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(oper == 0) { //집합 a, 집합 b 합침
					union(a, b);
				}
				else if(oper == 1) { //a와 b가 같은 집합인지 확인
					if(find(a) == find(b)) { //같은 집합일 경우
						sb.append(1);
					}
					else { //다른 집합일 경우
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
