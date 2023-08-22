package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3289_탁하윤 {
	
	static int n, parents[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			n = Integer.parseInt(st.nextToken());	// 집합의 수
			int m = Integer.parseInt(st.nextToken());	// 연산의 수
			
			make();	// 집합 만들기
			
			for (int i = 0; i < m; i++) {	// 연산의 수 만큼 돌면서
				st = new StringTokenizer(br.readLine());
				int oper = Integer.parseInt(st.nextToken());	// 0이면 합집합 1이면 포함확인
				int a = Integer.parseInt(st.nextToken());	// 집합 a
				int b = Integer.parseInt(st.nextToken());	// 집합 b
				
				if(oper == 1) {	// 합집합 연산이 아니라면
					if(find(a) == find(b)) {	// 포함되어있는지 확인
						sb.append(1);	// 포함되어있다면 1
					} else {
						sb.append(0);	// 아니라면 0
					}
				} else {	// 합집합 연산이라면 합치기
					union(a, b);
				}
			}
			System.out.println(sb.toString());
		}

	}
	
	static void make() {	// 집합 만들기
		parents = new int[n];	// 집합의 부모를 담을 배열
		for (int i = 0; i < n; i++) {	// 1부터 n까지 부모 담기
			parents[i] = i+1;
		}
	}
	
	static int find(int a) {	// 집합이 포함되어 있는지 확인
		if(a == parents[a-1]) return a;	// 자신이 대표자인 경우 값 리턴
		return parents[a-1] = find(parents[a-1]);	// (path compression) 자신이 속한 집합의 대표자를 자신의 부모로 바꾼다.
	}
	
	static boolean union(int a, int b) {	// 합집합
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;	// 이미 같은 집합이면 false 리턴
		parents[bRoot-1] = aRoot;	// 대표자로 합치기
		return true;
	}

}
