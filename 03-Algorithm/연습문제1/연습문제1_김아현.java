package ssafy;
public class 연습문제1_김아현 {

	static int[] block;

	public static void main(String[] args) {

		System.out.println(color(8));
	}

	static int color(int n) {
		block = new int[n + 1];

		if (n > 2) {
			block[0] = 0;
			block[1] = 2;
			block[2] = 3;
		}

		for (int i = 3; i < n + 1; i++) {
			block[i] = block[i - 1] + block[i - 2];
		}

		if (n == 1) {
			return 2;
		} else if (n == 2) {
			return 3;
		} else {
			return block[n];
		}
	}
}
