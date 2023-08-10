import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_김나연 {
	
	static int t,n,a[],s[][],isSelected[];
	static int mn=Integer.MAX_VALUE;
	
	static void go(int cnt, int start) {
		if(cnt==n/2) {
			int total =0;
			int total2=0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(i==j) continue;
					if(isSelected[i]==1&&isSelected[j]==1) total+=s[i][j];
					else if(isSelected[i]==0&&isSelected[j]==0) total2+=s[i][j];
				}
			}
			
			mn=Math.min(mn, Math.abs(total-total2));
			
			 return;
		}
		else {
			for(int i=start;i<n;i++) {
				isSelected[a[i]]=1;
				go(cnt+1, i+1);
				isSelected[a[i]]=0;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		t=Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=t;tc++) {
			sb=new StringBuilder();
			mn=Integer.MAX_VALUE;
			
			n=Integer.parseInt(br.readLine());
		
			a=new int[n];
			s=new int[n][n];
			isSelected=new int[n];
			
			for (int i = 0; i < n; i++) {
				st=new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					s[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < n; i++) {
				a[i]=i;
			}
			
			go(0,0);
			
			sb.append("#").append(tc).append(" ").append(mn);
			System.out.println(sb);
		}
	}

}
