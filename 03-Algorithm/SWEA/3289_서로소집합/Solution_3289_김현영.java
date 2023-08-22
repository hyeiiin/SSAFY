import java.io.*;
import java.util.*;

public class Solution_3289_김현영 {

	static int n;
	static int[] parents;

	// 서로소 집합을 생성하는 함수. 자신을 대표자로 가짐
	static void make() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	//a의 부모노드를 찾는 함수. 경로압축 사용
	static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	//집합b를 a에 합치는 함수
	static void union(int a, int b) {
		if(find(a) == find(b))
			return;
		parents[find(b)] = find(a);
	}

	//원소 a,b가 같은 집합에 있는지 확인하는 함수
	static int checkSameSet(int a, int b) {
		if (find(a) == find(b))
			return 1;
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트케이스
		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken()); // 집합의 수
			int m = Integer.parseInt(st.nextToken()); // 연산의 개수

			make();
			// m개의 연산 입력
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken()); // 연산 종류
				int a = Integer.parseInt(st.nextToken()) - 1; // 집합 a. parents배열은 인덱스 0부터 시작하므로 -1
				int b = Integer.parseInt(st.nextToken()) - 1; // 집합 b. parents배열은 인덱스 0부터 시작하므로 -1

				// 합집합 연산
				if (op == 0)
					union(a, b);
				// 같은 집합에 포함되어 있는지 확인하는 연산
				else if (op == 1)
					sb.append(checkSameSet(a, b));
			}

			sb.append("\n");
		}

		System.out.println(sb.toString());
	}
}
