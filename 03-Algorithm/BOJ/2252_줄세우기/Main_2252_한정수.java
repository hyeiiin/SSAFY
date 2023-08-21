import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2252_한정수 {
	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}
	static int N, M;
	//bfs
	static Node[] adjList;
	static int[] income_edge;
	static Queue<Integer> queue;
	
	//dfs
	static boolean[] visited;
	static int[] dfs_result;
	static StringBuilder sb2;
	static Stack<Integer> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new Node[N+1];		
		
		
		
		//일단 그래프를 나타내고, 위상정렬을 사용해서 나타내면 되는데,
		//인접행렬을 쓰게되면 N <= 32000에서 32,000 * 32,000 하면 10억 정도 나와서, int면 4000MB로 메모리 터짐.
		//즉, 인접리스트로 표현하라는 것.
		
		

		
		//공통 입력 (그래프 >> 인접리스트로)=================
		for (int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken());
			int big = Integer.parseInt(st.nextToken());
			
			//adjList[번호] 찍으면 연결된 큰놈 하나 노드 불러와서 숫자 불러오려면 Node.vertex 그놈한테 연결된 다음놈 부르려면 Node.next
			adjList[small] = new Node(big, adjList[small]);
			
			//큰놈한테 진입한거니까 income_edge[큰놈] += 1
//			income_edge[big] += 1;
		}
		
		
		
//		//========================= BFS =================================
//		//bfs로 푼다면.
//		//각 진입차수를 알아야되기 때문에, 각 [노드번호] = 진입차수 를 저장할 배열 필요
//		queue = new ArrayDeque<>();
//		income_edge = new int[N+1];
//		
//		for (int i=1; i<=N ; i++) {
//			//일단 최초로 진입차수가 0인 애들부터 큐에 넣고 
//			if(income_edge[i] == 0) {
//				queue.add(i);
//			}
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		while (!queue.isEmpty()) {
//			//큐에서 빼고
//			int temp = queue.poll();
//			sb.append(temp+" ");
//			
//			//뺀 놈을 인접리스트에 박아서, temp에 연결되어있는 모든 노드의 진입차수를 1씩 깎음.
//			for(Node node = adjList[temp] ; node != null ; node = node.next) {
//				int node_num = node.vertex;
//				income_edge[node_num] -= 1;
//				if(income_edge[node_num] == 0) {
//					//그러다 0이면 큐를 넣고.
//					queue.add(node_num);
//				}
//			}
//		}//while 종료.
//
//		//본래 while 종료후 queue 진입차수가 모두 0인지 확인 작업이 필요함. 그런데 문제에서 애초에 그렇게 되도록 값을 준다니까
//		//굳이 확인 안해도 됨.
//		System.out.println(sb.toString());
		//========================= BFS 끝=================================
		
		
		//========================= DFS ==================================
		//DFS로 풀 경우, 일단 그래프를 인접리스트/행렬로 나타내고
		// DFS로 노드 방문을 출력시킨다.
		// 그 출력 결과를 뒤집으면 그게 위상출력.
		//   >>왜냐면 DFS는 그 유향 그래프의 방향을 따라서, 순서를 따라서 쭉쭉쭉쭉 들어가고, 맨 마지막 노드부터 출력으로 뱉어내기 때문에,
		//    >>애초에 내가 순서에 대해서 신경쓸 필요가 없음.
		
		// 단, 유향 그래프이기 때문에, 시작한 node 위치에 따라 결과가 전부 다름. 
		// 맨 꼭대기에서 시작한거랑 가운데에서 시작한거랑 결과가 다르기 때문.
		// 따라서 주어진 모든 노드에 대해 전부 dfs 돌려야함. >> 이 문제 푸는데에 재귀 깊이 때문에 복잡도가 터질수도 있음.
		
		//DFS용 방문 배열 하나 만들어주고
		visited = new boolean[N+1]; 
		
		sb2 = new StringBuilder();
		stack = new Stack<>();
		for (int i=1; i<=N ; i++) {
			if(!visited[i]) {
				dfs(i);
			}
		}
		while(!stack.isEmpty()) {
			sb2.append(stack.pop()).append(" ");
		}
		System.out.println(sb2.toString());
//		System.out.println(sb2.reverse().toString());
		
	}
	public static void dfs(int start) {
		visited[start] = true;
		
		for (Node node = adjList[start] ; node != null ; node = node.next) {
			if(!visited[node.vertex]) {
				dfs(node.vertex);
			}
		}
		//전부다 끝나고 갈데 없어졌을때 stack에 집어넣기. 그래야 맨 뒷놈부터 들어감.
		stack.add(start);
	}

}
