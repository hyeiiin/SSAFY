import java.util.Scanner;

public class Solution_1210_김나연 {
	static int t;
	static int[][] a = new int[100][100];
	
	static int go(int y, int x) {
		y-=1;
		
		int flagL=0;
		int flagR=0;
		
		while(y>0) {
			if(flagR!=1 && x-1>=0 && a[y][x-1]==1) {
				x-=1;
				flagL=1;
			}else if(flagL!=1 &&x+1<100 && a[y][x+1]==1) {
				x+=1;
				flagR=1;
			}else if(y-1>=0 && a[y-1][x]==1) {
				y-=1;
				flagL=0;
				flagR=0;
			}
			
			
		}
		
		return x;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		for(int tc=0;tc<10;tc++) {
			t=in.nextInt();
		
			int destX=0, destY=0;
		
			for (int i = 0; i < 100; i++) {
				for (int j = 0; j < 100; j++) {
					a[i][j] = in.nextInt();
					if(a[i][j]==2) {
						destY=i;
						destX=j;
					}
				}
			}
		
			int res=go(destY, destX);
		
			System.out.println("#" + t + " " + res);
		}
		
		
	}
}
