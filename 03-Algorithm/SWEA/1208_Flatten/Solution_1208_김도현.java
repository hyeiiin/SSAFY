package baekjun;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_1208_김도현 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		for (int test_case = 1; test_case <= 10; test_case++) {
			int dump = sc.nextInt();
			int[] arr = new int[100];
			for (int i = 0; i < 100; i++) {
				arr[i] = sc.nextInt();
			}
			
			
			Arrays.sort(arr);
			for (int i = 0; i < dump; i++) {
				arr[99] = arr[99]-1;
				arr[0] = arr[0]+1;
				Arrays.sort(arr);
			}
			int result = arr[99]-arr[0];
			System.out.println("#"+test_case+" "+result);
		}
	}
}
