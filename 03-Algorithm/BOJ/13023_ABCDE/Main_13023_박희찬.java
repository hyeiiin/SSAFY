import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 13023
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M; // 사람, 관계의 수
	static List<ArrayList<Integer>> lst;  // 친구간 관계를 저장
	static boolean[] visited;  // 방문 체크
	static int res = 0;  // 최종 출력값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 친구 관계의 수
		
		lst = new ArrayList<>();
		
		// List<ArrayList<Integer>>의 원소 초기화
		for (int i = 0; i < N; i++) {
			lst.add(new ArrayList<Integer>());
		}
		
		// 주어진 친구 관계의 수를 List에 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			lst.get(a).add(b);
			lst.get(b).add(a);
		}
		

		// 모든 친구 검사(친구 관계가 있는 사람만)
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			if (lst.get(i).size() > 0) {
				dfs(i, 1);
			}
		}

		
		sb = new StringBuilder();
		sb.append(res);
		System.out.println(sb);

	} // Main

	static void dfs(int cur, int cnt) {
		// 문제의 조건이 1번이라도 맞으면, 더이상의 검사가 필요없음
		// 해당 Pruning이 없어서 남은 DFS()를 다 돌면 TLE.
		if (res == 1) {
			return;
		}
		
		// 조건을 만족하는 친구 관계를 찾으면 최종 출력값 표시.
		if (cnt == 5) {
			res = 1;
			return;
		}
		
		// dfs()는 본인의 친구 관계가 1개 이상인 사람이 들어올 수 있으므로,
		// 현재 본인을 방문 체크
		visited[cur] = true;
		
		// 자기가 가진 친구 관계의 수 만큼 반복
		for (int idx = 0; idx < lst.get(cur).size(); idx++) {
			// 다음 친구 관계가 방문 전이라면
			if (!visited[lst.get(cur).get(idx)]) {
				// 방문 표시
				visited[lst.get(cur).get(idx)] = true;
				
				// 재귀 호출
				dfs(lst.get(cur).get(idx), cnt + 1);
				
				// 다른 상황을 체크하기 위해 방문 롤백
				visited[lst.get(cur).get(idx)] = false;
			}
		}
		
	}  // dfs

}
