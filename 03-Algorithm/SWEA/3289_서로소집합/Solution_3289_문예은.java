import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3289_문예은 {
	static int N; // 초기 집합 개수
	static int parents[];
	
	// 모든 원소는 자기만 원소로 갖고,자신이 대표자인 서로소 집합이 되도록
	private static void make() { 
		parents = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	// 부모 노드 찾기
	private static int find(int a) {
		if (a==parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot!=bRoot) parents[bRoot] = aRoot; // 서로 다른 집합에 속해있을 때만 합치기
	}
	private static int isUnion(int a, int b) { // a가 속합 집합과 b가 속한 집합을 통합할 수 있으면 1, 아니면 0 반환
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot==bRoot) return 1; // 대표자가 같음, 즉 이미 같은 집합인 상황
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		for (int t = 1; t <= T; t++) {
			sb.append("#"+t+" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 원소 개수
			make();
			
			int M = Integer.parseInt(st.nextToken()); // 연산 개수
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken()); // 연산 선택
				int a = Integer.parseInt(st.nextToken()); // 원소 a 번호
				int b = Integer.parseInt(st.nextToken()); // 원소 b 번호
				int result = 0; // 최종 출력할 union 가능 여부
				if (op == 0) union(a, b); // 합치기 메소드 실행
				else { // 합치기 가능 여부 출력 메소드
					result = isUnion(a, b);
					sb.append(result); // 공백없이 결과 쌓기
				}
			}
			sb.append("\n"); // 다음 테스트케이스 출력을 위한 줄바꿈
		}
		System.out.println(sb.toString());
	}

}
