package algo_0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16435_조은서 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] feed = new int[N]; // 과일의 높이
		for(int i=0; i<N; i++) {
			feed[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(feed);
		
		for(int i=0; i<N; i++) {
			if(feed[i] <= L) {
				L += 1;
			}
		}

		System.out.println(L);
	}

}
