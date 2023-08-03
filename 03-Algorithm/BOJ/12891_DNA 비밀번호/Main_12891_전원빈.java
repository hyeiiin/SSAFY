import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int p;
	static int s;
	static int[] dna = new int[4];
	static int ans;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		p = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		String c;
		c = bf.readLine();
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i< 4; i++) {
			dna[i] = Integer.parseInt(st.nextToken());
		}
		int[] sample = new int[4];
		for(int j = 0; j < s; j++) {
			if(c.charAt(j) == 'A') {
				sample[0]++;
			}else if(c.charAt(j) == 'C') {
				sample[1]++;
			}else if(c.charAt(j) == 'G') {
				sample[2]++;
			}else if(c.charAt(j) == 'T') {
				sample[3]++;
			}
		}
		ans++;
		for(int j = 0; j< 4; j++) {
			if((dna[j]-sample[j]) > 0) {
				ans--;
				break;
			}
		}
		for(int i = 1; i <=p-s; i++) {
			if(c.charAt(i-1) == 'A') {
				sample[0]--;
			}else if(c.charAt(i-1) == 'C') {
				sample[1]--;
			}else if(c.charAt(i-1) == 'G') {
				sample[2]--;
			}else if(c.charAt(i-1) == 'T') {
				sample[3]--;
			}
			
			if(c.charAt(s+i-1) == 'A') {
				sample[0]++;
			}else if(c.charAt(s+i-1) == 'C') {
				sample[1]++;
			}else if(c.charAt(s+i-1) == 'G') {
				sample[2]++;
			}else if(c.charAt(s+i-1) == 'T') {
				sample[3]++;
			}
			ans++;
			for(int j = 0; j< 4; j++) {
				if((dna[j]-sample[j]) > 0) {
					ans--;
					break;
				}
			}
		}
		
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(ans);
		System.out.println(ans);
		
	}

}
