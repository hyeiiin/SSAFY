import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_김나연 {
	
	static int n,r,c, num;
	
	public static void go(int n, int y, int x, StringBuilder sb) {
		
		if(y==r&&x==c) {
			sb.append(num);
			return;
		}
		
		if(r<y+n && r>=y && c<x+n && c>=x) {
			go(n/2, y, x,sb);
			go(n/2, y, x+n/2,sb);
			go(n/2, y+n/2, x,sb);
			go(n/2, y+n/2, x+n/2,sb);
		}else {
			num = num+n*n;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		n=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		go( (int) Math.pow(2, n), 0, 0, sb);
		
		System.out.println(sb);
	}

}
