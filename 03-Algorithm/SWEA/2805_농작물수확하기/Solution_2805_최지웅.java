package lesson.swea_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_2805_최지웅 {

	static BufferedReader br;
	static String line;
	
	static int N;
	static int[][] arr;

	static int cal(int[][] arr) {
		int sum = 0;
		
		int start = N / 2;
		int size = 1;
		for (int i = 0; i < N; i++) {
			for (int j = start; j < start + size; j++) {
				sum += arr[i][j];
			}
			if (i < N / 2) {
				start -= 1;
				size += 2;
			}
			else {
				start += 1;
				size -= 2;
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				line = br.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = line.charAt(j) - '0';
				}
			}
			
			System.out.println("#" + t + " " + cal(arr));
		}
	}
}
