package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Solution_1208_전상혁 {
	static int dump;
	static int[] num;
	public static void main(String[] args) throws IOException, NoSuchElementException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for (int tc = 1; tc <= 10; tc++) {

			dump = Integer.parseInt(br.readLine());
			
			num = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 100; i++) {
				num[i] = Integer.parseInt(st.nextToken());

			}

			pre(dump);
			
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			for (int n : num) {
				if (n>max) {
					max = n;
				}
				if (n<min) {
					min = n;
				}
			}
			
			System.out.printf("#%d %d%n", tc, max-min);
			
		}
		

	}
	private static void pre(int d) {
		while(d!=0) {
			Arrays.sort(num);
			num[num.length-1] -= 1;
			num[0] += 1;
			
			d--;
					
		}
	}

}
