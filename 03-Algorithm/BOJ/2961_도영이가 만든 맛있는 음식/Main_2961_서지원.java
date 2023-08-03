package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2961_서지원 {
	
	static int n;
	static int[][] flavor;
	static int result = Integer.MAX_VALUE;
	
	private static void cook(int k, int sour, int bitter) {
		if (k == n) {
			if (bitter != 0) {
				result = Math.min(result, Math.abs(sour - bitter));				
			}
			return;
		}
		
		cook(k + 1, sour, bitter);
		cook(k + 1, sour * flavor[k][0], bitter + flavor[k][1]);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		flavor = new int[n][2];
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			flavor[i][0] = Integer.parseInt(temp[0]);
			flavor[i][1] = Integer.parseInt(temp[1]);
		}
		
		cook(0, 1, 0);
		System.out.println(result);
	}

}
