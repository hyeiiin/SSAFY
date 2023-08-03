package todo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_2023_최지웅 {
	
	static int N;
	static StringBuilder sb;
	
	static boolean isPrime(int num) {
		if (num < 2) return false;
		for (int div = 2; div * div <= num; div++) {
			if (num % div == 0) return false;
		}
		return true;
	}
	
	static void printPrime(int num, int len) {
		if (len == N) {
			if (isPrime(num)) {
				sb.append(num).append("\n");
			}
			return;
		} else if (num == 0 || isPrime(num)) {
			for (int n = 1; n <= 9; n++) {
				num *= 10;
				num += n;
				printPrime(num, len + 1);
				num -= n;
				num /= 10;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		printPrime(0, 0);
		System.out.println(sb.toString());
	}

}
