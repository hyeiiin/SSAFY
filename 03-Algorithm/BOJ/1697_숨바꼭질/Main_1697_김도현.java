import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_김도현 {
	
	static Queue<Integer> que = new LinkedList<>();
	static int arr[] = new int[100001];

	static int N, K;
	static int min_time;
	static int next_time;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		if(N >= K) {
			System.out.println(N-K);
			return;
		}

		BFS();

		System.out.println(min_time);

	}

	private static void BFS() {
		min_time = Integer.MAX_VALUE/16; // 최단 시간
		que.offer(N);
		arr[N] = 1;

		while( !que.isEmpty() ) {
			int time = que.poll();
			for(int i=0; i<3; i++) {
				switch(i) {
					case 0: next_time = time + 1;
					 	break;
					case 1: next_time = time - 1;
						break;
					default: next_time = time * 2;
				}

				if(next_time == K) {
					min_time = arr[time];
                    return;
				}
				if( Range_check() && (arr[next_time] == 0 || arr[next_time] == arr[time] + 1) ) {
					que.offer(next_time);
					arr[next_time] = arr[time] + 1;
				}

			}

		}

	}

	// 범위 체크
	static boolean Range_check() {
		return (next_time >= 0 && next_time <= 100000);
	}
} 
