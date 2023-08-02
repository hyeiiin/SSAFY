import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int M, N;
	static boolean[] visited;
	static int bit[];
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N + 1];
		bit = new int[N + 1];
		func(1, 0);
		

	}  // Main
	
	public static void func(int start, int cnt) {
		if (cnt == M) {
			sb = new StringBuilder();
			for (int i = 1; i <= N; i++) {
				if(i != N && visited[i]) sb.append(i).append(" ");
				else if (i == N && visited[i]) sb.append(i);
			}
			System.out.println(sb.toString());
			return;
		}
		
		for (int i = start; i <= N; i++) { // 현재 숫자부터 N까지
			if (visited[i]) continue;  // 중복 X
			visited[i] = true;
			func(i, cnt + 1);  // 중복X을 위해 다음 반복은 현재 숫자 + 1 부터 시작
			visited[i] = false;
			
		}
	}
	
	
}
