import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution { // 3124_최소 스패닝 트리
	static StringBuilder sb;
	static StringTokenizer st;
	static int TC;
	static int V, E; // 정점의 개수, 간선의 개수
	static Edge[] lst;
	static int[] parents;
	static int cnt;
	static long res;

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		TC = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc < TC + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken()); // 1 <= V <= 10_0000
			E = Integer.parseInt(st.nextToken()); // 1 <= E <= 20_0000

			lst = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int W = Integer.parseInt(st.nextToken());

				lst[i] = new Edge(A, B, W);
			}

			Arrays.sort(lst);
			cnt = 0;
			res = 0;

			make_Set();

			// 정점들을 꺼내어, 서로 Union이 가능하다면 해당 가중치를 누적.
			for (Edge e : lst) {
				if (union_Set(e.from, e.to)) {
					res += e.weight;
//					res += Long.valueOf(e.weight);

					// 간선의 수 = 정점 - 1, 조건이 만족한다면 탈출
					if (++cnt == V - 1) {
						break;
					}
				}
			}

			sb = new StringBuilder();
			sb.append("#").append(tc).append(" ").append(res);
			System.out.println(sb);

		} // TC

	} // Main

	// 집합 세팅
	static void make_Set() {
		parents = new int[V + 1];

		// 초기에는 자기 자신을 원소로 갖는 단위 서로소 집합을 생성.
		for (int i = 0; i < V + 1; i++) {
			parents[i] = i;
		}
	}

	/**
	 * 집합의 대표자를 찾는 함수
	 * 
	 * @param A : 찾을 집합
	 * @return
	 */
	static int find_Set(int A) {
		if (parents[A] == A) {
			return A;
		}

		// parent[A]를 통해 집합의 대표자를 찾고 그것을 반환(자신의 부모를 변경)
		return parents[A] = find_Set(parents[A]);
	}

	/**
	 * 두 집합 A, B를 합치는 함수
	 * 
	 * @param A
	 * @param B
	 */
	static boolean union_Set(int A, int B) {
		int aP = find_Set(A);
		int bP = find_Set(B);
//		System.out.println(Arrays.toString(parents));

//		System.out.println(Arrays.toString(parents));

//		if (aP < bP) {
//			parents[bP] = aP;
//		} else {
//			parents[aP] = bP;
//		}

		// 이미 같은 집합에 속해있음.
		if (aP == bP) {
			return false;
		}

		parents[bP] = aP;
		return true;
	}

}
