import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_김나연 {
	
	static int n,k, visited[];
	
	public static void go(int here, StringBuilder sb) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		visited[here]=1;
		
		queue.offer(here);
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			if(current==k) sb.append(visited[current]-1);
			
			if(current-1>=0&&visited[current-1]==0) {
					queue.offer(current-1);
					visited[current-1]=visited[current]+1;
			}
			if(current+1<=100000&&visited[current+1]==0) {
					queue.offer(current+1);
					visited[current+1]=visited[current]+1;
			}
			if(current*2<=100000&&visited[current*2]==0) {
					queue.offer(current*2);
					visited[current*2]=visited[current]+1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		visited=new int[100004];
		
		if(n==k) {
			sb.append(0);
			System.out.println(sb);
			return;
		}
		
		go(n, sb);
		System.out.println(sb);
	}

}
