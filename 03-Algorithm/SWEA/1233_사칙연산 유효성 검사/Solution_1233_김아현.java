package ssafy.Swea;

import java.io.*;
import java.util.*;

// 사칙연산 유효성 검사
public class Solution_1233_김아현 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
	
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			boolean check = true;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				char node = st.nextToken().charAt(0);
				char op = st.nextToken().charAt(0);
				
				// 리프노드 판단
				if(!st.hasMoreTokens()) {
					if(op < '1' || op > '9') {
						check = false;
					}
				}
			}
			
			if(check) {
				System.out.println("#"+t+" " + 1);
			}else {
				System.out.println("#"+t+" " + 0);
			}
		}
	}
}
