package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_탁하윤 {
	static final int max = 100000;
	static int N, K;
	static int[] visited = new int[max+1];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 수빈이 현재 위치
		K = Integer.parseInt(st.nextToken());	// 동생의 위치
		
		int result = bfs(N);	// 이동 시간
		System.out.println(result);
	}

	static int bfs(int v) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		q.offer(v);	// 현재 위치 넣기
		visited[v] = 1;	// 현재 위치 visited 처리
		
		while(!q.isEmpty()) {	// 공백 큐가 될 때 동안
			int cur = q.poll();	// 현재 위치 꺼내기
			
			if(cur == K) {	// 현재 위치가 동생의 위치와 같다면 1빼준 값 리턴하기
				return visited[cur]-1;	// 시작을 1부터 했기 때문
			}
			if(cur-1 >= 0 && visited[cur-1] == 0) {	// -1로 걷기한경우, 방문한적 없다면
				visited[cur-1] = visited[cur]+1;	// -1한 위치에 현재 걸린시간 +1 해주기
				q.offer(cur-1);	// q에 -1한 위치 넣기
			}
			if(cur+1 <= max && visited[cur+1] == 0) {	// +1 걷기한 경우, 방문한적 없다면
				visited[cur+1] = visited[cur]+1;	// +1한 위치에 걸린시간 +1 해주기
				q.offer(cur+1);	// cur+1한 위치 넣기
			}
			if(2*cur <= max && visited[2*cur] == 0) {	// 순간이동한 경우, 방문한적 없다면
				visited[2*cur] = visited[cur]+1;	// 순간이동한 위치에 걸린시간 +1 해주기
				q.offer(2*cur);	// 순간이동한 위치 넣기
			}
		}
		return -1;
		
	}
}
