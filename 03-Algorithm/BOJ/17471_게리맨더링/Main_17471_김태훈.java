package baekjoon;
import java.util.*;
import java.io.*;
public class Main_17471_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int n;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean selet[], visit[];
	static int arr[];
	static int min = Integer.MAX_VALUE;
	/*
	 * n = 지역 수
	 * arr = 각 구역의 인구 수
	 * sel = 부분 집합 만들때 사용
	 * visit = bfs로 연결 됐는지 확인할 때 사용
	 * list = 간선 저장 배열
	 * min = 차이 최솟값 저장(정답 입력하게 될 변수)
	 * 
	 * 풀이 : 2개의 지역구로 나누니까 2개의 부분 집합을 만든다.
	 * 부분 집합 만들때 한쪽이 0되는 경우는 처음 부터 만들지 않고 패스한다. 몰빵 되면 검사 의미가 없다
	 * 만들고 나서 dfs로 서로 연결이 되어 있는지 확인한다.
	 * 연결이 되어 있으면 그 두개의 인구수를 각자 더한다. 비교해서 더 작은거 min값에 저장한다
	 */
	static void init() throws IOException{
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		selet = new boolean[n];
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n; i++) {			
			st = new StringTokenizer(br.readLine());
			int edge = Integer.parseInt(st.nextToken());
			for (int j = 0; j < edge; j++) {
				int a = Integer.parseInt(st.nextToken())-1;
				graph.get(i).add(a);
			}
		}
	}
	
	static void subset(int idx) {
		if(idx == n) {
			ArrayList<Integer> alist = new ArrayList<>();
			ArrayList<Integer> blist = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if(selet[i]) alist.add(i);
				else blist.add(i);
			}
			if(alist.size() == 0 || blist.size() == 0) return;
			if(check(alist) && check(blist))
				min = Math.min(min, diff());
			return;
		}
		selet[idx] = true;
		subset(idx+1);
		selet[idx] = false;
		subset(idx+1);
	}
	
	static boolean check(ArrayList<Integer> list) {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		visit = new boolean[n];
		visit[list.get(0)] = true;
		que.offer(list.get(0));
		int count = 1;
		while(!que.isEmpty()) {
			int current = que.poll();
			for (int i = 0; i < graph.get(current).size(); i++) {
				int a = graph.get(current).get(i);
				if(list.contains(a) && !visit[a]) {
					que.offer(a);
					visit[a] = true;
					count++;
				}
			}
		}
		if(count == list.size()) return true;
		else return false;
	}
	
	static int diff() {
		int pA = 0;
		int pB = 0;
		for (int i = 0; i < n; i++) {
			if(selet[i]) pA += arr[i];
			else pB += arr[i];
		}
		int res = Math.abs(pA - pB);
		return res;
	}
	
	public static void main(String[] args) throws IOException{
		init();
		subset(0);
		if(min == Integer.MAX_VALUE) min = -1;
		System.out.println(min);
	}

}
