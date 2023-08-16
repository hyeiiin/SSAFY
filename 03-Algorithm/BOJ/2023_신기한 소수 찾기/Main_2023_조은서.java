package algo_0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2023_조은서 {

	static boolean[] prime;
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		interPrime(1,2);
		interPrime(1,3);
		interPrime(1,5);
		interPrime(1,7);
		
		System.out.println(sb);
	}
	
	private static boolean isPrime(int num) {
		if (num==0 || num==1) {
			return false;
		}
		for(int i=2; i*i<=num;i++) {
			if(num%i==0) return false;
		}
		return true;
	}
	
	private static void interPrime(int depth, int num) {
		if(depth == N) {
			sb.append(num+"\n");
			return;
		}
		
		for(int i=0; i<=9; i++) {
			int next = num*10 + i;
			if(isPrime(next)) {
				interPrime(depth+1, next);
			}
		}
	}
}
