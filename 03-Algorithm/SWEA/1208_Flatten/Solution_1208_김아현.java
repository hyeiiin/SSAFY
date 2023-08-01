import java.io.*;
import java.util.*;

public class _1208_Swea {

	private static int dump;
	private static int[] boxes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 0; t < 10; t++) {

			dump = Integer.parseInt(br.readLine());

			boxes = new int[100];
			StringTokenizer st = null;

			// boxes 배열 초기 설정
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				boxes[i] = Integer.parseInt(st.nextToken());
			}

			while (dump != 0) {
				Arrays.sort(boxes);
				boxes[99] -= 1;
				boxes[0] += 1;

				dump--;
			}

			Arrays.sort(boxes);
			System.out.println("#" + (t + 1) + " " + (boxes[99] - boxes[0]));

		}

	}
}
