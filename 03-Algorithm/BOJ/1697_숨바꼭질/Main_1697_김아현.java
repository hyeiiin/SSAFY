package ssafy.boj;

import java.io.*;
import java.util.*;
public class _1697_Boj {

	final static int Max = 100000;
	static int dir[] = {-1,1};
	static int[] sec;
	static int n,m, ns;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		sec = new int[Max+1];
		System.out.println(findBro(n));
		
	}

	static int findBro(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(start);
		
		
		sec[start] = 0;
		
		while (!queue.isEmpty()) {
			int temp = queue.poll();
			
			if(temp == m) {
				return sec[m];
			}
			
			for (int d = 0; d < 3; d++) {
				if(d < 2) {
					ns = temp + dir[d];
				}else {
					ns = temp*2;
				}
				
				
				if( ns>= 0 && ns <= Max && sec[ns] == 0) {
					sec[ns] = sec[temp] + 1;
					queue.offer(ns);
				}
			}
		}
		
		return 0;
	}
}