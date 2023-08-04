import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Solution_1225_김나연 {
	
	static int t, temp, mn=Integer.MAX_VALUE;
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			t = in.nextInt();
			
			Deque<Integer> q = new ArrayDeque<Integer>();
			
			for (int i = 0; i < 8; i++) {
				temp = in.nextInt();
				q.add(temp);
			}
			
			int cnt=1;
			
			while(true) {
				int front = q.poll();
				
				if(front-cnt<=0) {
					q.add(0);
					break;
				}
				q.add(front-cnt);
				
				cnt++;
				
				if(cnt==6)cnt=1;
			}
			
			System.out.print("#" + t + " ");
			for(int i=0;i<8;i++) {
				System.out.print(q.poll() + " ");
			}
			System.out.println();
		}
		
	}
}
