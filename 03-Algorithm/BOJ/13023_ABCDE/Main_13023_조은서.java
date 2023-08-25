import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13023_조은서 {
	
	static ArrayList<Integer>[] graph;
	static int N, M;
	static boolean[] visited;
	static boolean check;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 사람 수
		M = Integer.parseInt(st.nextToken()); // 친구 관계 수
		
		// 인접리스트 초기화
		graph = new ArrayList[N];
		for(int i=0; i<N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 친구 관계 저장
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
		
		
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			check = false;
			dfs(i,1); // 0번부터 N-1번까지 DFS 탐색 후 depth가 5가 되는지 확인
			if(check) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);

	}
	
	static void dfs(int start, int depth) {
		if(depth == 5) {
			check = true; // 친구관계 성립
			return;
		}
		visited[start] = true;
		for(int next : graph[start]) {
			if(!visited[next]) {
				dfs(next, depth+1);
			}
		}
		visited[start] = false;
	}

}
