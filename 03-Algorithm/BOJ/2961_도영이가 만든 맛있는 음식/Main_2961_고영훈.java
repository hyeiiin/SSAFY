import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_고영훈 {
	static int N;
	static int[] sours;
	static int[] bitters;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		sours = new int[N];
		bitters = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sours[i] = Integer.parseInt(st.nextToken());
			bitters[i] = Integer.parseInt(st.nextToken());
		}
		final int diff = generateSubset(0, false, 1, 0);
		System.out.println(Integer.toString(diff));
	}

	public static int generateSubset(int index, boolean food, int sour, int bitter) {
		if (index == N) {
			return food ? Math.abs(sour - bitter) : Integer.MAX_VALUE;
		}
		return Math.min(
			generateSubset(index + 1, true, sour * sours[index], bitter + bitters[index]),
			generateSubset(index + 1, food, sour, bitter)
		);
	}
}
