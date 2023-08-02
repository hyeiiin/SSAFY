import java.util.Scanner;

public class Main_11659_김나연 {
	
	static int n,m,temp;
	static int a[];
	static int s, e;
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		n=in.nextInt();
		m=in.nextInt();
		
		a = new int[n+1];
		
		for(int i=1;i<=n;i++) {
			temp=in.nextInt();		
			a[i]=a[i-1]+temp;
		}
		
		for(int i=0;i<m;i++) {
			s=in.nextInt();
			e=in.nextInt();
			
			System.out.println(a[e]-a[s-1]);
		}
		
	}

}
