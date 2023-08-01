import java.io.*;
import java.util.*;

public class Solution_1208_이세은 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// 테스트 케이스 10개
		for (int test_case = 1; test_case <= 10; test_case++) {
			int dump = Integer.parseInt(br.readLine());

			int[] stage = new int[100];

			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int i = 0; i < stage.length; i++) {
				int height = Integer.parseInt(st.nextToken());
				stage[i] = height;
			}
			
			
			for(int i=0; i<dump; i++) {
				Arrays.sort(stage);
				
				if(stage[stage.length-1]-stage[0] <=1)
					break;
				stage[0]++;
				stage[stage.length-1]--;
			}
			Arrays.sort(stage);
			bw.write("#" +test_case+" "+String.valueOf(stage[stage.length-1]-stage[0])+"\n");
			bw.flush();

		}
		br.close();
		bw.close();

	}
}
