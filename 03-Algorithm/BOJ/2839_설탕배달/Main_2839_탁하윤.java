package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_탁하윤 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		
		while(true) {
			if(N%5 == 0) {	// 5로 나누어 떨어지는 경우 몫 값 저장하고 while문 탈출
				result += N/5;
				break;
			}
			N = N-3;	// 5로 못 나누면 3빼주고 result 값 1 증가
			result++;
			if(N == 0) {	// N이 0이 됐다면 while문 종료
				break;
			}
			if(N<3) {	// 만약 N이 0이 아니고 3보다 작다면 정확하게 N 킬로그램을 만들 수 없기 때문에 -1로 값을 변경하고, while문 탈출
				result = -1;
				break;
			}
		}
		
		System.out.println(result);
		
	}

}
