package todo.lesson._0811;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2839_최지웅 {
	
	static int minPack = 0;
	
	static final int IMPOSSIBLE = -1;
	
	static int find(int n) {
		
		for (int i = n / 5; i >= 0; i--) {
			if ((n - i * 5) % 3 == 0) {
				n -= i * 5;
				return i + n / 3;
			}
		}
		
		return IMPOSSIBLE;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
	
		System.out.println(find(N));
	}

}
