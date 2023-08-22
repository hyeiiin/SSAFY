import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_13023_김도현 {

	static int N,M;
	static boolean visited[];
	static boolean result = false;
	static ArrayList<ArrayList<Integer>> list;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 사람의 수
		M = Integer.parseInt(st.nextToken()); // 연산의 수
		list = new ArrayList<>(N);			  // 특정 N번째 사람에 친구들을 넣을 리스트
		for (int i = 0; i < N; i++) {		  // N번만큼 리스트를 만들어서 초기 세팅해줌
			list.add(new ArrayList<>());
		}
		visited = new boolean[N];			  // 이미 찾은 친구 안찾게 방문 배열
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			list.get(num1).add(num2);		  // 일반적인 친구가 아닌 서로 친구여야함!
			list.get(num2).add(num1);
		}
		for (int i = 0; i < list.size(); i++) { // 몇번 째 친구부터 시작할지 다 탐색
			if(!result) {
				dfs(i,0);
			}
		}
		
		if(result) {
			System.out.println(1);
		}else {
			System.out.println(0);
		}
	}
	public static void dfs(int start, int depth) {
		if(depth>=4) {			// 4개 이상 이어져 있으면 맞음으로 결과값을 트루로
			result = true;
			return;
		}
		if(!visited[start] && !result) { // 반복문 덜 돌려야하니까 이미 친구인거 확인되면 반복안하도록
			visited[start] = true;		 // 그 친구 방문 처리하고
			for (int i = 0; i < list.get(start).size(); i++) { // 방문 처리한 친구 목록 찾고 다시 dfs
				if(!visited[list.get(start).get(i)]) {
					dfs(list.get(start).get(i),depth+1);
				}
			}
			visited[start] = false;
		}
	}
}
