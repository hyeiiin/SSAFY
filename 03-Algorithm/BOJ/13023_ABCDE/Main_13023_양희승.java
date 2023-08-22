package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_13023 {
	
	static int N, M, ANSWER;
	static ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 정점 개수(N), 간선 개수(M) 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// matrix 초기화 (N개의 ArrayList를 생성)
		for (int i = 0; i < N; i++) {
			matrix.add(new ArrayList<>());
		}
		
		// 간선의 개수만큼 정점 관계를 입력 받은 후, matrix에 값 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			matrix.get(a).add(b);
			matrix.get(b).add(a);
		}
		
		// 방문 체크할 배열 생성
		visited = new boolean[N];
		
		// 정점 관계를 파악하기 위해 순회하며 조건에 부합하는지 확인
		// 조건에 부합할  경우 ANSWER를 1로 변경
		for (int i = 0; i < N; i++) {
			if (DFS(i, 0)) {
				ANSWER = 1;
				break;
			}
		}
		
		// 조건 충족 여부 출력
		System.out.println(ANSWER);
		
	}
	
	// 정점 간의 관계를 파악하는 메소드
	private static boolean DFS(int v, int depth) {
		// 만약 조건 충족 (5개의 정점을 돌 수 있는 경우)할 경우, return true
		if (depth == 4) {
			return true;
		}
		
		visited[v] = true;
		for (int i : matrix.get(v)) {
			if (!visited[i]) {
				if (DFS(i, depth + 1)) {
					return true;
				}
			}
		}
		visited[v] = false;
		
		return false;
	}
	
}
