import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1861_김나연 {
	
	static int t, n, a[][], dy[]= {-1,0,1,0}, dx[]= {0,1,0,-1};
	static int cnt, ans;
	
	public static void go(int y, int x) {
		for (int i = 0; i<4; i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			
			if(ny<0||nx<0||ny>=n||nx>=n) continue;
			if(a[y][x]+1!=a[ny][nx]) continue;
			cnt++;
			go(ny, nx);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		t=Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			sb=new StringBuilder();
			
			cnt=0;
			ans=Integer.MAX_VALUE;
			
			n=Integer.parseInt(br.readLine());
			a=new int[n][n];
			
			int mx_cnt=0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					a[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					cnt=0; 
					go(i, j);
					if(cnt>mx_cnt) {
						mx_cnt=cnt;
						ans=a[i][j];
					}else if(cnt==mx_cnt) {
						ans=Math.min(ans, a[i][j]);
					}
					
				}
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append(" ").append(mx_cnt+1);
			System.out.println(sb);
		}
		
		
	}

}
