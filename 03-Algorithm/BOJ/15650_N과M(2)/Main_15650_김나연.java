import java.util.Scanner;

public class Main_15650_김나연 {
	
	static int n;
	static int m;
	static int input[], numbers[];
	
	static void comb(int cnt, int start) {
		if(cnt==m) {
			for(int number:numbers) {
				System.out.print(number+" ");
			}
			System.out.println();
			
			return;
		}else {
			for(int i=start;i<=n;i++) {
				numbers[cnt] = input[i];
				comb(cnt+1, i+1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		n=in.nextInt();
		m=in.nextInt();
		
		input = new int[n+1];
		numbers = new int[m];
		
		for (int i = 1; i <= n; i++) {
			input[i]=i;
		}
		
		comb(0, 1);
		
	}
}
