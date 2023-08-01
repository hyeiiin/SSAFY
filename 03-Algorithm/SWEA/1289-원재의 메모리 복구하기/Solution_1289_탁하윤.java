package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1289_탁하윤 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			String str = br.readLine();
			char init = '0';
			
			int ans = 0;
			
			for(int j=0; j<str.length(); j++) {
				char ch = str.charAt(j);
				if(ch != init) {
					ans++;
					init = ch;
				}
			}
			
			System.out.printf("#%d %d\n", i, ans);
		}

	}

}
