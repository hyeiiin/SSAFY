import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int num;
	static StringBuilder sb = new StringBuilder();
	static PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			if(Math.abs(o1) == Math.abs(o2)) {
				return o1-o2;
			}
			return Math.abs(o1)-Math.abs(o2);
		}
		
	});
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		for(int i = 0; i < n; i++) {
			num = Integer.parseInt(bf.readLine());
			if(num != 0)q.add(num);
			else {
				if(q.size() == 0)sb.append(0).append("\n");
				else {
					sb.append(q.peek()).append("\n");
					q.poll();
				}
			}
		}
		System.out.println(sb.toString());
	}

}