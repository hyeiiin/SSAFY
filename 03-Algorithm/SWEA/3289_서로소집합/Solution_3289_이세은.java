import java.io.*;
import java.util.*;

public class Solution_3289_이세은 {

	private static int n;
	private static int parents[]; // 대표자 배열

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트케이스 수
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // n개의 집합
			int m = Integer.parseInt(st.nextToken()); // 연산의 개수
			StringBuilder sb = new StringBuilder(); // 정답 저장 sb

			// 각 원소를 최소로 가지는 집합 생성
			makeSet();

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				// 옵션과 두수 입력받기
				int op = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// 합집합 연산
				if (op == 0) {
					// a와 b가 포함된 집합을 서로 합친다.
					// 각 집합의 대표자 찾기
					unionSet(a, b);
				}
				// 같은 집합에 포함되는지 여부 연산
				else if (op == 1) {
					// 각 집합의 대표자가 같은지 확인
					if (findSet(a) == findSet(b))
						sb.append("1");
					else
						sb.append("0");

				}
			}

			System.out.println("#" + test_case + " " + sb.toString());
		}

	}

	// Make-Set, n개의 원소에 대해 각 원소를 대표자로 하는 집합 n개 생성 메서드
	public static void makeSet() {
		parents = new int[n + 1]; // n개의 집합 개수, 각 집합의 대표자 배열 초기화
		for (int i = 1; i <= n; i++) {
			parents[i] = i; // 각 원소를 최소로 가지는 집합, 대표자는 해당 원소
		}
	}

	// find-Set, 원소 n이 포함된 집합의 대표자 리턴 메서드
	public static int findSet(int num) {
		if (parents[num] == num)
			return num; // 계속 대표자 찾다가 루트 노드 만났다면 리턴
		return parents[num] = findSet(parents[num]); // 대표자 아직 못찾은 상황에서는 재귀적으로 계속 대표자 찾아간다

	}

	// union-Set, 원소 a와 원소 b가 포함된 집합을 합치는 메서드
	public static void unionSet(int a, int b) {

		if (a == b) // 두수가 같다면 진행X
			return;

		// 각 집합의 대표자 먼저 찾기
		int aRoot = findSet(a);
		int bRoot = findSet(b);

		// 만약 같은 집합이면 진행X
		if (aRoot == bRoot)
			return;
		// 만약 다른 집합이면 union 수행
		else {
			parents[bRoot] = aRoot; // 임의의 하나의 집합의 대표자에 다른 집합의 대표자를 이어 붙인다
		}
	}

}
