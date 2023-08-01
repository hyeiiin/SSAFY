package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _1289 {
	static int n;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		for(int i = 1; i<=n; i++) {
			char flag = '0';
			int ans = 0;
			String s = bf.readLine();
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(j) != flag) {
					ans++;
					if(flag == '0')flag = '1';
					else flag = '0';
				}
			}
			System.out.println("#" + i + " "+ ans);
			
		}

	}

}
