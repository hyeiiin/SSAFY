import java.util.Scanner;

public class 연습문제1_아파트 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc  = new Scanner(System.in);
		int N = sc.nextInt(); // 몇층까지 아파트를 칠한건지 받아오기
		int[][] memo = new int[N+1][2];
		
		//memo[n][0] = 노란색으로 칠할때
		//memo[n][1] = 파란색으로 칠할때
		memo[1][0] = 1;
		memo[1][1] = 1;
		for(int i=2; i<=N; i++) {
			memo[i][0] = memo[i-1][0]+memo[i-1][1];
			memo[i][1] = memo[i-1][0];
		}
		
		System.out.println(memo[N][0]+memo[N][1]);
		
	}

}
