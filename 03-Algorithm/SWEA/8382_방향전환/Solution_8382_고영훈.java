import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_8382_고영훈 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private static int solution() throws Exception {
		final StringTokenizer st = new StringTokenizer(br.readLine());
		final int x1 = Integer.parseInt(st.nextToken());
		final int y1 = Integer.parseInt(st.nextToken());
		final int x2 = Integer.parseInt(st.nextToken());
		final int y2 = Integer.parseInt(st.nextToken());

		int xDist = Math.abs(x1 - x2);
		int yDist = Math.abs(y1 - y2);
		int count = 0;
		while (xDist != 0 && yDist != 0) {
			count += 2;
			xDist--;
			yDist--;
		}

		int dist = Math.max(xDist, yDist);
		while (dist > 1) {
			count += 4;
			dist -= 2;
		}
		return count + dist;
	}

	public static void main(String[] args) throws Exception {
		final int T = Integer.parseInt(br.readLine());
		final StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " " + solution() + "\n");
		}
		System.out.println(sb);
	}
}
