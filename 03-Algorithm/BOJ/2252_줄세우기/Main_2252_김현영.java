import java.io.*;
import java.util.*; 

public class Main_2252_김현영 {

	static int n, m;
	static Node[] adjList; // 인접 리스트
	static int[] inDegree; // 진입차수
	static Deque<Integer> q = new ArrayDeque<>();

	// 인접리스트에 사용할 노드
	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}

	public static List<Integer> topologySort() {
		// 위상정렬을 한 학생을 저장할 리스트
		List<Integer> list = new ArrayList<>();

		// 진입차수가 0인 노드 모두 큐에 추가
		for (int i = 0; i < n; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}
		while (!q.isEmpty()) {
			int now = q.poll(); // 큐에서 노드 꺼내기
			list.add(now + 1); // 리스트의 인덱스 사용을 위해 입력받은 값을 -1 해주었기 때문에 리스트에 저장 시 +1 처리
			// 현재 노드의 인접리스트 모두 탐색
			for (Node temp = adjList[now]; temp != null; temp = temp.next) {
				// 인접리스트의 차수가 1이라면 큐에 추가
				if (--inDegree[temp.vertex] == 0)
					q.offer(temp.vertex);
			}

		}

		return list;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken()); // 학생 수
		m = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수

		adjList = new Node[n]; // 학생 수만큼 인접리스트 생성
		inDegree = new int[n]; // 학생 수만큼 진입차수 배열 생성

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			adjList[a] = new Node(b, adjList[a]); // 인접 리스트 추가
			inDegree[b]++; // 진입 차수 증가
		}
 
		// 위상 정렬
		List<Integer> list = topologySort();

		// 위상정렬된 리스트 출력
		for (int i : list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
