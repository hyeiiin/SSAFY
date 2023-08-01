import java.util.Scanner;

public class Main_15649_김나연 {
	
	static int n;
	static int m;
	static boolean[] isSelected;
	static int[] numbers;
	
	static void perm(int cnt) {
		if(cnt==m) {
			for(int number:numbers) {
				System.out.print(number+" ");
			}
			System.out.println();
			
			return;
		}else {
			for(int i=1;i<=n;i++) {
				
				if(isSelected[i]==true) continue;
	
				numbers[cnt] = i;
				isSelected[i]=true;
				perm(cnt+1);
				isSelected[i]=false;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		isSelected = new boolean[n+1];
		numbers = new int[m];
		
		perm(0);
		
		
	}
}
