import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간 합 구하기 4
public class Main_11659_조은서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N, M;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		

		st = new StringTokenizer(br.readLine());
		int[] numbers = new int[N+1];
		for(int i=1; i<=N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken()) + numbers[i-1];
		}
		
		int sum[] = new int[M];
		for(int idx = 0; idx < M; idx++) {
			st = new StringTokenizer(br.readLine());
			int i =Integer.parseInt(st.nextToken());
			int j =Integer.parseInt(st.nextToken());
			
			sum[idx] = numbers[j] - numbers[i-1];
		}
		
		for(int idx = 0; idx < M; idx++) {
			System.out.println(sum[idx]);
		}
		
	}

}
