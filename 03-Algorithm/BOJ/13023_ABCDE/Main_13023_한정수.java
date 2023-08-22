import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13023_한정수 {
	static int N,M;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static boolean result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N];
		for (int i=0; i<N ; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		
		visited = new boolean[N];
		result = false;
		int a = 0;
		int b = 0;
		for (int i=0; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}
		//입력완료
		//결국 dfs로 깊이가 4번까지 들어가느냐 == 재귀가 4번까지 도느냐에 대한 문제.
		//인접행렬로는 메모리 너무 크니깐 인접 리스트로 구현.
		
		for (int i=0; i<N; i++) {
			//dfs 실행.
			dfs(0, i);
			if(result) {
				break;
			}
		}
		if(result) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		
	}
	public static void dfs(int cnt, int start) {
		if (result) {
			return;
		}
		if (cnt == 4) {
			result = true;
			return;
		}
		visited[start] = true;
		for(int i : adjList[start]) {
			// start점에 인접한 모든 점에 대해, dfs 다시 실행.
			if (!visited[i]) {
				dfs(cnt+1, i);
			}
		}
		visited[start] = false;
	}

}
