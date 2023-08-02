import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int M, N;
	static int start, end;
	static StringBuilder sb;
	static int[] lst;
	static int[] accumulate_sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lst = new int[N + 1];
		accumulate_sum = new int[N + 1];
		
		// lst 값
		st = new StringTokenizer(br.readLine());
		
		// (각각 처음부터 i번째 숫자까지의) 누적합
		for (int i = 0; i < N; i++) {
			accumulate_sum[i + 1] = accumulate_sum[i] + Integer.parseInt(st.nextToken());
		}
		
		// M개의 구해야할 누적값 범위 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			sb = new StringBuilder();
			sb.append(accumulate_sum[end] - accumulate_sum[start - 1]);
			System.out.println(sb.toString());
		}

	}  // Main
}
