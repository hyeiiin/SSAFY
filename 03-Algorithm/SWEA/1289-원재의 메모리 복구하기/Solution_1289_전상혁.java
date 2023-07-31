package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution1289 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			char[] arr = sc.next().toCharArray();
			
//			[0, 0, 1, 1]
//			System.out.println(Arrays.toString(arr));
			char[] comp_arr = new char[arr.length];
			
			for (int i = 0; i < arr.length; i++) {
				comp_arr[i] = '0';
			}
//			[0, 0, 0, 0]
//			System.out.println(Arrays.toString(comp_arr));
			int cnt = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i]!=comp_arr[i]) {
					char res = arr[i];
					for (int j = i; j < arr.length; j++) {
						comp_arr[j] = res;
					}

					cnt++;
					
				}
			}
			System.out.printf("#%d %d%n",tc,cnt);
			
		}
		

	}

}
