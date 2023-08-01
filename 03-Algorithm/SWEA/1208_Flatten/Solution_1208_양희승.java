import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {

		for (int tc=1; tc<11; tc++) {
			
			int dump;
			int[] box;
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			String str;
			
			str = br.readLine();
			st = new StringTokenizer(str);
			
			dump = Integer.parseInt(st.nextToken());
			
			str = br.readLine();
			st = new StringTokenizer(str);
			int len = st.countTokens();
			box = new int[len];
			for (int i=0; i<box.length; i++) {
//				System.out.println(st.nextToken());
				box[i] = Integer.parseInt(st.nextToken());
			}
			
			while (dump != 0) {
				Arrays.sort(box);
				box[0]++;
				box[len-1]--;
				dump -= 1;
			}
			
			int max = Arrays.stream(box).max().getAsInt();
			int min = Arrays.stream(box).min().getAsInt();
			
			int result = max - min;
			
			System.out.printf("#%d %d\n", tc, result);
			System.out.println();
		}
		
	}
}

