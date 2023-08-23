package todo.lesson._0823;

import java.util.*;

public class Main_17471_최지웅 {

	static int N;
	static int[] pArr;

	static List<List<Integer>> adjList;

	static final boolean A = true;
	static final boolean B = false;

	static List<Integer> areaA = new ArrayList<>();
	static List<Integer> areaB = new ArrayList<>();

	//
	static boolean[] visited;
	static List<Integer> curArea = new ArrayList<>();

	static void DFS(int u, boolean region) {
		
		visited[u] = true;
		curArea.add(u);
		for (int v : adjList.get(u)) {
			if (!visited[v]) {
				if (region == A) {
					if (areaA.contains(v) && !areaB.contains(v)) {
						DFS(v, region);						
					}
				} else if (region == B) {
					if (!areaA.contains(v) && areaB.contains(v)) {
						DFS(v, region);						
					}
				}
			}
		}
	}

	static boolean checkConnected(boolean region) {

		visited = new boolean[N + 1];
		curArea.clear();

		if (region == A) {			
			DFS(areaA.get(0), A);
			Collections.sort(curArea);
			if (curArea.equals(areaA))
				return true;
			else
				return false;
		} else if (region == B) {			
			DFS(areaB.get(0), B);
			Collections.sort(curArea);
			if (curArea.equals(areaB))
				return true;
			else
				return false;
		} 
		
		return false;
	}

	static int calDiff() {
		int sumA = 0;
		int sumB = 0;

		for (int id : areaA) {
			sumA += pArr[id];
		}
		for (int id : areaB) {
			sumB += pArr[id];
		}

		return Math.abs(sumA - sumB);
	}

	public static void main(String[] args) {

		/* input */
		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		pArr = new int[N + 1];
		for (int n = 1; n <= N; n++) {
			pArr[n] = in.nextInt();
		}

		adjList = new ArrayList<>();
		// dummy
		adjList.add(new LinkedList<>());
		for (int n = 1; n <= N; n++) {
			adjList.add(new LinkedList<>());
		}
		
		int adjCnt;
		int to;
		for (int from = 1; from <= N; from++) {
			adjCnt = in.nextInt();
			for (int cnt = 1; cnt <= adjCnt; cnt++) {
				to = in.nextInt();
				adjList.get(from).add(to);	
			}
		}

		int minDiff = Integer.MAX_VALUE;
		boolean found = false;

		// For all subset,
		for (int bits = 1; bits < 1 << N; bits++) {

			if (bits == (1 << N) - 1)
				continue;

			// initialize area A and B
			areaA.clear();
			areaB.clear();

			for (int n = 0; n < N; n++) {
				// divide into area A and B
				if ((bits & 1 << n) != 0) {
					areaA.add(n + 1);
				} else
					areaB.add(n + 1);
			}

			// check area A and B is connected
			boolean validA = checkConnected(A);
			boolean validB = checkConnected(B);

			if (validA && validB) {
				found = true;
				minDiff = Math.min(minDiff, calDiff());
			}
		}

		final int NOT_FOUND = -1;
		System.out.println(found ? minDiff : NOT_FOUND);
		
		//
		in.close();
	}

}
