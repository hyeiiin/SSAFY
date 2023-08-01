import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2805_고영훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static int solution() throws Exception {
		int N = Integer.parseInt(br.readLine());
		int[][] farm = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				farm[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}
		
		int benefit = 0;
		int start = N / 2;
		int end = start + 1;
		for (int i = 0; i < N; i++) {
			for (int j = start; j < end; j++) {
				benefit += farm[i][j];
			}
			if (i < N / 2) {
				start--;
				end++;
			} else {
				start++;
				end--;
			}
		}
		return benefit;
	}
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int answer = solution();
			System.out.println("#" + t + " " + answer);
		}
	}
}
