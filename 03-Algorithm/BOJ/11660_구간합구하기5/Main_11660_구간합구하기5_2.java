
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 11660 구간합 구하기5
 * - 누적합 연습(memoization)
 * - 2차원 배열의 누적합
 * s-130228kb/872ms
 * @author KimDaol
 *
 */
public class Main_11660_구간합구하기5_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] sigma = new int[N + 1][N+1]; //누적합을 저장할 2차원 배열
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) { // 2차원 누적 시그마 배열 만들기
				sigma[i][j] = sigma[i-1][j]+sigma[i][j-1]-sigma[i-1][j-1]+Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb= new StringBuilder();
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum=0;
			sum = sigma[x2][y2]-sigma[x1-1][y2]-sigma[x2][y1-1]+sigma[x1-1][y1-1];
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	}
}