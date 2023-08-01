package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2805 {

	static int[][] s = new int[49][49];
	static String ss;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(bf.readLine());
		for(int t = 1; t<= test; t++) {
			int f = Integer.parseInt(bf.readLine());
			for(int i = 0; i < f; i++) {
				ss = bf.readLine();
				for(int j = 0; j< f; j++) {
					s[i][j] = Character.getNumericValue(ss.charAt(j));
				}
			}
			int m = f/2;
			int c = 1;
			int ans = 0;
			for(int i = 0; i< (f/2 + 1); i++) {
				for(int j = 0; j< c; j++) {
					ans += s[i][m+j];
					
				}
				m--;
				c += 2;
			}
			c -= 2;
			m++;
			for(int i = f/2+1; i< f ; i++) {
				c -= 2;
				m++;
				for(int j = 0; j< c; j++) {
					ans += s[i][m+j];
				}
				
			}
			StringBuilder st = new StringBuilder();
			st.append("#" + t);
			st.append(" ");
			st.append(ans);
			
			System.out.println(st.toString());
		}
		
	}

}
