package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class _1228 {
	static int n;
	static int m;
	static int loc;
	static int k;
	static String s;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test = 1; test <= 10; test++) {
			sb.append("#").append(test).append(" ");
			List<Integer> list = new LinkedList<Integer>();
			n = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < n; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			m = Integer.parseInt(bf.readLine());
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < m; i++) {
				s = st.nextToken();
				loc = Integer.parseInt(st.nextToken());
				k = Integer.parseInt(st.nextToken());
				for(int j = 0; j < k; j++) {
					list.add(loc+j, Integer.parseInt(st.nextToken()));
				}	
			}
			
			for(int i = 0; i < 10; i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
