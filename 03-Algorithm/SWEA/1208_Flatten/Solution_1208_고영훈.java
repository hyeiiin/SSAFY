import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_고영훈 {
	final static int T = 10;
	final static int WIDTH = 100;
	final static int HEIGHT_LIMIT = 100;

	final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] arr;

	public static int solution() throws Exception {
		int dump = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int leftI = 0;
		int rightI = WIDTH - 1;
		int leftH = 1;
		int rightH = HEIGHT_LIMIT;

		arr = new int[WIDTH];
		for (int i = 0; i < WIDTH; i++) {
			int x = Integer.parseInt(st.nextToken());
			leftH = Math.min(leftH, x);
			rightH = Math.max(rightH, x);
			arr[i] = x;
		}
		Arrays.sort(arr);

		while (dump > 0) {
			if (arr[leftI] > leftH) {
				leftI = 0;
				leftH++;
			}
			if (arr[rightI] < rightH) {
				rightI = WIDTH - 1;
				rightH--;
			}
			arr[leftI++]++;
			arr[rightI--]--;
			dump--;
		}
		if (arr[leftI] > leftH) {
			leftI = 0;
			leftH++;
		}
		if (arr[rightI] < rightH) {
			rightI = WIDTH - 1;
			rightH--;
		}
		return rightH - leftH;
	}

	public static void main(String[] args) throws Exception {
		for (int t = 1; t <= T; t++) {
			int diff = solution();
			System.out.println("#" + t + " " + diff);
		}
	}
}
