import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution { // 3289
	static StringBuilder sb;
	static StringTokenizer st;
	static int TC;
	static int N, M; // 정점 최대 번호, 연산의 개수
	static int oper, A, B; // 연산 종류, 연산 대상, 연산 대상
	static int[] parent;  // 각 정점의 부모노드 번호를 저장할 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc < TC + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			make_Set();
			
			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				oper = Integer.parseInt(st.nextToken());
				A = Integer.parseInt(st.nextToken());
				B = Integer.parseInt(st.nextToken());
				
				// 1. 두 원소가 같은 집합에 포함되어 있는지 확인.
				if (oper == 1) {
					sb.append(find_Set(A) == find_Set(B)? 1:0);

				} else {  // 2. A와 B집합을 합침.
					union_Set(A, B);
				}
			}  // for
			
			System.out.println(sb);
			
		} // TC

	} // Main
	
	/**
	 * 배열 세팅
	 */
	static void make_Set() {
		// 정점은 1번부터 시작
		parent = new int[N + 1];
		
		// 초기에는 자기 자신을 원소로 갖는 단위 서로소 집합을 생성.
		for (int i = 1; i < N + 1; i++) {
			parent[i] = i;
		}
		
	}

	/**
	 * 집합의 대표자를 찾는 함수
	 * @param A : 찾을 집합
	 * @return
	 */
	static int find_Set(int A) {
		if (A == parent[A]) {
			return A;
		}
		
		// parent[A]를 통해 집합의 대표자를 찾고 그것을 반환(자신의 부모를 변경)
		return parent[A] = find_Set(parent[A]); 
	}

	/**
	 * 두 집합 A, B를 합치는 함수
	 * @param A
	 * @param B
	 */
	static void union_Set(int A, int B) {
		int aP = find_Set(A);
		int bP = find_Set(B);
		
		// 편의상 원소 값이 더 작은 쪽으로 합침
		if (aP < bP) {
			parent[bP] = aP;
		} else {
			parent[aP] = bP;
		}
		 
	}

}
