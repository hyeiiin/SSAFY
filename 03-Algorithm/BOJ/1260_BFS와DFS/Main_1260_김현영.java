import java.io.*;
import java.util.*;

public class Main_1260_김현영 { 
	static void bfs(List<Set<Integer>> graph, int n, int v) {
		StringBuilder sb = new StringBuilder();

		List<Integer> visited = new ArrayList<>(); // 방문한 노드 저장
		Deque<Integer> q = new ArrayDeque<>(); // 다음으로 확인할 노드 저장
		q.add(v);    // 큐에 처음 방문할 노드 저장
		while (!q.isEmpty()) { //더이상 방문할 노드가 없을 때까지 반복
			sb.append(q.peek()).append(" ");
		    //현재 노드가 다음에 방문할 수 있는 노드들의 리스트를 오름차순 정렬
			List<Integer> next = new ArrayList<>(graph.get(q.peek() - 1));
			Collections.sort(next); 
			visited.add(q.poll());    //방문처리
		    //next를 하나씩 확인하며 방문하지도 않았고 방문할예정도 아닌 다음 노드를 모두 찾아서 큐에 저장
			for (Integer i : next) {
				if (!visited.contains(i) && !q.contains(i))
					q.add(i);
			} 
		}
		System.out.println(sb.toString());
	}

	public static void dfs(List<Set<Integer>> graph, List<Integer> visited, Deque<Integer> q) {
		if (q.isEmpty()) //더이상 방문 가능한 노드가 없다면 탐색 종료
			return;

		System.out.print(q.peek() + " ");

		//현재 노드가 다음에 방문할 수 있는 노드들의 리스트를 오름차순 정렬
		List<Integer> next = new ArrayList<>(graph.get(q.peek() - 1));
		Collections.sort(next); 
		visited.add(q.poll());	//방문처리
		
		//next를 하나씩 확인하며 방문하지도 않았고 방문할예정도 아닌 다음 노드를 1개 찾아서 큐에 저장
		for (int i = 0; i < next.size(); i++) {
			if (!visited.contains(next.get(i)) && !q.contains(next.get(i))) {
				q.add(next.get(i)); 
                dfs(graph, visited, q);	//현재노드에서 방문할 다음 노드에서 방문할 노드를 찾아감
			}
		} 
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken()); // 노드의 수
		int m = Integer.parseInt(st.nextToken()); // 간선의 수
		int v = Integer.parseInt(st.nextToken()); // 방문을 시작할 노드

		// 그래프를 저장하는 리스트
		// 리스트의 인덱스 = 정점번호. 리스트의 각 요소는 해당 인덱스와 연결되어 있는 번호들의 리스트
		List<Set<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Set<Integer> init = new HashSet<>();
			graph.add(init);
		}
		//간선의 수 만큼 연결되어있는 노드 2개를 입력받고 그래프에 저장
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int e1 = Integer.parseInt(st.nextToken());
			int e2 = Integer.parseInt(st.nextToken());

			graph.get(e1 - 1).add(e2);
			graph.get(e2 - 1).add(e1);
		} 
        
		// dfs
		List<Integer> visited = new ArrayList<>(); // 방문한 노드 저장
		Deque<Integer> q = new ArrayDeque<>(); // 다음으로 확인할 노드 저장
		q.add(v);    //큐에 처음 방문할 노드 저장
		dfs(graph, visited, q);
		
		System.out.println();

		// bfs
		bfs(graph, n, v);

	}
}