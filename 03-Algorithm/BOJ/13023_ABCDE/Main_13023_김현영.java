import java.io.*;
import java.util.*;
 

public class Main_13023_김현영 {

	static boolean[] isVisited;
	static Node[] list;
	
	static boolean flag;

	static void dfs(int cnt, int start, int n) {
		//이어진 간선의 개수가 5가 되면 종료
		if (cnt == 5) {
			flag = true;
			return;
		}
		// 현재 사람의 친구관계 탐색. 이미 방문한 친구라면 다음 관계의 친구 탐색
		for(Node temp = list[start]; temp!= null; temp= temp.next) {
			if(!isVisited[temp.vertex]) {
				isVisited[temp.vertex] = true;
				dfs(cnt+1, temp.vertex, n);
				isVisited[temp.vertex] = false;
			}
		}
	}

	//인접리스트의 노드
	public static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());	//사람 수
		int m = Integer.parseInt(st.nextToken());	//친구 관계 수

		// 인접 리스트 입력
		list = new Node[n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//양방향 연결
			list[a] = new Node(b, list[a]);
			list[b] = new Node(a, list[b]);
		} 
		
		//모든 사람의 친구관계를 확인
		for (int i = 0; i < n; i++) {
			isVisited = new boolean[n];
			isVisited[i] = true;
			flag = false;
			dfs(1, i, n);
			//주어진 친구관계를 가진 사람이 있다면 종료
			if (flag) {
				System.out.println(1);
				return;
			}
		}
		//모든 사람을 확인한 후에도 주어진 친구관계를 가진 사람이 없다면 0 출력
		System.out.println(0);

	}
}
