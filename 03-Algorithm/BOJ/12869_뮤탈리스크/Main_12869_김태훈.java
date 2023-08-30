package baekjoon;
import java.util.*;
import java.io.*;
public class Main_12869_김태훈 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int N, hp[], max;
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		hp = new int[3];
		max = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			hp[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void go(int scv1, int scv2, int scv3, int cnt) {
		if(scv1 <=0 && scv2<=0 && scv3<=0) {
			max = Math.max(max, cnt);
			return;
		}
		
		go(scv1-9, scv2-3, scv3-1, cnt+1);
		go(scv1-9, scv2-1, scv3-3, cnt+1);
		go(scv1-1, scv2-3, scv3-9, cnt+1);
		go(scv1-1, scv2-9, scv3-3, cnt+1);
		go(scv1-3, scv2-9, scv3-1, cnt+1);
		go(scv1-3, scv2-1, scv3-9, cnt+1);
	}
	
	public static void main(String[] args) throws IOException{
		init();
		go(hp[0], hp[1], hp[2], 0);
		System.out.println(max);
	}

}
