package todo.lesson._0817;

import java.io.*;
import java.util.*;

public class Solution_1247_최지웅 {

	static int N;
	
	static boolean[] selected;
	static int[] seq;
	
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static Point company;
	static Point home;
	static List<Point> customers;
	
	static int minDist;
	
	static int calDist(Point a, Point b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y); 
	}
	
	static void visit() {
		
		int sumDist = 0;
		
		sumDist += calDist(company, customers.get(seq[0]));
		
		for (int i = 0; i < N - 1; i++) {
			sumDist += calDist(customers.get(seq[i]), customers.get(seq[i + 1]));
		}
		
		sumDist += calDist(customers.get(seq[N - 1]), home);
		
		minDist = Math.min(minDist, sumDist);
	}
	
	static void permutation(int count) {
		if (count == N) {
			visit();
		} else {
			for (int i = 0; i < N; i++) {
				if (selected[i]) continue;
				selected[i] = true;
				seq[count] = i; 
				permutation(count + 1);
				selected[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		int x, y;
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			customers = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			company = new Point(x, y);

			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Point(x, y);
			
			for (int n = 1; n <= N; n++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customers.add(new Point(x, y));
			}
			
			minDist = Integer.MAX_VALUE;
			selected = new boolean[N];
			seq = new int[N];
			permutation(0);
			
			sb.append(String.format("#%d %d\n", t, minDist));
		}
		
		System.out.println(sb);
		
	}

}
