package BOJ;

import java.io.*;

public class Main_1463_김민석 {
	//dp를 이용하는 memo배열
	static int[] memo;
	static int find(int N) {
		//2이하면 초기화한 해당 횟수 출력
		if(N<=2) return memo[N];
		//값이 들어가있다면 그냥 출력
		if(memo[N] != 0) return memo[N];
		else {
			//2와 3으로 다 나누어질 경우
			if(N%6 == 0) {
				//2로 나눴을 때와 3으로 나눴을 때 더 작은횟수를 선택
				memo[N] = Math.min(find(N/3), find(N/2));
				//내려온 값이랑 -1했을 때의 횟수를 비교하고 횟수 1번 추가해서 저장
				return memo[N] = Math.min(memo[N], find(N-1))+1;
			}
			//3으로 나누어지는경우. -1했을때와 횟수를 비교하고 횟수 1번 추가해서 저장
			if(N%3 == 0) return memo[N] = Math.min(find(N/3), find(N-1))+1;
			//2로 나누어지는 경우. -1했을때와 횟수를 비교하고 횟수 1번 추가해서 저장
			if(N%2 == 0) return memo[N] = Math.min(find(N/2), find(N-1))+1;
			//2,3으로 나누어지지 않을경우 -1했을때의 횟수와 횟수 1번 추가해서 저장
			return memo[N]=find(N-1) + 1;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//넉넉하게 문제에서 제시한 만큼의 크기로 설정
		memo = new int[(int)Math.pow(10, 6)+1];
		//2일때 초기화
		memo[2] = 1;
		System.out.println(find(N));
	} 
}
