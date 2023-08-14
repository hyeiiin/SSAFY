package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1074_전상혁 {
	//static int n;
	static int N,R,C;
	static int cnt = 0;
	//static int map[][];
	public static void main(String[] args) throws IOException {
		//N,r,c
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		//r=3,c=1
		//map[3][1] 을 몇번째로 방문했는지 출력
		//N>1 인 경우, 배열을 크기가 2^(N-1)x2^(N-1)로 4등분
		//배열의 크기 2^N*2^N -> N=3, 8*8
		//부분행렬의 크기 = (int)(Math.pow(2,N-1)); //N=3 p=4 4*4
		
		//n = (int) Math.pow(2,N);
		//map = new int[n][n];
		
		
		makez(0,0, (int)Math.pow(2, N));
		
	}
	private static void makez(int r, int c, int size) {
		//size가 1일 경우 1x1행렬이므로 값은 1밖에 도출이 안됨
		if (size==1) {
			System.out.println(cnt);
			return;
		}
		//half_size(부분행렬의 사이즈) = 2^N/2
		int half_size = size/2;
		//1사분면
		//찾으려는 행(R)과 열(C)이 4등분한 범위 안에 있을 경우 재귀 탐색
		if (R< r+half_size && C< c + half_size) {
			makez(r,c, half_size);
		}
		//2사분면
		//행은 변화없고 열만 half_size 이상
		//찾으려는 행과 열이 2사분면 범위 안에 있을 경우 재귀 탐색
		if (R< r+half_size && c+half_size<=C) {
			//탐색한 칸의 개수 카운트
			//1사분면엔 없는 것을 확인해서 온거라 1사분면의 개수를 포함해서 세야 함
			cnt += (size*size) / 4; 
			makez(r,c+half_size, half_size);
		}
		//3사분면
		//열은 변화 없고 행이 half_size 이상
		//찾으려는 행과 열이 3사분면 범위 안에 있을 경우 재귀 탐색
		if (R>=r+half_size && C< c+half_size) {
			//1사분면,2사분면 없는 것을 확인하고 왔으므로 1,2,사분면의 칸 개수를 포함해서 시작
			cnt += ((size*size) / 4)* 2;
			makez(r+half_size, c, half_size);
		}
		//4사분면
		//행과 열 둘다 half_size이상에서 시작
		//찾으려는 행과 열이 4사분면 범위 안에 있을 경우 재귀 탐색
		if (R>=r+half_size && C>=c+half_size) {
			//1,2,3사분면의 칸 개수 포함하여 시작
			cnt += ((size*size)/4 )* 3;
			makez(r+half_size, c+half_size, half_size);
		}
		
	}
	
		

}
