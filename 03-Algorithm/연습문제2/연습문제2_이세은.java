import java.util.Arrays;
import java.util.Scanner;

public class ��������2_�̼��� {

	static int[] f;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		// f(6)ã��
		f = new int[N + 1];
		Arrays.fill(f, -1);
		System.out.println(find(N));// ���� �����θ� �Ǵ� ����� �� +1
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
