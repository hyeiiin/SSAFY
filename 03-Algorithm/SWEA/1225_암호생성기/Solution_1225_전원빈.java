package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1225 {
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test = 1; test <= 10; test++) {
			int temp = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < 8; i++) {
				q.add(Integer.parseInt(st.nextToken()));
			}
			while(temp != 0) {
				for(int i = 1; i <= 5; i++) {
					temp = q.peek();
					q.poll();
					temp -= i;
					if(temp <= 0) {
						temp = 0;
						q.add(temp);
						break;
					}else q.add(temp);
				}
			}
			sb.append("#"  + test + " ");
			for(int i = 0; i< 8; i++) {
				sb.append(q.peek() + " ");
				q.poll();
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
