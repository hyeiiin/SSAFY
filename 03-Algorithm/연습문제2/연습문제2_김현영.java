import java.util.Scanner;

public class 연습문제2_김현영 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] f = new int[n + 1];
		f[1] = 2;
		f[2] = 5;
		for (int i = 3; i <= n; i++) {
			f[i] = f[i - 1] * 2 + f[i - 2];
		}

		System.out.println(f[n]);

	}

}
