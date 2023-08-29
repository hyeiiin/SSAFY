import java.util.Scanner;

public class 연습문제1_김도현 {

	static int fn[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		fn(sc.nextInt());
	}
	public static void fn(int num) {
		fn = new int[num+1];
		fn[1] = 2;
		fn[2] = 3;
		for (int i = 3; i <= num; i++) {
			fn[i] = fn[i-1]+fn[i-2];
		}
		System.out.println(fn[num]);
	}

}
