package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1260_전상혁 {
	static int N, M, V, arr[][];
	static boolean visited[];
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//N: 정점의 개수, M: 간선의 개수, V: 탐색을 시작할 정점의 번호
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		//간선이 연결하는 두 정점의 번호 입력
		arr = new int[N+1][N+1];
		visited = new boolean[N+1]; //1번인덱스부터 사용
		sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
//		for (int i = 0; i < N+1; i++) {
//			for (int j = 0; j < N+1; j++) {
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		dfs(V);
		System.out.println(sb);
		//방문배열과 StringBuilder 초기화
		visited = new boolean[N+1];
		sb = new StringBuilder();
		
		bfs(V);
		System.out.println(sb);
		
	}
	//정점 번호가 작은 것부터 우선 방문, 더 이상 방문할 수 있는 점이 없으면 종료
	private static void dfs(int start) {
		sb.append(start).append(" ");
		visited[start] = true;
		
		for (int i = 1; i <= N; i++) {
			//현재 정점과 연결된 정점이 있는 경우
			if (arr[start][i]==1) {
				//방문하지 않았다면
				if (!visited[i]) {
					System.out.println(start+", "+i);
					
					visited[i]=true;
					//연결되어 있는 정점이 또 다른 정점과 연결되어 있는지 확인
					dfs(i);

				}
			
			}
		}
		
	}
	private static void bfs(int start) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		//탐색 시작 V 정점부터 출발
		sb.append(start).append(" ");
		visited[start] = true;
		queue.offer(start);
		
		while (!queue.isEmpty()) {//큐에 값이 있는 경우
			//값을 꺼내고 대기열에서 삭제 
			int current = queue.poll();
			for (int i = 1; i <= N; i++) {
				//시작 정점과 연결된 인접정점을 탐색, 그 정점을 방문하지 않았다면
				if (arr[current][i] != 0 && !visited[i]) {
					//대기열에 넣고 방문처리
					sb.append(i).append(" ");
					queue.offer(i);
					visited[i] = true;
				}
			}
			
		}
		
	}

}
