package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point{
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution_1247_김나연 {
	
	static int t,n, x,y, isSelected[], numbers[], res;
	static int home_y, home_x, company_y, company_x;
	static Point customer[];
	
	public static void go(int cnt) {
		if (cnt == n) {
			int start_y = home_y;
			int start_x = home_x;
			int dis = 0;
			int flag = 1;

			for (int i = 0; i < n; i++) {
				int idx = numbers[i];
				dis+= Math.abs(start_x - customer[idx].x) + Math.abs(start_y - customer[idx].y);
				
				start_y = customer[idx].y;
				start_x = customer[idx].x;
				
				if (res <= dis) {
					flag = 0; break;
				}
			}

			if (flag == 1) {
				dis += Math.abs(start_x - company_x) + Math.abs(start_y - company_y);

				res = Math.min(dis, res);
			}
			
		}
		else {
			for (int i = 0; i < n; i++) {
				if (isSelected[i] == 1)continue;
				numbers[cnt] = i;

				isSelected[i] = 1;
				go(cnt + 1);
				isSelected[i] = 0;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		t=Integer.parseInt(br.readLine());
		
		for (int test_case = 1; test_case <= t; test_case++) {
			StringBuilder sb = new StringBuilder();
			n=Integer.parseInt(br.readLine());
			
			customer=new Point[n];
			isSelected=new int[n];
			numbers=new int[n];
			res=Integer.MAX_VALUE;
			
			st=new StringTokenizer(br.readLine());
			for (int i = 0; i < n + 2; i++) {
				if(i==0) {
					home_x=Integer.parseInt(st.nextToken());
					home_y=Integer.parseInt(st.nextToken());
				}else if(i==1) {
					company_x=Integer.parseInt(st.nextToken());
					company_y=Integer.parseInt(st.nextToken());
				}else {
					x=Integer.parseInt(st.nextToken());
					y=Integer.parseInt(st.nextToken());
					customer[i-2]=new Point(x, y);
				}
				
			}
			
			go(0);
			
			sb.append('#').append(test_case).append(' ').append(res);
			System.out.println(sb);
		}
		
	}

}
