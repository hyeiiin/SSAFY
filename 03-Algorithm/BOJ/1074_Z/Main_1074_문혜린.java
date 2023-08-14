package algorithm.baekjoon;

import java.util.*;
import java.io.*;

//Z
public class Main_1074_문혜린 {
	static int cnt = 0;
	static int r, c;
	
	public static void z(int x, int y, int size) {
		if(size == 1) {
			System.out.println(cnt);
			return;
		}
		
		//좌상
		if(r < x+size/2 && c < y+size/2) { //구하려는 위치가 1사분면에 있을 경우
			z(x, y, size/2);
		}
		//우상
		if(r < x+size/2 && c >= y+size/2) { //구하려는 위치가 2사분면에 있을 경우
			cnt += size*size / 4;
			z(x, y+size/2, size/2);
		}
		//좌하
		if(r >= x+size/2 && c < y+size/2) { //구하려는 위치가 3사분면에 있을 경우
			cnt += size*size / 4 * 2;
			z(x+size/2, y, size/2);
		}
		//우하
		if(r >= x+size/2 && c >= y+size/2) { //구하려는 위치가 4사분면에 있을 경우
			cnt += size*size / 4 * 3;
			z(x+size/2, y+size/2, size/2);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //2^N인 2차원 배열
		r = Integer.parseInt(st.nextToken()); //r행
		c = Integer.parseInt(st.nextToken()); //c열
		
		int size = (int)Math.pow(2, N);
		
		z(0, 0, size);
	}

}
