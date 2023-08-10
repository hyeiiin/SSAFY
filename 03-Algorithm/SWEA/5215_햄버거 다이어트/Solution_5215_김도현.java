import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215_김도현 {

	
	static int N,L,res;
	static int[] t,k;
	static boolean [] visited;
	
	public static void dfs(int start, int sum, int kal) {
		
		if(kal>L) {
			return;
		}
		res = Math.max(res, sum);
		
		for (int i = start; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(i+1,sum+t[i],kal+k[i]);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 갯수
			L = Integer.parseInt(st.nextToken()); // 최대 칼로리
			t = new int[N]; //맛 정보
			k = new int[N]; // 칼로리 정보
			visited = new boolean[N]; // 방문 여부
			res = Integer.MIN_VALUE; // 결과 값
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = Integer.parseInt(st.nextToken());
				k[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0,0);
			
			System.out.println("#"+test_case+" "+res);
		}
	}

}
