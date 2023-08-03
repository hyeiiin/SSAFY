
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 11660 구간합 구하기5
 * - 누적합 연습(memoization)
 * - 1차원 배열 누적합을 활용하기
 * s - 120436kb/1416ms
 * @author KimDaol
 *
 */
public class Main_11660_구간합구하기5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] sigma = new int[N + 1][N+1];//누적합을 저장할 배열 만들기
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) { // 1차배열 입력 받으면서 누적 시그마 계산해두기.
				sigma[i][j] = sigma[i][j-1]+Integer.parseInt(st.nextToken()); //1row씩 누적합 만들기
			}
		}
		
		StringBuilder sb= new StringBuilder();
		for(int m=0; m<M; m++) {
			//범위 정보 받기
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum=0;
			for(int x=x1; x<=x2; x++) { //x1행부터 x2행까지 M개의 행 합계차 계산해서 누적하기
				sum += sigma[x][y2] - sigma[x][y1-1];
			}
//			System.out.println(sum); // M의 범위가 10만이라 출력이 너무 많아서 시간터짐.
			sb.append(sum+"\n");
		}
		System.out.println(sb);
	}
}