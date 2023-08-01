package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1210 {

	static int[][] l = new int[100][100];
	static int ans;
	//좌우 먼저 탐색하고 위로
	
	static void gogo(int y, int x) {
//		System.out.println(y + "\t" + x);
		if(y == 0) {//도착치
			ans = x;
			return;
		}
		//왼쪽
		if( x > 0 &&l[y][x-1] == 1) {
			int nex1 = 0;
			for(int i = 0; i <= x-1; i++) {
				if(l[y][i] == 0)nex1 = i;
				
			}
			if(nex1 == 0)x = 0;
			else x= nex1 +1;
		}//오른쪽
		else if(x < 99 && l[y][x+1] == 1) {
			int nex = 99;
			for(int i = 99; i >= x+1; i--) {
				if(l[y][i] == 0)nex = i;
				
			}
			if(nex == 99)x = 99;
			else x= nex -1;
		}
		gogo(y-1, x);//다음 ㄱ
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 1; i<=10; i++) {
			
			int a = Integer.parseInt(bf.readLine());
			for(int j = 0; j<100; j++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int k = 0; k <100; k++) {
					l[j][k]= Integer.parseInt(st.nextToken());
				}
			}
//			for(int j = 0; j<100; j++) {
//				for(int k = 0; k <100; k++) {
//					System.out.printf("%d ", l[j][k]);
//				}
//				System.out.println();
//			}
			
			
			for(int u = 0; u< 100; u++) {
				if(l[99][u] == 2) {
					gogo(99, u);
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(i);
			sb.append(" ");
			sb.append(ans);
			System.out.println(sb.toString());
		}
		
	}

}
