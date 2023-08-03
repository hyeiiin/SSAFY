import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static long s[];
	static long b[];
	static long ans = Long.MAX_VALUE;
	static int flag;
	
	static void suffle(int cnt, int st, long totals, long totalb) {
		if(cnt == n) {
			if(totals ==1 && totalb == 0 && flag == 0)return;
			
			long temp = 0;
			if(totalb>totals)temp = totalb-totals;
			else temp = totals-totalb;
			
			ans = Math.min(ans, temp);
			return;
		}
		flag += 1;

		suffle(cnt+1, st+1, totals*s[st], totalb + b[st]);
		flag -= 1;

		suffle(cnt+1, st+1, totals, totalb);
	
		
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(bf.readLine());
		s = new long[n];
		b = new long[n];
		for(int i = 0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			s[i] = Integer.parseInt(st.nextToken());
			b[i] = Integer.parseInt(st.nextToken());
		}
	
		suffle(0, 0, 1, 0);
		StringBuilder sb = new StringBuilder();
		sb.append(ans);
		System.out.println(ans);
	}

}
