package SR;

import java.io.*;
import java.util.*;

public class Main_17471_����� {	// union ���
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, ans = Integer.MAX_VALUE, parents[], person[], copyParents[];
	static List<Integer> aList = new ArrayList<>();
	static List<Integer> bList;
	static List<Integer>[] link;

	@SuppressWarnings("unchecked")
	public static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		parents = new int[N + 1];
		person = new int[N + 1];
		link = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
			person[i] = Integer.parseInt(st.nextToken());
			link[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int k = 0; k < n; k++) {
				link[i].add(Integer.parseInt(st.nextToken()));
			}
		}
	}

	public static int findSet(int a) {
		if (a == copyParents[a]) return a;
		return copyParents[a] = findSet(copyParents[a]);
	}

	public static void union(int a, int b) { 
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) copyParents[bRoot] = aRoot;
	}

	public static void doUnion(List<Integer> list) {
		for (int i = 0; i < list.size(); i++) {
			int v = list.get(i);
			for(int k = 0; k < link[v].size(); k++) {
				if(list.contains(link[v].get(k))) {
					union(v, link[v].get(k));
				}
			}
		}
	}

	public static void solve() {
		doUnion(aList);
		doUnion(bList);
		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			set.add(findSet(i));
		}
		if(set.size() != 2) return;
		int a = 0;
		int b = 0;
		for(int i : aList) a += person[i];
		for(int i : bList) b += person[i];
		ans = Integer.min(ans, Math.abs(a - b));
	}

	public static void comb(int cnt, int index) {
		if (cnt == 0) {
			bList = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (!aList.contains(i))
					bList.add(i);
			}
			copyParents = parents.clone();
			solve();
			return;
		}
		for (int i = index; i <= N; i++) {
			aList.add(i);
			comb(cnt - 1, i + 1);
			aList.remove(aList.size() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		init();
		for (int i = 1; i <= N / 2; i++) {
			comb(i, 1);
		}
		bw.write(ans == Integer.MAX_VALUE ? -1 + "\n" : ans + "\n");
		bw.flush();
		bw.close();
	}
}

//public class BOJ17471_BFS {	// BFS ���
//	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//	static StringTokenizer st;
//	static int N, ans = Integer.MAX_VALUE, parents[], person[];
//	static List<Integer> aList = new ArrayList<>();
//	static List<Integer> bList;
//	static List<Integer>[] link;
//
//	@SuppressWarnings("unchecked")
//	public static void init() throws IOException {
//		N = Integer.parseInt(br.readLine());
//		st = new StringTokenizer(br.readLine());
//		parents = new int[N + 1];
//		person = new int[N + 1];
//		link = new ArrayList[N + 1];
//		for (int i = 1; i <= N; i++) {
//			parents[i] = i;
//			person[i] = Integer.parseInt(st.nextToken());
//			link[i] = new ArrayList<>();
//		}
//		for (int i = 1; i <= N; i++) {
//			st = new StringTokenizer(br.readLine());
//			int n = Integer.parseInt(st.nextToken());
//			for (int k = 0; k < n; k++) {
//				link[i].add(Integer.parseInt(st.nextToken()));
//			}
//		}
//	}
//
//	public static int bfs(int start, List<Integer> list) {
//		boolean visited[] = new boolean[N + 1];
//		Queue<Integer> q = new ArrayDeque<>();
//		q.offer(start);
//		int sum = person[start];
//		visited[start] = true;
//		while(!q.isEmpty()) {
//			int v = q.poll();
//			for(int i = 0; i < link[v].size(); i++) {
//				int nv = link[v].get(i);
//				if(!visited[nv] && list.contains(nv)) {
//					visited[nv] = true;
//					sum += person[nv];
//					q.offer(nv);
//				}
//			}
//		}
//		for(int i = 0; i < list.size(); i++) {
//			if(!visited[list.get(i)]) {
//				return -1;
//			}
//		}
//		return sum;
//	}
//
//	public static void comb(int cnt, int index) {
//		if (cnt == 0) {
//			bList = new ArrayList<>();
//			for (int i = 1; i <= N; i++) {
//				if (!aList.contains(i))
//					bList.add(i);
//			}
//			int aSum = bfs(aList.get(0), aList);
//			int bSum = bfs(bList.get(0), bList);
//			if(aSum == -1 || bSum == -1) return;
//			ans = Integer.min(ans, Math.abs(aSum - bSum));
//			return;
//		}
//		for (int i = index; i <= N; i++) {
//			aList.add(i);
//			comb(cnt - 1, i + 1);
//			aList.remove(aList.size() - 1);
//		}
//	}
//
//	public static void main(String[] args) throws IOException {
//		init();
//		for (int i = 1; i <= N / 2; i++) {
//			comb(i, 1);
//		}
//		bw.write(ans == Integer.MAX_VALUE ? -1 + "\n" : ans + "\n");
//		bw.flush();
//		bw.close();
//	}
//}
