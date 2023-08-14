import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int r;
	static int c;
	static int ga;
	static int cnt;
	static int ans;
	
	static void divi(int y, int x , int ga){
		if(ga == 2) {
			if(r == y && c == x) {
				ans +=0;
				return;
			}else if(r == y && c == x+1 ) {
				ans += 1;
				return;
			}else if(r == y+1 && c == x) {
				ans+= 2;
				return;
			}else if(r == y+1 && c == x+1) {
				ans +=3;
				return;
			}
		}
		if(r < y+ga/2 && c < x+ga/2) {
			divi(y, x, ga/2);			
		}else if(r < y+ga/2 && c >= x+ga/2 ) {
			ans += ga*ga/4;
			divi(y, x+ga/2, ga/2);
		}else if(r >= y+ga/2 && c < x+ga/2) {
			ans += ga*ga/2;
			divi(y+ga/2, x, ga/2);
		}else if(r >= y+ga/2 && c >= x+ga/2) {
			ans += ga*ga/4*3;
			divi(y+ga/2, x+ga/2, ga/2);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(bf.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		ga = (int)Math.pow(2, n);
		divi(0, 0, ga);
		StringBuilder sb = new StringBuilder();
		sb.append(ans);
		System.out.println(sb.toString());
	}

}