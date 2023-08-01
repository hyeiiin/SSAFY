import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_고영훈 {
	final static int T = 10;
	final static int SIZE = 100;
	final static int HEIGHT_LIMIT = 100;
	final static int[][] mat = new int[SIZE][SIZE];

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static int solution() throws Exception {
		br.readLine();
		int y = 0;
		int x = 0;
		for (int i = 0; i < SIZE; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < SIZE; j++) {
				int e = Integer.parseInt(st.nextToken());
				if (e == 2) {
					y = i;
					x = j;
				}
				mat[i][j] = e;
			}
		}
		
		while (y > 0) {
			if (x - 1 >= 0 && mat[y][x - 1] == 1) {
				while (x - 1 >= 0 && mat[y][x - 1] == 1) {
					x--;
				}
			} else if (x + 1 < SIZE && mat[y][x + 1] == 1) {
				while (x + 1 < SIZE && mat[y][x + 1] == 1) {
					x++;
				}
			}
			y--;
		}

		return x;
	}

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= T; t++) {
			int answer = solution();
			System.out.println("#" + t + " " + answer);
		}
	}
}
