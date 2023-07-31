 package SWEA;

import java.util.*;

public class Solution_1289_김수린 {
	static int T;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			int ans = 0;
			char before;
			String tmp = sc.next();
			before = '0';
			for(int k = 0; k < tmp.length(); k++) {
				if(before != tmp.charAt(k)) {
					before = tmp.charAt(k);
					ans++;
				}
			}
			sb.append("#").append(i + 1).append(" ").append(ans).append("\n");
		}
		System.out.print(sb.toString());
		sc.close();
	}
}
