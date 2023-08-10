package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Pair{
	public int x;
	public int y;
	
	Pair(int x, int y){
		this.x=x;
		this.y=y;
	}
}

public class Solution_5215_김나연 {
	
	static int t,n,l,isSelected[],tt,kk, ans,mx_score;
	static Pair a[];
	
	public static void go(int cnt) {
		if(cnt==n) {
			int cal=0;
			int score=0;
			for (int i = 0; i < cnt; i++) {
				if(isSelected[i]==1) {
					score+=a[i].x;
					cal+=a[i].y;
				}
			}
    
			if(ans<score && cal<=l) {
				ans=score;
			}
			
			return;
		}
		
		isSelected[cnt]=1;
		go(cnt+1);
		isSelected[cnt]=0;
		go(cnt+1);
		
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		t=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			sb=new StringBuilder();
			st=new StringTokenizer(br.readLine());
			
			n=Integer.parseInt(st.nextToken());
			l=Integer.parseInt(st.nextToken());
			
			ans=0;
			a=new Pair[n];
			isSelected=new int[n];
			
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				
				tt=Integer.parseInt(st.nextToken());
				kk=Integer.parseInt(st.nextToken());
				
				a[i]=new Pair(tt,kk);
			}
			
			go(0);
			
			sb.append("#").append(tc).append(" ").append(ans);
			System.out.println(sb);
		}
		
		
	}

}
