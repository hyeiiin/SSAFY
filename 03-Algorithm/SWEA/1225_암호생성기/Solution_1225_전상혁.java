package swea;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_1225_전상혁 {
	static Deque<Integer> deque = new LinkedList<Integer>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			int t = sc.nextInt();
			
			deque.clear();

			for (int i = 0; i < 8; i++) {
				deque.add(sc.nextInt());
			}
			make();
			System.out.printf("#%d ", tc);
			for (int num : deque) {
				System.out.print(num+" ");
			}
			System.out.println();
				
		}
	}
	private static void make() {
		boolean flag = false;
		int num = 0;
		top:
			while(!flag) {
				for (int i = 1; i <= 5; i++) {
					num = deque.poll() - i;
					if (num<=0) {
						num = 0;
						flag = true;
					}
					deque.add(num);
					if (flag) {
						break top;
					}
				}
			}
	}


}
