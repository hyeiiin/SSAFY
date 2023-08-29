import java.util.Arrays;
import java.util.Scanner;

public class 연습문제1_이세은 {
	static int[] f;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// f(8)찾기
		f = new int[N+1];
		Arrays.fill(f, -1);
		f[0] = 1;
		f[1] = 2;

		System.out.println(find(N));

	}

	public static int find(int n) {
		if (f[n] == -1 && n>1) {
			f[n] = find(n - 1) + find(n-2);
		}
		return f[n];
	}

}
