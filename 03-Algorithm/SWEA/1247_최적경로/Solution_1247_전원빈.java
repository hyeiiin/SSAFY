package swea;

import java.io.*;
import java.util.*;

public class _1247 {
	static int n;
	static int comy;
	static int comx;
	static int homey;
	static int homex;
	static int cosy[];
	static int cosx[];
	
	static void swap(int[] p, int i, int j){
		int temp = p[i];
		p[i] = p[j];
		p[j] = temp;
	}
	
	static boolean np(int[] p) {
		int n = p.length;
		int i = n-1;
		
		while(i > 0 && p[i-1] >= p[i])--i;
		if(i == 0)return false;
		
		int j = n-1;
		while(p[i-1] >= p[j])--j;
		
		swap(p, i-1, j);
		
		int k = n-1;
		
		while(i < k) {
			swap(p, i++, k--);
		}
		return true;
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(bf.readLine());
		for(int t = 1; t <= test; t++) {
			n = Integer.parseInt(bf.readLine());
			cosy = new int[n];
			cosx = new int[n];
			int[] p = new int[n];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			comx = Integer.parseInt(st.nextToken());
			comy = Integer.parseInt(st.nextToken());
			homex = Integer.parseInt(st.nextToken());
			homey = Integer.parseInt(st.nextToken());
			for(int i = 0; i < n; i++) {
				cosx[i] = Integer.parseInt(st.nextToken());
				cosy[i] = Integer.parseInt(st.nextToken());
				p[i] = i;
			}
			int d = Integer.MAX_VALUE;
			do {
				int tempd = Math.abs(comx - cosx[p[0]]) + Math.abs(comy - cosy[p[0]]);
				for(int i = 0; i < n-1; i++) {
					tempd += Math.abs(cosx[p[i]] - cosx[p[i+1]])+ Math.abs(cosy[p[i]] - cosy[p[i+1]]);
				}
				tempd += Math.abs(homex - cosx[p[n-1]]) + Math.abs(homey - cosy[p[n-1]]);
				d = Math.min(tempd, d);
			}while(np(p));
			sb.append("#").append(t).append(" ").append(d).append("\n");
		}
		System.out.println(sb.toString());
	}

}
