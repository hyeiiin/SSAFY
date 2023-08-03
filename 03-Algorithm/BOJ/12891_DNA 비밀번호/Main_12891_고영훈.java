import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_12891_고영훈 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int S = Integer.parseInt(st.nextToken());
		final int P = Integer.parseInt(st.nextToken());
		final int N = S - P;
		final String DNA = br.readLine();
		st = new StringTokenizer(br.readLine());
		final int A = Integer.parseInt(st.nextToken());
		final int C = Integer.parseInt(st.nextToken());
		final int G = Integer.parseInt(st.nextToken());
		final int T = Integer.parseInt(st.nextToken());

		int a = 0;
		int c = 0;
		int g = 0;
		int t = 0;
		int count = 0;
		for (int i = 0; i < P; i++) {
			switch (DNA.charAt(i)) {
			case 'A':
				a++;
				break;
			case 'C':
				c++;
				break;
			case 'G':
				g++;
				break;
			case 'T':
				t++;
				break;
			}
		}
		if (a >= A && c >= C && g >= G && t >= T) {
			count++;
		}
		for (int i = 1; i <= N; i++) {
			switch (DNA.charAt(i - 1)) {
			case 'A':
				a--;
				break;
			case 'C':
				c--;
				break;
			case 'G':
				g--;
				break;
			case 'T':
				t--;
				break;
			}
			switch (DNA.charAt(i + P - 1)) {
			case 'A':
				a++;
				break;
			case 'C':
				c++;
				break;
			case 'G':
				g++;
				break;
			case 'T':
				t++;
				break;
			}
			if (a >= A && c >= C && g >= G && t >= T) {
				count++;
			}
		}

		bw.write(Integer.toString(count));
		bw.write("\n");
		bw.flush();
	}
}
