import java.util.Scanner;

public class 연습문제1_김도현 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] fn = new int[1000];
		fn[1] = 2;
		fn[2] = 3;
		for (int i = 3; i < 1000; i++) {
			fn[i] = fn[i-1]+fn[i-2];
		}
		for (int i = 3; i < fn.length; i++) {
			System.out.print(fn[i]+" ");
		}
	}

}
