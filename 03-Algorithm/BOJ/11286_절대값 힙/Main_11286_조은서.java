package algo_0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_11286_조은서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) ->  {
			int abs1 = Math.abs(o1);
			int abs2 = Math.abs(o2);
			
			if(abs1 == abs2) return o1 > o2 ? 1: -1;
			return abs1 - abs2;
		});
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x!=0) {
				priorityQueue.offer(x);
			}
			else if(x==0) {
				if(priorityQueue.isEmpty()) {
					sb.append(0).append("\n");
				}
				else {
					int min = priorityQueue.poll();
					sb.append(min).append("\n");
				}
			}
		}
		System.out.println(sb);
		
	}

}
