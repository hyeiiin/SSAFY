package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_전상혁 {
	static int N, S, B, input[][];
	static boolean isSelected[];
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		input = new int[N][2];
		isSelected = new boolean[N];
		
//		int mult = 1;
//		int sum = 0;
//		int diff = 0;
//		st = new StringTokenizer(br.readLine());
//		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken()); //S
			input[i][1] = Integer.parseInt(st.nextToken()); //B	
			
			//00 01 10 11
//			mult *= S;
//			sum += B;
//			
//			diff = Math.abs(mult-sum);
				
		}
		min = Integer.MAX_VALUE;
		
		ps(0);
		
		System.out.println(min);
		
			//신맛= 곱
			//쓴맛= 합	
		

	}
	private static void ps(int cnt) {
		if (cnt==N) {
			int mult = 1;
			int sum = 0;
			int count = 0;
			
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					
					mult *= input[i][0];
					sum += input[i][1];
//					diff = Math.abs(mult-sum);
					count++;
				}
			}
			if (count == 0) return;
			if (min > Math.abs(mult-sum)) {
				min = Math.abs(mult-sum);
			}
			return;
		}	
		isSelected[cnt] = true;

		ps(cnt+1);
		isSelected[cnt] = false;
		ps(cnt+1);
		
	}

}
