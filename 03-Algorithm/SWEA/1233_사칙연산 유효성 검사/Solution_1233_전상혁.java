package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1233_전상혁 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int res = 1;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				//정점번호 생략
				st.nextToken();
				
				char node = st.nextToken().charAt(0);
//				System.out.println(node);
				if (st.hasMoreTokens()) {
					if (node>'0' && node<='9') {
						res = 0;
					}
					
				}else {
					if (node<'0' || node>'9') {
						res = 0;
					}
				}
			}
			
			if (tc/2==0) {
				res = 0;
			}
			System.out.printf("#%d %d%n",tc,res);
			
		}
	}

}
