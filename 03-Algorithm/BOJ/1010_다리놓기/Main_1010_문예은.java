import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1010_문예은 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			// nCk 구하기 (동쪽 포인트 중에서 서쪽포인트개수만큼 뽑는 조합)
			int K = Integer.parseInt(st.nextToken()); // 서쪽 포인트
			int N = Integer.parseInt(st.nextToken()); // 동쪽 포인트
			
			int [][] dari = new int[N+1][N+1]; // 기록해둘 배열 
			
			for (int i = 0; i <= N; i++) {
				// 행 인덱스에 따라, 구하려는 K값까지만 구하면 됨(이후는 필요없음)
				for (int j = 0, end = Math.min(i, K); j <= end; j++) {
					if (j==0 || i==j) dari[i][j] = 1;  // 파스칼삼각형의 왼쪽 오른쪽 대각선은 모두 1
					else dari[i][j] = dari[i-1][j-1] + dari[i-1][j]; 
				}
			}
			sb.append(dari[N][K]+"\n");
		}
		System.out.println(sb.toString());
	}

}
