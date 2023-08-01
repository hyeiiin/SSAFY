import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String str;
		int T=Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			int n=Integer.parseInt(br.readLine());
			int answer=0,gap;
			int[][] arr=new int[n][n];
			for(int i=0;i<n;i++) {
				str=br.readLine();
				for(int j=0;j<n;j++) {
					arr[i][j]=str.charAt(j)-'0';
				}
			}
			for(int i=0;i<=n/2;i++) {
				for(int j=n/2-i;j<=n/2+i;j++) {
					answer+=arr[i][j];
				}
			}
			for(int i=n-1;i>n/2;i--) {
				gap=n-i-1;
				for(int j=n/2-gap;j<=n/2+gap;j++) {
					answer+=arr[i][j];
				}
			}
			System.out.println("#"+tc+" "+answer);
		}
	}
}
