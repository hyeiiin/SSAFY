package swea;
import java.util.*;
import java.io.*;
public class Solution_2805_김태훈 {

	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = Integer.parseInt(br.readLine());
		int[] arr = new int[t];
		
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			int sum = 0;
			int nn = n/2;
			for(int j=nn; j>=0; j--) {
				String str = br.readLine();
				for(int k=j; k<n-j; k++) {
					sum += str.charAt(k)-'0';
				}
			}
			for(int j=1; j<=nn; j++) {
				String str = br.readLine();
				for(int k=j; k<n-j; k++) {
					sum += str.charAt(k)-'0';
				}
			}
			arr[i] = sum;
		}
		
		
		
//		for(int i=0; i<t; i++) {
//			int n = Integer.parseInt(br.readLine());
//			int[][] map = new int[n][n];
//			int sum = 0;
//			for(int j=0; j<n; j++) {
//				st = new StringTokenizer(br.readLine());
//				for(int k=0; k<n; k++) {
//					map[j][k] = Integer.parseInt(st.nextToken());
//				}
//			}
//			int nn = n/2;
//			for(int j=0; j<nn; j++) {
//				
//				for(int k=0; k<j*2+1; k++) {
//					sum += map[j][k];
//				}
//			}
//			for(int j=0; j<nn; j++) {
//				for(int k=0; k<(nn-j)*2-1; k++) {
//					sum += map[j][k];
//				}
//			}
//			arr[i] = sum;
//		}
		for (int i = 0; i < t; i++) {
			System.out.printf("#%d %d\n",i+1, arr[i]);
		}
	}

}
