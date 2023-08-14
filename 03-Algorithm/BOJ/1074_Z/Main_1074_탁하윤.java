package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_탁하윤 {
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 배열 크기, 2^N*2^N
		int r = Integer.parseInt(st.nextToken());	// 찾고 싶은 r행
		int c = Integer.parseInt(st.nextToken());	// 찾고 싶은 c행
		cnt = 0;	// 몇 번째로 방문했는지 결과값 
		
		div(r, c, (int)Math.pow(2, N));	// 찾고 싶은 r행, c행, 배열 길이(2^N)
		
		System.out.println(cnt);
	}
	
	// 구역 나누기
	private static void div(int r, int c, int size) {
		
		if(size == 1) {	// 만약 더 나눌 사이즈가 없다면 종료
			return;
		}
		if(r<size/2 && c<size/2) {	// 찾고 싶은 r행 c행이 1사분면 이라면, 더할 값이 없으니 다시 쪼개기
			div(r, c, size/2);
		} else if(r<size/2 && c>=size/2) {	// 2사분면 이라면, 1사분면을 지나쳐 오니 값 더하고, 다시 쪼개기 (다시 호출할 때는 r과 c의 상대위치로)
			cnt += (size*size)/4;
			div(r, c-size/2, size/2);
		} else if(r>=size/2 && c<size/2) {	// 3사분면 이라면, 1-2사분면을 지나쳐 오니 값 더하고, 다시 쪼개기
			cnt += ((size*size)/4)*2;
			div(r-size/2, c, size/2);
		} else if(r>=size/2 && c>=size/2) {	// 4사분면 이라면, 1-2-3사분면을 지나쳐 오니 값 더하고, 다시 쪼개기
			cnt += ((size*size)/4)*3;
			div(r-size/2, c-size/2, size/2);
		}
	}

}
