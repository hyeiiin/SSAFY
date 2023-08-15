package baekjoon;
import java.util.*;
import java.io.*;
public class Main_1074_김태훈 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	static int n, r, c, v, count;
	
	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		v = (int)Math.pow(2, n); 
	}
	
	private static void go(int v, int x, int y) {
		//종료 조건 
		if(x == r && y == c) {
			sb.append(count);
			return ;
		}
		if(r<x+v && r>=x && c<y+v && c>=y) {
			go(v/2, x, y);
			go(v/2, x, y+v/2);
			go(v/2, x+v/2, y);
			go(v/2, x+v/2, y+v/2);
		}else {			
			count += v*v;
		}
	}
	
	private static void print() {
		System.out.println(sb);
	}
	public static void main(String[] args) throws IOException {
		init();
		go(v, 0, 0);
		print();
	}

}

