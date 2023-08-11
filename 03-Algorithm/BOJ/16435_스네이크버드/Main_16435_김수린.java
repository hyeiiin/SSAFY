package BOJ.Test;

import java.io.*;
import java.util.*;

public class Main_16435_김수린 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L, fruitHeight[];
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		fruitHeight = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			fruitHeight[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruitHeight);
		for(int i = 0; i < N; i++) {
			if(L >= fruitHeight[i]) L++;
			else break;
		}
		
		System.out.println(L);
	}
}
