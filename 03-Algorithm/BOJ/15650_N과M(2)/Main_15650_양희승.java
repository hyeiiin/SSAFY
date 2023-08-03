package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3_15650 {
	
	static int n, m, arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String input = br.readLine();
		st = new StringTokenizer(input);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[m];
		recur(0, 1);
		
	}
	
	private static void recur(int depth, int start) {
		
		if (depth == m) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			
			System.out.println();
			return;
		}
		for (int i = start; i <= n; i++) {
			arr[depth] = i;
			recur(depth+1, i+1);
		}
		
	}
	
}
