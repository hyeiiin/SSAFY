import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926_김나연 {
	
	static int n,m,r;
	static int[][] a, b;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		a=new int[n+1][m+1];
		b=new int[n+1][m+1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				a[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k=0;k<r;k++) {
			int cnt=0;
			
			while(true) {
				if(n/2<=cnt||m/2<=cnt) break;
				
				for(int i=1+cnt;i<n-cnt;i++) {
					b[i+1][1+cnt]=a[i][1+cnt];
				}
				for(int i=1+cnt;i<m-cnt;i++) {
					b[n-cnt][i+1]=a[n-cnt][i];
				}
				for(int i=n-cnt;i>1+cnt;i--) {
					b[i-1][m-cnt]=a[i][m-cnt];
				}
				for(int i=m-cnt;i>1+cnt;i--) {
					b[1+cnt][i-1]=a[1+cnt][i];
				}
				
				cnt++;
			}
			
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					a[i][j]=b[i][j];
				}
			}
			
		}
		
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				sb.append(b[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
		
	}

}
