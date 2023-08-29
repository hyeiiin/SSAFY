import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 연습문제2_탁하윤 {
	static int input, output, f[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = Integer.parseInt(br.readLine());	// 막대 만들 길이
		f = new int[input+1];	// 길이 방법 담을 배열
		output = dp(input);
		
		System.out.println(output);
	}
	private static int dp(int n) {	// 막대 만들수 있는 방법 개수 구하기
		f[1] = 2;
		f[2] = 5;
		for (int i = 3; i <= input; i++) {
			f[i] = f[i-1]*2 + f[i-2];
		}
		return f[n];
	}
	

}
