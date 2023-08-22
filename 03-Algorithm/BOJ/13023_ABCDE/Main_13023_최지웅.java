package todo.lesson._0822;

import java.io.*;
import java.util.*;

public class Main_13023_최지웅 {
	
	static List<List<Integer>> adjList;
	
	static boolean found;
	
	static boolean[] visited;
	
	static void find(int from, int count) {
		
		visited[from] = true;
		
		if (count == 5) {
			found = true;
			return;
		}
		else {
			for (int to : adjList.get(from)) {
				if (visited[to]) continue;
				find(to, count + 1);
				visited[to] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		/* input */
//		System.setIn(new FileInputStream("input.txt"));
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		int M = in.nextInt();
		
		adjList = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			adjList.add(new LinkedList<>());
		}
		
		int a, b;
		for (int m = 1; m <= M; m++) {
			a = in.nextInt();
			b = in.nextInt();
			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}
		
		/* solve */
		found = false;
		visited = new boolean[N];
		for (int n = 0; n < N; n++) {
			if (found) break;
			Arrays.fill(visited, false);
			find(n, 1);
		}
		
		
		/* output */
		final int FOUND = 1;
		final int NOT_FOUND = 0;
		if (found) {
			System.out.println(FOUND);
		} else {
			System.out.println(NOT_FOUND);
		}

		//
		in.close();
	}

}
