package baekjoon;
import java.util.*;
import java.io.*;
public class Main_11659_김태훈 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[m];
		int[] sum = new int[n+1];
		int[] cum_sum = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=n; i++) {
			sum[i] = Integer.parseInt(st.nextToken());		//더해야 하는 배열
			cum_sum[i] = cum_sum[i-1]+sum[i];
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			arr[i] = cum_sum[end]-cum_sum[start-1];
			System.out.println(arr[i]);
		}
	}

}
