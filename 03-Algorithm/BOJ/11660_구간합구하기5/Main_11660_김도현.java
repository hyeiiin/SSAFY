package baekjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11660_김도현 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][N];
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(i==0) {
					arr[0][j] = Integer.parseInt(st.nextToken());
				}else {
					arr[i][j] = arr[i-1][j]+Integer.parseInt(st.nextToken());
				}
			}
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			if(x1!=0) {
				for (int j = y1; j <= y2; j++) {
					sum+=(arr[x2][j]-arr[x1-1][j]);
				}
			}else {
				for (int j = y1; j <= y2; j++) {
					sum+=(arr[x2][j]);
				}
			}
			System.out.println(sum);
		}
		
	}

}
