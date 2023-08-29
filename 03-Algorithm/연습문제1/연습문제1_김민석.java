package 연습문제;

import java.io.*;

public class 연습문제1_김민석 {
	//dp를 위한 memo변수
	static int[] memo;
	/**
	 * 찾아서 배열에 넣어주기
	 * @param N
	 * @return
	 */
	static int find(int N) {
		//2보다 작을땐 N에다 1더하기
		if(N<=2) return memo[N] = N+1;
		//기존꺼에 노란색 올리는 경우 + 가장 위에꺼가 노란색일때 파란색 올리는경우
		return memo[N] = find(N-1) + find(N-2);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		memo = new int[N+1];
		System.out.println(find(N));
	}
}	
