import java.util.Scanner;

public class Solution_1208_김나연 {
	static int c;
	static int[] a = new int[100];
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		for (int tc = 1; tc <= 10; tc++) {
			c=in.nextInt();
			
			for(int i=0;i<100;i++) {
				a[i]=in.nextInt();
			}
			
			int maxIdx=0;
			int minIdx=0;
			int maxValue=0;
			int minValue=Integer.MAX_VALUE;
			
			for (int k = 0; k < c + 1; k++) {
				maxIdx=0;
				minIdx=0;
				maxValue=0;
				minValue=Integer.MAX_VALUE;
				
				for(int i=0;i<100;i++) {
					if(maxValue<a[i]) {
						maxValue=a[i];
						maxIdx=i;
					}
					if(minValue>a[i]) {
						minValue=a[i];
						minIdx=i;
					}
				}
				a[maxIdx]--;
				a[minIdx]++;
			}
			
			System.out.println("#" + tc + " " + (maxValue-minValue));
		}
		
		
	}
}
