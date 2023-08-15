package ssafy.Boj;

import java.io.*;
import java.util.*;

// 설탕배달
public class Main_2839_김아현 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int count = 0;

		// n이 0이 아닐 때까지 반복
		while (n != 0) {
			if (n % 5 == 0) {
				int temp = (int) n / 5;
				count += temp;
				n -= temp * 5;
				break;
			}
			
			n -= 3;
			count++;
			
			if (n != 0 && n < 3) {
				count = -1;
				break;
			}
		}
		System.out.println(count);
	}
}