import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_한정수 {
	static int N, K;
//	static int mult_cnt; 
//	static int plus_cnt;
	
	static int min_cnt = Integer.MAX_VALUE;
	static boolean[] visited;
	static Queue<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];
		queue = new ArrayDeque<>();
		
		//일단 N을 큐에 넣고, N을 꺼내고, N-1, N+1, 2N을 큐에 박는다. visited 처리도 해주고.
		//현재 큐 개수 세고, 그 수만큼 for문 돌림.
		//N-1을 꺼내고 시작할텐데, 마찬가지로 N-2, N, 2N-2를 보면서, 이미 N의 경우 방문했던 적이 있고, 이미 방문했던 숫자를 방문하면 최댓값이 절대 안나옴.
		//...
		//이런식으로 진행하면서 큐 개수세고 for문 돌리기 전에 depth를 1씩 올려주면서, K를 찾으면 그때 depth가 최솟값임.
	
		int depth = 0;
		queue.add(N);
		visited[N] = true;
		int temp = 0;
		int len = 0;
		boolean found_K = false;
		while(!queue.isEmpty() && !found_K) {
			len = queue.size();
			depth += 1;
			for (int i=0 ; i<len ; i++) {
				temp = queue.poll();
				if (temp == K) {
					System.out.println(depth -1);
					found_K = true;
					break;
				}
				if(temp-1 >= 0 && !visited[temp-1]) {
					visited[temp-1] = true;
					queue.add(temp-1);
				}
				if(temp+1 <= 100000 && !visited[temp+1]) {
					visited[temp+1] = true;
					queue.add(temp+1);
				}
				if(temp*2 <= 100000 && !visited[temp*2] ) {
					visited[temp*2] = true;
					queue.add(temp*2);
				}
			}
			
		}
		
		
	}
	
}
