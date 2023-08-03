package sdf;

import java.util.ArrayList;
import java.util.Scanner;

public class Main_2023_정준원 {
	static ArrayList<Integer> list;
	static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		list = new ArrayList<>();
		int[] arr = { 2, 3, 5, 7 };

		for (int i = 0; i < arr.length; i++) {
			dfs(arr[i], 0);
		}

		for (int i : list)
			System.out.println(i);

	}

	static void dfs(int v, int d) {

		if (d == N - 1) {
			list.add(v);
			return;
		}

		for (int i = 1; i < 10; i++) {
			if (issosu(10 * v + i))
				dfs(10 * v + i, d + 1);
		}

	}

	static boolean issosu(int v) {
		int cnt = 0;

		if (v == 1)
			return false;

		for (int i = 2; i <= Math.sqrt(v); i++) {
			if (v % i == 0)
				return false;
		}

		return true;
	}

}
