package BOJ.Test;

import java.io.*;
import java.util.*;

public class Main_11660_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	static int[][] arr, addArr;
	static int N, M, X1, Y1, X2, Y2;
	
	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		addArr = new int[N + 1][N + 1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = 0;
			while(st.hasMoreTokens()) {
				arr[i][cnt++] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= arr.length; i++) {
			for(int k = 1; k <= arr.length; k++) {
				addArr[i][k] = addArr[i][k - 1] + arr[i - 1][k - 1];
			}
		}
	}
	
	public static void solve() throws IOException {
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			X1 = Integer.parseInt(st.nextToken());
			Y1 = Integer.parseInt(st.nextToken());
			X2 = Integer.parseInt(st.nextToken());
			Y2 = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			for(int k = X1; k <= X2; k++) {
				sum += addArr[k][Y2] - addArr[k][Y1 - 1];
			}
			sb.append(sum).append('\n');
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		solve();
		System.out.print(sb.toString());
	}
}
