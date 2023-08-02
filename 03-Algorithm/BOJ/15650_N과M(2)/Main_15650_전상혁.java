package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Main_15650_전상혁 {
	static int N, M, numbers[];
	static boolean[] isSelected;	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		//1부터 N까지 자연수 중 중복x M개 선택한 수열
		//고른 수열은 무조건 오름차순!!
		
		numbers = new int[M];
		isSelected = new boolean[N+1];
		
		digit(0,1);
		
		
		
	}
	private static void digit(int cnt, int start) {
		if (cnt==M) {
			for (int i = 0; i < numbers.length; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for (int i = start; i <= N; i++) {
			if (isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			digit(cnt+1, i+1);
			isSelected[i] = false;
		}
	}

}
