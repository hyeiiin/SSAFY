import java.util.Arrays;
import java.util.Scanner;

public class 연습문제2_이세은 {

	static int[] f;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// f(6)찾기
		f = new int[N + 1];
		Arrays.fill(f, -1);
		System.out.println(find(N));// 빨간 색으로만 되는 경우의 수 +1
	}

	public static int find(int n) {
		if(n ==1)
			return 2;
		if(n==2)
			return 5;
		if (f[n] == -1) {
			f[n] = find(n - 1)*2 + find(n-2);
		}
		return f[n];
	}
}
