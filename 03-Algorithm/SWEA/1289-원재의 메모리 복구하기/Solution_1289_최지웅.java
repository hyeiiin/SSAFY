package swea_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_1289_최지웅 {

	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		String origin;
		int len;
		char[] cur;
		
		int cnt;
		
		for (int t = 1; t <= T; t++) {
			origin = br.readLine();
			len = origin.length();
			cur = new char[len];
			
			for (int i = 0; i < origin.length(); i++) {
				cur[i] = '0';
			}
			
			cnt = 0;
			
			for (int i = 0; i < len; i++) {
				if (origin.charAt(i) == cur[i]) continue;
				else {
					cnt++;
					for (int j = i; j < len; j++) {
						cur[j] = origin.charAt(i);
					}
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
	}
}
