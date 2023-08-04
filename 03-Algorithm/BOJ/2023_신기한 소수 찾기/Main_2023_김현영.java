import java.io.*;

public class Main_2023_김현영 {

	// 소수 체크 함수
	public static boolean checkPrime(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0)
				return false; // 소수아님
		}
		return true; // 소수임
	}

    //start:소수를 체크할 숫자 size:자릿수
	public static void func(int start, int size) {
		if (checkPrime(start)) {
            //숫자가 소수이면서 자릿수가 맞다면 출력
			if ((int) (Math.log10(start) + 1) == size) {
				System.out.println(start);
				return;
			}
            //자릿수를 늘려가면서 신기한 소수 체크 
			for (int i = 0; i < 10; i++) {
				func(start * 10 + i, size);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		//n의 첫 시작은 소수인 2,3,5,7로 해야함
		func(2, n);
		func(3, n);
		func(5, n);
		func(7, n);

	}

}
