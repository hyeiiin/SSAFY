package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class _1208 {
	static int[] s = new int[100];
	static int min = 987654321;
	static int max = 0;
	static int minpos;
	static int maxpos;
	static int ans;
	static int n;
	
	
	static void flat(int cnt) {
		min = 987654321;
		max = 0;
		for(int i = 0; i< 100; i++ ) {
			if(s[i]< min) {
				min = s[i];
				minpos = i;
			}
			if(s[i] > max) {
				max = s[i];
				maxpos = i;
			}
		}
		if(cnt == 0) {
			ans = max - min;
			return;
		}
		s[minpos]++;
		s[maxpos]--;
		flat(--cnt);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 1; i <= 10; i++) {
			n = Integer.parseInt(bf.readLine());
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j = 0; j< 100; j++ ) {
				s[j] = Integer.parseInt(st.nextToken());
			}
			flat(n);
			StringBuilder sb = new StringBuilder();
			sb.append("#"+i+" "+ans);
			System.out.println(sb.toString());
		}
	}

}
