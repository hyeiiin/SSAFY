import java.util.Scanner;

public class 연습문제2_막대 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); // 막대의 길이
		int[] memo = new int[N+1];
		memo[1] = 2;
		memo[2] = 5;
		for(int i = 3; i<=N; i++) {
			memo[i] = memo[i-1]*2+memo[i-2];
		}
		System.out.println(memo[N]);
	}

}
