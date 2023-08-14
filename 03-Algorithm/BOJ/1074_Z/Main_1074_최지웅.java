package todo.lesson._0814;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_최지웅 {
	
	static int N;
	static int r, c;
	
	static boolean found = false;
	static int count = 0;
	
	static void DnC(int y, int x, int size) {
		if (found) return;
		if (!(y <= r && r < y + size && x <= c && c < x + size)) {
			count += size * size;
			return;
		}
		if (size == 2) {
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if (y + i == r && x + j == c) {
						found = true;
						return;
					}
					count++;
				}
			}
		} else {
			int subSize = size / 2;
			DnC(y, x, subSize);
			DnC(y, x + subSize, subSize);
			DnC(y + subSize, x, subSize);
			DnC(y + subSize, x + subSize, subSize);
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		DnC(0, 0, 1 << N);
		System.out.println(count);
	}

}
