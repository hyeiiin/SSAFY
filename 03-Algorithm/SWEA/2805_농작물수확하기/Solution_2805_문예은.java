import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_2805_문예은 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수

		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 농장크기
			int[][] farm = new int[N][N]; // N*N 농장 배열 입력 준비
			int sum = 0; // 한칸 수익의 합
			
			for (int i = 0; i < N; i++) {
				String[] arr = br.readLine().split(""); // 공백없는 숫자 입력....
				for(int j = 0; j < N; j++) {
					farm[i][j] = Integer.parseInt(arr[j]); 
				}
			}
//			System.out.println(Arrays.deepToString(farm));
			// EX. N이 7일 때
			for(int i = 1; i <= N/2 + 1; i++) { // 1 2 3 4  다이아몬드 중간값부터 위쪽 부분
				int idx = 0;
				for(int j = 0; j <= N/2 - i; j++) { // 012, 01, 0, x 
					idx++; // 합하지 않는 부분의 인덱스 건너뛰도록 증가시킴
				}
				for (int k = 0; k < i*2 - 1; k++) { // 0, 012, 01234, 0-6
					sum += farm[i-1][idx++]; // 합하는 부분 인덱스 모두 더하도록 증가시킴
				}
			}
			int row = N/2 + 1; // 다이아몬드 아래부분, 인덱스 증가를 위해 따로 구함...
			for(int i = N/2; i >= 1; i--) { // 2 1
				int idx = 0;
				for (int j = 0; j <= N/2 - i; j++) { // 0 01
					idx++;
				}
				for (int k = 1; k <= i*2 - 1 ; k++) { // 3 1
					sum += farm[row][idx++];
				}
				row++; // 행 인덱스 추가
			}
			System.out.printf("#%d %d%n", t+1, sum);
		}
	}
}
