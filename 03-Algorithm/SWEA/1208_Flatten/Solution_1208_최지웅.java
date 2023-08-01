package lesson.swea_ans;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_최지웅 {
	
	static final int rowSize = 100;
	
	static int numDump;
	static int[] height = new int[rowSize];
	
	static BufferedReader br;
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws Exception {
		
		System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			numDump = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int r = 0; r < rowSize; r++) {
				height[r] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(height);
			for (int d = 1; d <= numDump; d++) {
				height[0] += 1;
				height[rowSize - 1] -= 1;
				Arrays.sort(height);
			}
			Arrays.sort(height);
			
			System.out.println("#" + t + " " + (height[rowSize - 1] - height[0]));
		}
	}

}
