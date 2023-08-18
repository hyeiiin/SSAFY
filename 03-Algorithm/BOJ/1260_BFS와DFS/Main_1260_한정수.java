import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_한정수 {
	static int N, M, V;
	static int[][] arr;
	static boolean[] visited;
	static StringBuilder sb;
	static Queue<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		queue = new ArrayDeque<>();
		
		sb = new StringBuilder();
		int from = -1;
		int to = -1;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			arr[from][to] = 1;
			arr[to][from] = 1;
		}
		//배열 입력 완료===============
		
		dfs(V);
		System.out.println(sb.toString());
		
		//bfs 돌리기 전에 초기화
		sb = new StringBuilder();   
		Arrays.fill(visited, false);
		
		bfs(V);
		System.out.println(sb.toString());
	}
	
	public static void dfs(int start) {
		//일단 시작하면 그놈 출력하고, 방문 찍고
		sb.append(start+" ");
		visited[start] = true;
		
		//인접행렬에 있는지 돌리고
		for(int i=1; i<=N ; i++) {
			if(arr[start][i] == 1 && !visited[i]) {
				//연결된 간선이 있으면 걔 방문했는지 확인하고, dfs 호출.
				dfs(i);
			}
		}
	}
	public static void bfs(int start) {
		//일단 최초로 시작하는애 큐에 넣고, 방문 찍고
		visited[start] = true;
		queue.add(start);
		
		//큐가 완전히 빌때까지 반복
		while(!queue.isEmpty()) {

			int temp = queue.poll();	//뽑고
			sb.append(temp+" ");		//출력하고
			
			//인접행렬 돌아보고
			for (int i=1 ; i<=N ; i++) {
				if(arr[temp][i] == 1 && !visited[i]) {
					visited[i] = true;  //방문 찍고
					queue.add(i);		//큐에 넣고
				}
			}
		}
	}

}
