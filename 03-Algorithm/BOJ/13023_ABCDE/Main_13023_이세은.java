import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13023_이세은 {

	private static List<Integer>[] arr;
	private static boolean[] visited;
	private static boolean end = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람 수
		int m = Integer.parseInt(st.nextToken()); // 관계 수
		arr = new ArrayList[n];
		visited = new boolean[n];// 0번 사람부터 깊이가 5가 되는 부분있는지 탐색

		// n명 만큼 리스트 초기화
		for (int i = 0; i < n; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 서로 친구 관계 업데이트
			arr[a].add(b);
			arr[b].add(a);
		}

		for(int i=0 ;i<n; i++) {
			dfs(i, 1);//현재 나 자신부터 시작하므로 카운트 1부터 센다
			if(end) {
				System.out.println(1);
				return;
			}
		}
		System.out.println(0);
	}
	
	public static void dfs(int p, int cnt) {
		if(end || cnt == 5) {//카운트 모두 끝내면 실행중인 재귀 모두 끝내기
			end = true;
			return;
		}
		visited[p] = true; //현재 사람 방문처리
		
		//이어진 친구들 탐색
		for(int i=0; i<arr[p].size(); i++) {
			int r = arr[p].get(i);
			if(!visited[r]) {
				visited[r] = true;
				dfs(r, cnt+1); //다음 친구 엮인 사람 탐색, 카운트 각 재귀의 매개변수에 넣어준다
			}
		}
		//이어진 친구들 재귀 돌린 후 탐색했던 사람 다시 방문처리 취소, 이후 다음 사람들 재귀에서 친구 될 수 있기 때문에
		visited[p] = false;
	}
}
