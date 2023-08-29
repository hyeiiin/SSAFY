import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 연습문제1_탁하윤 {
	static int input, result, f[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = Integer.parseInt(br.readLine());	// 색칠할 아파트 층
		f = new int[input+1];	// 1부터 시작하기 때문에 +1 해주기
		result = apart(input);	// 색칠할 수 있는 방법 수 
		
		System.out.println(result);
	}

	private static int apart(int n) {	// apart 색칠하기
		f[1] = 2;
		f[2] = 3;
		
		for (int i = 3; i <= n; i++) {
			f[i] = f[i-1] + f[i-2];
		}
		return f[n];
	}
	
	

}
