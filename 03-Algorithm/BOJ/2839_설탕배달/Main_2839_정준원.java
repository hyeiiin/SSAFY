package sdf;  

import java.util.*;

public class Main_2839_정준원 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N;
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int v = 0;

		while (N > 0) {

			if (N % 5 == 0) {
				v += N / 5;
				N = N % 5;

			}

			else if (N >= 3) {
				v++;
				N = N - 3;

			} else
				break;

		}

		if (N != 0) {

			System.out.println(-1);

		}

		else {
			System.out.println(v);

		}

	}

}
