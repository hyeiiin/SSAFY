package algo_0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_조은서 {
	static int answer =0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int size = (int) Math.pow(2, N);
		
		sol(r, c, size);
		
	}
	
	private static void sol(int r, int c, int size) {
		if(size == 1) {
			System.out.println(answer);
			return;
		}
		
		if(r < size/2 && c < size/2) {
			sol(r, c, size/2);
		}
		else if(r < size/2 && c >= size/2) {
			answer += size * size / 4;
			sol(r, c - size/2, size/2);
		}
		else if(r >= size/2 && c < size/2) {
			answer += (size * size / 4) * 2;
			sol(r - size/2, c, size/2);
		}
		else {
			answer += (size * size / 4) * 3;
			sol(r-size/2, c-size/2,size/2);
		}
	}
}
