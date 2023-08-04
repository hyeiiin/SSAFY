package baekjoon;
import java.util.*;
import java.io.*;
public class Main_2961_김태훈 {
	static int[] sin = new int[11];
	static int[] sson = new int[11];
	static int n;
	static boolean[] isSelected = new boolean[11];
	static int best = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		/*
		 * 신맛 곱 - 쓴맛 합
		 */
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			sin[i] = Integer.parseInt(st.nextToken());
			sson[i] = Integer.parseInt(st.nextToken());
		}
		recipe(0, 1, 0, 0);
		System.out.println(best);
	}
	private static void recipe(int idx, int sin_sum, int sson_sum, int size) {
		
		if(idx == n) {
			if(size != 0)
				best = Math.min(best, Math.abs(sin_sum-sson_sum));
			return;
		}
		
		recipe(idx+1, sin_sum, sson_sum, size);
		recipe(idx+1, sin_sum*sin[idx], sson_sum+sson[idx], size+1);
	}

}

