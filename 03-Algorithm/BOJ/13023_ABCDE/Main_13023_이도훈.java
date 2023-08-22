import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13023_이도훈 {
	static int N;
	static int M;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static boolean isPossible = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		adjList = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adjList[a].add(b);
			adjList[b].add(a);
		}

		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			dfs(i, 0);
		}

		System.out.println(isPossible ? "1" : "0");

	}

	static void dfs(int num , int cnt) {
		if(isPossible) return;

		if (cnt == 4) {
			isPossible = true;
			return;
		}

		if(visited[num]) return;

		visited[num] = true;

		for (Integer adj : adjList[num]) {
			if(visited[adj]) continue;
			dfs(adj, cnt + 1);
		}

		visited[num] = false;
	}

}
