import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main_17471_이도훈 {

	static boolean[] team;
	static int[] parents;
	static int[] population;
	static ArrayList<Integer>[] edges;
	static int N;
	static int limit;
	static int answer = Integer.MAX_VALUE;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		limit = N + 1;
		StringTokenizer st = new StringTokenizer(br.readLine());

		edges = new ArrayList[N + 1];
		population = new int[N + 1];
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			population[i] = Integer.parseInt(st.nextToken());
			edges[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			for (int j = 0; j < n; j++) {
				edges[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		team = new boolean[N + 1];

		dfs(1);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}

	static void dfs(int depth) {
		if (depth == limit) {

			// 연결되어있는 애들끼리 Union-Find
			for (int i = 1; i <= N; i++) {
				for (Integer other : edges[i]) {
					if (team[other] == team[i]) {
						union(other, i);
					}
				}
			}
			HashSet<Integer> set = new HashSet<>();

			for (int i = 1; i <= N; i++) {
				set.add(find(i));
			}
			if (set.size() > 2 || set.size() == 1) {
				initParents();
				return;
			}

			int aSum = 0;
			int bSum = 0;
			for (int i = 1; i <= N; i++) {
				if (team[i]) {
					aSum += population[i];
				} else {
					bSum += population[i];
				}
			}

			answer = Math.min(answer, Math.abs(aSum - bSum));
			initParents();
			return;
		}

		dfs(depth + 1);

		team[depth] = true;
		dfs(depth + 1);
		team[depth] = false;



	}

	static void union(int a, int b) {
		int aP = find(a);
		int bP = find(b);

		if (aP != bP) {
			parents[bP] = aP;
		}
	}

	static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	static void initParents() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
}
