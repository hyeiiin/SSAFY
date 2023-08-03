package lesson.boj_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_최지웅 {
	
	static int N;
	static int[] sArr;
	static int[] bArr;
	
	static int calDiff(int bit) {
		int S = 1, B = 0;
		for (int i = 0; i < N; i++) {
			if ((bit & (1 << i)) != 0) {
				S *= sArr[i];
				B += bArr[i];
			}
		}
		return Math.abs(S - B);
	}

	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		sArr = new int[N];
		bArr = new int[N];
		
		StringTokenizer st;
		int S, B;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			
			S = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			sArr[n] = S;
			bArr[n] = B;
		}
		
		int minDiff = Integer.MAX_VALUE;
		for (int bit = 1; bit < (1 << N); bit++) {
			minDiff = Math.min(minDiff, calDiff(bit));
		}
		
		System.out.println(minDiff);
	}

}
