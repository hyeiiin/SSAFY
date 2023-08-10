package swea;

import java.io.*;
import java.util.*;

public class _6808 {
	
	static int win;
	static int lose;
	
	static void swap(int[] p, int i, int j) {
		int temp = 0;
		temp = p[i];
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
		int t = Integer.parseInt(bf.readLine());
		for(int test = 1; test <= t; test++) {
			int[] gyu = new int[9];
			int[] in = new int [9];
			int[] use = new int [19];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < 9; i++) {
				gyu[i] = Integer.parseInt(st.nextToken());
				use[gyu[i]] = 1;
			}
			int w = 0;
			for(int i = 1; i <= 18; i++) {
				if(use[i] == 1)continue;
				else {
					in[w] = i;
					w++;
				}
			}

			win = 0;
			lose = 0;
			do {
				int who = 0;
				int gup = 0;
				int inp = 0;
				for(int i = 0; i < 9; i++) {
					who = gyu[i] - in[i];
					if(who >= 0)gup +=gyu[i] + in[i];
					else if(who < 0)inp += gyu[i] + in[i];
				}
				if(gup > inp)win++;
				else lose++;
			}while(np(in));
			sb.append("#").append(test).append(" ").append(win).append(" ").append(lose).append("\n");
			
		}
		System.out.println(sb.toString());
	}

}
