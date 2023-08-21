package algo_0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_조은서 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		
		boolean visited[] = new boolean[100001];
		visited[start] = true;
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		
		int cnt = 0;
		
		if(start==target) {
			System.out.println(cnt);
		}
		
		else {
			int qSize = queue.size();
			while(true) {
				cnt++;
	//			System.out.println(queue);
				qSize = queue.size();
				for(int i=0; i<qSize; i++) {
					int n = queue.remove();
					if(n-1 == target || n+1 == target || n*2 == target) {
						System.out.println(cnt);
						return;
					}
					if(n-1 >= 0 && !visited[n-1]) {
						visited[n-1] = true;
						queue.add(n-1);
					}
					if(n+1 <=100000 && !visited[n+1]) {
						visited[n+1] = true;
						queue.add(n+1);
					}
					if (n*2 <= 100000 && !visited[n*2]) {
						visited[n*2] = true;
						queue.add(n*2);
					}
				}
			}
		}
	}

}
