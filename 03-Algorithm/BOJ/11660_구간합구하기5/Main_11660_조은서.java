package algo_0802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//구간 합 구하기 5
public class Main_11660_조은서 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// N x N 표 입력 받기
		int[][] map = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) + map[i][j-1]; // 행 별로 누적합 저장
			}
		}
		
		// x1, y1, x2, y2 입력 받기
		for(int idx=0; idx<M; idx++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			for(int i = x1; i <= x2; i++) {
				sum += (map[i][y2] - map[i][y1-1]); // 행마다 구간 합을 구하고 출력값(=sum)에 더해주기
			}
			sb.append(sum);
			sb.append("\n");
			
		}
		System.out.println(sb); // * sysout은 시간 초과가 난다..

	}

}
