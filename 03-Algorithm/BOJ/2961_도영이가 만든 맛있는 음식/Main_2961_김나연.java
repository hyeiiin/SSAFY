import java.util.Scanner;

public class Main_2961_김나연 {
	
	static int n, a[], b[];
	static boolean isSelected[];
	static int totalSour=1, totalBitter, res = Integer.MAX_VALUE;
	static int flag=0;
	
	public static void generatedSubSet(int cnt){
		if(cnt==n+1) {
			totalSour=1;
			totalBitter=0;
			flag=0;
			
			for (int i = 1; i <= n; i++) {
				if(isSelected[i]==true) {
					totalSour = totalSour*a[i];
					totalBitter = totalBitter+b[i];
					flag=1;
				}
			}
			if(flag==1) res=Math.min(res, Math.abs(totalSour-totalBitter));
			
		}else {
			isSelected[cnt]=true;
			generatedSubSet(cnt + 1);
			isSelected[cnt] = false;
			generatedSubSet(cnt + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n=in.nextInt();
		
		a=new int[n+1];
		b=new int[n+1];
		isSelected = new boolean[n+1];
		
		for(int i=1;i<=n;i++) {
			a[i]=in.nextInt();
			b[i]=in.nextInt();
		}

		generatedSubSet(1);
		
		System.out.println(res);
	}
}
