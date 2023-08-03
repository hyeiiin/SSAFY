import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_2961_고영훈 {
	static int N;
	static int[] sours;
	static int[] bitters;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		sours = new int[N];
		bitters = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sours[i] = Integer.parseInt(st.nextToken());
			bitters[i] = Integer.parseInt(st.nextToken());
		}
		final int diff = generateSubset(0, 0, 1, 0);
		bw.write(Integer.toString(diff));
		bw.write("\n");
		bw.flush();
	}
	
	public static int generateSubset(int index, int count, int sour, int bitter) {
		if (index == N) {
			if (count == 0) {
				return Integer.MAX_VALUE;
			}
			return Math.abs(sour - bitter);
		}
		return Math.min(
				generateSubset(index + 1, count + 1, sour * sours[index], bitter + bitters[index]),
				generateSubset(index + 1, count, sour, bitter)
		);
	}
}
