package 연습문제;

import java.io.*;

public class 연습문제2_김민석 {
	//dp를 위한 memo배열
	static int[] memo;
	/**
	 * 찾아서 배열에 넣어주기
	 * @param N
	 * @return
	 */
	static int find(int N) {
		//2보다 작으면 해당 값 리턴
		if(N<=2) return memo[N];
		//이전상태에 노,파 붙이는 경우의 수 + 그 이전상태에 빨간색 하나 붙이는 경우의수
		return memo[N] = find(N-1)*2 + find(N-2);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N+1];
		//파 
		//노
		memo[1] = 2;
		
		//파파
		//파노
		//노파
		//노노
		//빨
		memo[2] = 5;
		System.out.println(find(N));
	}
}	
