package baekjoon;
import java.util.*;
import java.io.*;
public class Main_15650_김태훈 {
	static int n=0;
	static int m=0;
	static int[] numbers;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		numbers = new int[m];
		perm(0, 1);
	}
	private static void perm(int cnt, int start) {
		if(cnt == m) {
			for(int i=0; i<m; i++) {
				System.out.printf("%d ",numbers[i]);
			}
			System.out.println();
			return;
		}

		else {			
			for(int i=start; i<=n; i++) {
				numbers[cnt] = i;
				perm(cnt+1, i+1);
			}
		}
	}

}
