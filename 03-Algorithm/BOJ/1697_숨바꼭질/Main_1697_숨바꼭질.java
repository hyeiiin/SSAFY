import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질 {
	static final int Max = 1000000;
	static int N, K;
	static Queue<Integer> chaserQ;
	static boolean[] visited = new boolean[Max + 1];
	static int[] dist; //각 위치의 시간 기록용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치

		chaserQ = new LinkedList<Integer>();
		dist = new int[Max + 1];
		visited[N] = true;
		dist[N] = 0; //수빈이 위치에서 시간을 0 : 시작 위치니까
		chaserQ.add(N); // 수빈이 위치 넣기
		bfs();
		System.out.println(dist[K]);

	}

	static void bfs() {
		int time = 0;
		 while (!chaserQ.isEmpty()) {
	            int now = chaserQ.remove();
	            if (now-1 >= 0) {
	                if (visited[now-1] == false) {
	                    chaserQ.add(now-1);
	                    visited[now-1] = true; //방문처리
	                    dist[now-1] = dist[now] + 1;//시간 1 추가해서 기록
	                }
	            }
	            if (now+1 <= Max) {
	                if (visited[now+1] == false) {
	                    chaserQ.add(now+1);
	                    visited[now+1] = true;
	                    dist[now+1] = dist[now] + 1;
	                }
	            }
	            if (now*2 < Max) {
	                if (visited[now*2] == false) {
	                    chaserQ.add(now*2);
	                    visited[now*2] = true;
	                    dist[now*2] = dist[now] + 1;
	                }
	            }
		 }

	}

}