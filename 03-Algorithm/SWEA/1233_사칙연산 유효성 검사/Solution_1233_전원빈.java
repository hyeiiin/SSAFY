package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1233 {
	static int n;
	static char[] no;
	static int ans;
	static int stand;
	
	static void gogo(int num) {
		if(num > n) {
			ans = 0;
			return;
		}
		if(no[num] == '/') {
			gogo(num*2);
			gogo(num*2+1);
			return;
		}else if(no[num] == '+') {
			gogo(num*2);
			gogo(num*2+1);
			return;
		}else if(no[num] == '-') {
			gogo(num*2);
			gogo(num*2+1);
			return;
		}else if(no[num] == '*') {
			gogo(num*2);
			gogo(num*2+1);
			return; 
		}
		if(num*2 <= n) {
			if(no[num*2] != ' ')ans = 0;
			return;
		}
		if(num*2 + 1 <= n) {
			if(no[num*2+1] != ' ')ans = 0;
			return;
		}
		return;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int test = 1; test <= 10; test++) {
			n = Integer.parseInt(bf.readLine());
			no = new char[n+1];
			for(int i = 0; i< n; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				int head = Integer.parseInt(st.nextToken());
				no[head] = st.nextToken().charAt(0);
			}
			ans = 1;
			gogo(1);
			sb.append("#").append(test).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}

}
