package ssafy;

import java.util.Arrays;
import java.util.Scanner;

public class _1289_Swea {

	static int count;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			String num = sc.next();
			int idx = 0;
			char check;
			count = 0;
			check = '0';
			for (int j = 0; j < num.length(); j++) {
				if (num.charAt(j) != check) {
					check = num.charAt(j);
					count++;
				}
			}
			System.out.println("#"+(i+1)+" "+count);
		}

	}
}