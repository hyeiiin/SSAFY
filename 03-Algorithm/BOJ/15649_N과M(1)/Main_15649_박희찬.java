import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
	static int N, M;
	static boolean[] visited;
	
	public static void def(int cnt, List lst, boolean[] visited) {
		if(cnt == M) {  // END : 구해야 할 수열의 길이
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lst.size(); i++) {
				sb.append(lst.get(i)).append(" ");
			}
			System.out.println(sb.toString());
			return;
		}
		
		else {
			for (int i = 1; i <= N; i++) {  // 1부터 N까지의 자연수
				if (visited[i]) continue;  // 1. 이미 사용한 숫자라면 pass
				
				// 2. 사용하지 않은 숫자를 추가
				lst.add(i);
				visited[i] = true;
				def(cnt + 1, lst, visited);  // 현재까지의 넘버링 상태로 재귀
				visited[i] = false;  // 다른 조합을 찾기위해 미사용 처리
				lst.remove(lst.size()-1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N + 1];  // 숫자 방문 처리
		List<Integer> lst = new ArrayList<>();
		
		// 구해야할 수열의 길이, 수열 리스트, 방문 처리
		def(0, lst, visited);
		
	}
	
}
