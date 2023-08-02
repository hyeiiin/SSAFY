package baeckjoon;

import java.io.*;
import java.util.*;

 
public class Main_11660_±èÇö¿µ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		//Ç¥ ÀÔ·Â
		int[][] table = new int[n][n]; 		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(i == 0 && j == 0)
					table[i][j] = num;
				else if(i == 0) {
					table[i][j]= num + table[i][j-1];
				}
				else if(j == 0) {
					table[i][j] = num + table[i-1][j];
				}
				else {
					table[i][j] = num + table[i-1][j] + table[i][j-1] - table[i-1][j-1];
				}		
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			//½ÇÁ¦ ÀÎµ¦½º¸¦ À§ÇØ -1
			int startX = Integer.parseInt(st.nextToken())-1;
			int startY = Integer.parseInt(st.nextToken())-1;
			int endX = Integer.parseInt(st.nextToken())-1;
			int endY = Integer.parseInt(st.nextToken())-1;
			
			//1 1  2 3
			//0 3  2 0
			int sum = table[endX][endY];
			if(startX != 0 && startY!=0) {
				sum -= table[endX ][startY - 1]; //¼¼·ÎÁÙ»©±â				
				sum -= table[startX - 1][endY];		//°¡·ÎÁÙ»©±â
				sum += table[startX - 1][startY - 1];	//ÀÌÁßÀ¸·Î »©Áø ºÎºÐ ´õÇÏ±â
			}
			else if(startX != 0 ) {
				sum -= table[startX - 1][endY];		//°¡·ÎÁÙ»©±â
			}else if (startY != 0 ) {
				sum -= table[endX][startY - 1]; //¼¼·ÎÁÙ»©±â				
			}
			
			System.out.println(sum);
			
		} 
		
	}
}
