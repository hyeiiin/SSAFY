import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3289_김도현 {

	static int[] parents;
	static int N,M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = null;
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 초기 집합의 갯수
			M = Integer.parseInt(st.nextToken()); // 연산의 개수
			parents = new int[N+1]; // N만큼 집합을 만들어주고
			sb = new StringBuilder();
			make();
			sb.append("#"+tc).append(" ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int command = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(command==0) {
					union(a, b);
				}
				else if(command==1) {
					sb.append(Check(a, b));
				}
			}
			System.out.println(sb.toString());
		}
	}
	
	private static void make() {
		parents = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			parents[i] = i; // 모든 요소는 자기만 원소로 갖는 단위 서로소 집합이 되게 한다.(자신이 곧 대표자인 루트로 표현)
		}
	}
	
	private static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	// a가 속한 집합과 b가 속한 집합을 합칠 수 있다면 합치고 true 반환, 아니면 false 반환
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false; // 서로의 대표자가 같은 즉, 같은 집합의 상항이므로 union하지 않음.
		// union 처리( bRoot를 aRoot 아래로 붙이기!! : 임의로 ..)
		parents[bRoot] = aRoot;
		return true;
	}
	private static int Check(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return 1; // 서로의 대표자가 같으면 1 아니면 0
		return 0;
	}

}
