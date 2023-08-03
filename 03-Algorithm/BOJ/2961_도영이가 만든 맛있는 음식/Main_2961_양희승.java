import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, min = Integer.MAX_VALUE;
	static int[][] sb;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		// 재료, 중복제거를 위한 배열 선언 및 입력
		sb = new int[n][2];
		visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			sb[i][0] = Integer.parseInt(st.nextToken());
			sb[i][1] = Integer.parseInt(st.nextToken());
		}
		
		subset(0);
		System.out.println(min);
	}
	
	static void subset(int depth) {
		
		if(depth == sb.length) {
			
			int s = 1;
			int b = 0;
			int cnt = 0;
			for(int i = 0; i < visited.length; i++) {
				if(visited[i]) {
					cnt++;
					s *= sb[i][0]; 
					b += sb[i][1];
				}
			}
			
			// 아무 음식도 선택되지 않은 경우는 return
			if(cnt == 0)
				return;
			
			// 최솟값 갱신
			min = Math.min(min, Math.abs(b-s));
			return;
		}
		
		// 현재 재료 선택 > 다음 재료 선택
		visited[depth] = true;
		subset(depth+1);
		
		// 현재 재료 미선택 > 다음 재료 선택
		visited[depth] = false;
		subset(depth+1);
	}
}
