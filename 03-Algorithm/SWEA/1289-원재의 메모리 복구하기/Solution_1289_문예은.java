package com.ssafy.Algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_1289_문예은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int cnt = 0;
			String str = br.readLine();
			char[] ch = str.toCharArray();
			if(ch[0] == '1') cnt++;
			for (int j = 1; j < ch.length; j++) {
				if(ch[j]!=ch[j-1]) cnt++;
			}
			
			System.out.printf("#%d %s%n", i+1, cnt);
		}
	}

}
