package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1233_김나연 {

//	static char[] tree;
	static int n, flag=1;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			sb=new StringBuilder();
			flag=1;
			
			n = Integer.parseInt(br.readLine());
			
			if(n%2==0) {
				for (int i = 0; i < n; i++) {
					br.readLine();
				}
				flag=0;
			}
			
			if(flag==0) {
				sb.append("#").append(tc).append(" ").append(flag);
				System.out.println(sb);
				continue;
			}
			
//			tree = new char[n+1];
			
			for (int i = 0; i < n/2; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				char y = st.nextToken().charAt(0);
				
				if(y!='-'&&y!='+'&&y!='*'&&y!='/') {
					for (int j = ++i; j < n/2; j++) {
						br.readLine();
					}
					flag=0;
					break;
				}
				
//				tree[x]=y;

				st.nextToken();
				st.nextToken();
			}
			
			
			for (int i = 0; i <= n/2 ; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				char y = st.nextToken().charAt(0);
				
				if(y=='-'||y=='+'||y=='*'||y=='/') {
					for (int j = ++i; j <= n/2; j++) {
						br.readLine();
					}
					
					flag=0;
					break;
				}
				
//				tree[x]=y;
			}
			
			sb.append("#").append(tc).append(" ").append(flag);
			System.out.println(sb);
			
//			for (int i = 0; i <= n; i++) {
//				System.out.print(tree[i]);
//			}
			
			
		}

	}

}
